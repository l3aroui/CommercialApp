package ma.ram.commercialapp.controllers;



import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.services.CommercialServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/commercial")
public class CommercialController {

    private final CommercialServiceI commercialServiceI;

    @Autowired
    public CommercialController(CommercialServiceI commercialServiceI) {
        this.commercialServiceI = commercialServiceI;
    }

    @GetMapping
    public ResponseEntity<Commercial> getCurrentUser() {
        // Récupérer l'ID utilisateur depuis le token JWT
        String keycloakUserId = commercialServiceI.userKeycloakId();

        // Trouver ou créer l'utilisateur dans la base de données
        Commercial commercial = commercialServiceI.findOrCreateUser(keycloakUserId, getClaimsFromToken());

        return ResponseEntity.ok(commercial);
    }

    private Map<String, Object> getClaimsFromToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Jwt) {
            return ((Jwt) principal).getClaims();
        }
        return Collections.emptyMap();
    }

}
