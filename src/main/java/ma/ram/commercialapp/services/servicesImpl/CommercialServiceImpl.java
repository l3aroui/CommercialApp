package ma.ram.commercialapp.services.servicesImpl;

import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.services.CommercialServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CommercialServiceImpl implements CommercialServiceI {


    @Autowired
    private CommercialRepository commercialRepository;

    @Override
    public String userKeycloakId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = "";

        // Vérifier si le principal est une instance de Jwt
        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            Map<String, Object> claims = jwt.getClaims();

            // Essayer d'obtenir "user_id" ou "sub" selon ce que tu utilises dans Keycloak
            if (claims.containsKey("user_id")) {
                userId = String.valueOf(claims.get("user_id"));
            } else if (claims.containsKey("sub")) {
                userId = String.valueOf(claims.get("sub")); // "sub" est souvent l'ID de l'utilisateur dans JWT
            }
        }

        return userId;
    }

    @Override
    public Long userId() {
        Commercial commercial=commercialRepository.findByKeycloakId(userKeycloakId()).orElseThrow(()->new RuntimeException("I'm in CommercialServiceImpl"));
        return commercial.getId();
    }

    public Commercial findOrCreateUser(String keycloakUserId, Map<String, Object> claims) {
        Optional<Commercial> existingUser = commercialRepository.findByKeycloakId(keycloakUserId);

        if (existingUser.isPresent()) {
            return existingUser.get(); // Retourner l'utilisateur s'il existe déjà
        } else {
            // Créer un nouvel utilisateur si aucun utilisateur avec cet ID n'existe
            Commercial newCommercial = new Commercial();
            newCommercial.setKeycloakId(keycloakUserId);

            // Récupérer et définir d'autres informations à partir des claims
            if (claims.containsKey("preferred_username")) {

                newCommercial.setUsername((String) claims.get("preferred_username"));
            }

            if (claims.containsKey("given_name")) {
                newCommercial.setFirstname((String) claims.get("given_name"));
            }
            if (claims.containsKey("family_name")) {
                newCommercial.setLastname((String) claims.get("family_name"));
            }
            if (claims.containsKey("email")) {
                newCommercial.setEmail((String) claims.get("email"));
            }
            newCommercial.setKeycloakId(userKeycloakId());
            return commercialRepository.save(newCommercial); // Sauvegarder le nouvel utilisateur
        }
    }
}
