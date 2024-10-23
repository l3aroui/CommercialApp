package ma.ram.commercialapp.services;


import ma.ram.commercialapp.entities.commercial.PhoneNumber;

public interface PhoneNumberServiceI {
    PhoneNumber addPhoneNumberToClient(PhoneNumber phoneNumber, Long id);
    void addPhoneNumberToCommercial(PhoneNumber phoneNumber,Long commercialId);
    void UpdatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(Long id);
}