package ma.ram.commercialapp.services;


import ma.ram.commercialapp.entities.PhoneNumber;

public interface PhoneNumberServiceI {
    void addPhoneNumberToClient(PhoneNumber phoneNumber, Long id);
    void addPhoneNumberToCommercial(PhoneNumber phoneNumber,Long id);
    void UpdatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(Long id);
}