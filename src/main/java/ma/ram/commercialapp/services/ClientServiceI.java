package ma.ram.commercialapp.services;

import ma.ram.commercialapp.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClientServiceI {
    Client addClient(Client client, String commercialId);
    Client editClient(Client client);
    void deleteClient(Long id);
    Page<Client> viewAllCommercialClients(Long idCommercial, Pageable pageable);
    Client viewClient(Long id);
    void addClientToCategory(Long idCategory,Long idClient,Long idCommercial);
}
