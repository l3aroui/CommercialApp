package ma.ram.commercialapp.controllers;
import ma.ram.commercialapp.entities.Sale;
import ma.ram.commercialapp.services.CommercialServiceI;
import ma.ram.commercialapp.services.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/sale")
public class SaleController{
    private final SaleServiceI saleServiceI;
    private final CommercialServiceI commercialServiceI;
    @Autowired
    public SaleController(SaleServiceI saleServiceI,CommercialServiceI commercialServiceI) {
        this.saleServiceI = saleServiceI;
        this.commercialServiceI=commercialServiceI;
    }
    @PostMapping("/{id}")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale,@PathVariable("id") Long clientId) {
        return ResponseEntity.ok(saleServiceI.addSale(sale,commercialServiceI.userId(),clientId));
    }
    @PostMapping("/edit")
    public void updateSale(@RequestBody Sale sale) {
        saleServiceI.updateSale(sale);
    }
    @GetMapping
    public List<Sale> viewAllSales() {
        return saleServiceI.viewAllSales();
    }
    @DeleteMapping("delete/{id}")
    public void deleteSale(@PathVariable("id") Long saleId) {
        saleServiceI.deleteSale(saleId);
    }
}