package ma.ram.commercialapp.services;

import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.entities.Sale;

import java.util.List;

public interface SaleServiceI {
    Sale addSale(Sale sale, Long commercialId, Long clientId);
    void updateSale(Sale sale);
    List<Sale> viewAllSales();
    void deleteSale(Long id);
}
