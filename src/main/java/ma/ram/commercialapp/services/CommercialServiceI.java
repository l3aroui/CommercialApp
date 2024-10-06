package ma.ram.commercialapp.services;


import ma.ram.commercialapp.entities.Commercial;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommercialServiceI {

    String userKeycloakId();
    Long userId();

    Commercial findOrCreateUser(String keycloakUserId, Map<String, Object> claims);

}
