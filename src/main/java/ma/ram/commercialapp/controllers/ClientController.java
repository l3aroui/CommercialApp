package ma.ram.commercialapp.controllers;

import lombok.RequiredArgsConstructor;
import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.PhoneNumber;

import ma.ram.commercialapp.services.ClientServiceI;
import ma.ram.commercialapp.services.CommercialServiceI;
import ma.ram.commercialapp.services.PhoneNumberServiceI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServiceI clientService;
    private final CommercialServiceI commercialServiceI;
    private final PhoneNumberServiceI phoneNumberServiceI;
    @GetMapping
    public ResponseEntity<Page<Client>> clientList(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable= PageRequest.of(page,size);

        return ResponseEntity.ok(clientService.viewAllCommercialClients(commercialServiceI.userId(),pageable));
    }
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        return ResponseEntity.created(URI.create("/api/v1/client"+client.getId())).body(clientService.addClient(client,commercialServiceI.userKeycloakId()));
    }
    @GetMapping("/{id}")
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
    @PostMapping("/addToCategory")
    public void addClientToCategory(@RequestParam Long idCategory,@RequestParam Long idClient){
        clientService.addClientToCategory(idCategory,idClient,commercialServiceI.userId());
    }
    @PostMapping("/addPhoneNumber/{id}")
    public ResponseEntity<PhoneNumber> addPhoneNumberToClient(@RequestBody PhoneNumber phoneNumber,@PathVariable("id") Long clientId){
        return ResponseEntity.ok(phoneNumberServiceI.addPhoneNumberToClient(phoneNumber,clientId));
    }

    @PostMapping("/editPhone")
    public void updatePhoneNumber(@RequestBody PhoneNumber phoneNumber){
        phoneNumberServiceI.UpdatePhoneNumber(phoneNumber);
    }

    @DeleteMapping("/deleteNumber/{id}")
    public void delete(@PathVariable("id") Long phoneId){
        phoneNumberServiceI.deletePhoneNumber(phoneId);
    }
}
