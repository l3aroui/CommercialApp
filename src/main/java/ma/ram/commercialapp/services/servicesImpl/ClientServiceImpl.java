package ma.ram.commercialapp.services.servicesImpl;
import ma.ram.commercialapp.entities.Category;
import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.repositories.CategoryRepository;
import ma.ram.commercialapp.repositories.ClientRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.services.ClientServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientServiceI {
    private final ClientRepository clientRepository;
    private final CommercialRepository commercialRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CommercialRepository commercialRepository, CategoryRepository categoryRepository) {
        this.clientRepository = clientRepository;
        this.commercialRepository = commercialRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Client addClient(Client client, Long commercialId) {
        Commercial commercial=commercialRepository.findById(commercialId).orElseThrow(()->new RuntimeException("commercial not found"));
        if (!commercial.getClients().contains(client)){
            throw  new RuntimeException("Client already exist");
        }
        commercial.getClients().add(client);
        return clientRepository.save(client);
    }
    @Override
    public Client editClient(Client client) {
        Client updateClient = clientRepository.findById(client.getId()).orElseThrow(() -> new RuntimeException("client not found"));
        updateClient.setEmail(client.getEmail());
        updateClient.setFirstname(client.getFirstname());
        updateClient.setLastname(client.getLastname());
        return clientRepository.save(updateClient);
    }
    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    @Override
    public Page<Client> viewAllCommercialClients(Long idCommercial, Pageable pageable) {
        return clientRepository.findAllByCommercialId(idCommercial, pageable);
    }
    @Override
    public Client viewClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("user not exist"));
    }

    @Override
    public void addClientToCategory(Long idCategory, Long idClient, Long idCommercial) {
        Commercial commercial=commercialRepository.findById(idCommercial).orElseThrow(()->new RuntimeException("commercial not found"));
        Client client=clientRepository.findById(idClient).orElseThrow(()->new RuntimeException("client not found"));
        Category category=categoryRepository.findById(idCategory).orElseThrow(()->new RuntimeException("category not found"));
        if (commercial.getClients().contains(client)&&commercial.getCategories().contains(category)){
            category.getClients().add(client);
            categoryRepository.save(category);
        }else {
            throw new RuntimeException("This client or category is not available to you");
        }
    }

}