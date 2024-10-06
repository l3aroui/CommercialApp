package ma.ram.commercialapp.services;

import ma.ram.commercialapp.entities.Address;

public interface AddressServiceI {
    void addAddressForClient(Address address,Long id);
    void addAddressForCommercial(Address address,Long commercialId);
    void UpdateAddress(Address address,Long id);
    void deleteAddress(Long id);
}
