package ma.ram.commercialapp.services.servicesImpl;
import ma.ram.commercialapp.entities.Client;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.entities.PhoneNumber;
import ma.ram.commercialapp.repositories.ClientRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.repositories.PhoneNumberRepository;
import ma.ram.commercialapp.services.PhoneNumberServiceI;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberServiceI {

    private final PhoneNumberRepository phoneNumberRepository;
    private final ClientRepository clientRepository;
    private final CommercialRepository commercialRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository, ClientRepository clientRepository, CommercialRepository commercialRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.clientRepository = clientRepository;
        this.commercialRepository = commercialRepository;
    }
    @Override
    public PhoneNumber addPhoneNumberToClient(PhoneNumber phoneNumber, Long id) {
        Client client=clientRepository.findById(id).orElseThrow(()->new RuntimeException("client not found"));
        if (client.getPhoneNumbers().size()<2){
            client.getPhoneNumbers().add(phoneNumber);
            phoneNumber.setClient(client);
            clientRepository.save(client);
        }else throw new RuntimeException("cant add more than 2 numbers");
        return phoneNumber;
    }
    @Override
    public void addPhoneNumberToCommercial(PhoneNumber phoneNumber, Long  commercialId) {
        Commercial commercial =commercialRepository.findById(commercialId).orElseThrow(()->new RuntimeException("commercial not found"));
        commercial.getPhoneNumber().add(phoneNumber);
        phoneNumberRepository.save(phoneNumber);
    }
    @Override
    public void UpdatePhoneNumber(PhoneNumber phoneNumber) {
        PhoneNumber phoneNumberUpdated=phoneNumberRepository.findById(phoneNumber.getId()).orElseThrow(()->new RuntimeException("phone number notFound"));
        phoneNumberUpdated.setPhoneNumber(phoneNumber.getPhoneNumber());
        phoneNumberRepository.save(phoneNumberUpdated);
    }
    @Override
    public void deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
    }

}
