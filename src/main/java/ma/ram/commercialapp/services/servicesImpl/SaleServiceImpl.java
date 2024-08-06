package ma.ram.commercialapp.services.servicesImpl;

import jakarta.persistence.EntityNotFoundException;
import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.entities.Sale;
import ma.ram.commercialapp.repositories.ClientRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.repositories.SaleRepository;
import ma.ram.commercialapp.services.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleServiceI {


    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final CommercialRepository commercialRepository;
    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ClientRepository clientRepository, CommercialRepository commercialRepository) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.commercialRepository = commercialRepository;
    }
    @Override
    public Sale addSale(Sale sale, Long commercialId, Long clientId) {
        Commercial commercial = commercialRepository.findById(commercialId)
                .orElseThrow(() -> new EntityNotFoundException("Commercial not found"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.getSales().add(sale);
        commercial.getSales().add(sale);
        sale.setCommercial(commercial);
        sale.setClient(client);
        return saleRepository.save(sale);
    }
    @Override
    public void updateSale(Sale sale) {
        Sale updatedSale=saleRepository.findById(sale.getId()).orElseThrow(()->new RuntimeException("sale not found"));
        updatedSale.setDateOfSale(LocalDateTime.now());
        updatedSale.setPrice(sale.getPrice());
        updatedSale.setQuantity(sale.getQuantity());
        updatedSale.setClient(sale.getClient());
        saleRepository.save(updatedSale);
    }
    @Override
    public List<Sale> viewAllSales() {
        return saleRepository.findAll();
    }
    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
