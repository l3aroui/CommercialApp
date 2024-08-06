package ma.ram.commercialapp.controllers;

import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.repositories.CategoryRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.services.ClientServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientServiceI clientService;
    private final CommercialRepository commercialRepository;
    @Autowired
    public ClientController(ClientServiceI clientService, CommercialRepository commercialRepository) {
        this.clientService = clientService;
        this.commercialRepository = commercialRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Client>> clientList(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable= PageRequest.of(page,size);
        Long IdCommercial=commercialRepository.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(clientService.viewAllCommercialClients(IdCommercial,pageable));
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client,@AuthenticationPrincipal  UserDetails userDetails){
        Long idCommercial=commercialRepository.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.created(URI.create("/api/client"+client.getId())).body(clientService.addClient(client,idCommercial));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Client> showClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.viewClient(id));
    }

    @DeleteMapping("/delete")
    public void deleteClient(@RequestParam Long id){
        clientService.deleteClient(id);
    }
    @PostMapping("/edit")
    public ResponseEntity<Client> editClient(@RequestBody Client client){
         return ResponseEntity.ok(clientService.editClient(client));
    }

    @PostMapping("addToCategory")
    public void addClientToCategory(@RequestParam Long idCategory,@AuthenticationPrincipal  UserDetails userDetails,@RequestParam Long idClient){
        clientService.addClientToCategory(idCategory,idClient,commercialRepository.findByUsername(userDetails.getUsername()).getId());
    }
}
