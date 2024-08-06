package ma.ram.commercialapp.services.servicesImpl;
import ma.ram.commercialapp.entities.Address;
import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.repositories.AddressRepository;
import ma.ram.commercialapp.repositories.ClientRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.services.AddressServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressServiceI {
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final CommercialRepository commercialRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ClientRepository clientRepository, CommercialRepository commercialRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.commercialRepository = commercialRepository;
    }
    @Override
    public void addAddressForClient(Address address, Long id) {
        Client client=clientRepository.findById(id).orElseThrow(()->new RuntimeException(("client not found")));
        if (client.getAddresses().size()<2){
            client.getAddresses().add(address);
            addressRepository.save(address);
            clientRepository.save(client);
        }else{
            throw  new RuntimeException("Client can have just 2 addresses");
        }
    }
    @Override
    public void addAddressForCommercial(Address address, Long id) {
        Commercial commercial=commercialRepository.findById(id).orElseThrow(()->new RuntimeException(("client not found")));
        if (commercial.getAddresses().size()<2){
            commercial.getAddresses().add(address);
            addressRepository.save(address);
            commercialRepository.save(commercial);
        }else{
            throw  new RuntimeException("Commercial can have just 2 addresses");
        }
    }
    @Override
    public void UpdateAddress(Address address, Long id) {
        Address updatedAddress=addressRepository.findById(address.getId()).orElseThrow(()->new RuntimeException("address not found"));
        updatedAddress.setCity(address.getCity());
        updatedAddress.setCountry(address.getCountry());
        updatedAddress.setPostalCode(address.getPostalCode());
        updatedAddress.setDistrict(address.getDistrict());
        addressRepository.save(updatedAddress);
    }
    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
