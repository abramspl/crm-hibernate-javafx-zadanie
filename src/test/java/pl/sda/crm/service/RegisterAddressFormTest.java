package pl.sda.crm.service;

import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.crm.exception.CustomerAlreadyExistsException;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAddressFormTest {

    private final AddressRegistration registration =
            new AddressRegistration(HibernateUtil.getSessionFactory());

    @Test
    void shouldRegisterAddress(){
        //given
        final var form = new RegisterAddressForm("kolejowa","warszawa", "22-123", CountryCode.PL);
        //when
        final var registeredAddressId = registration.registerAddress(form);
        //then
        assertNotNull(registeredAddressId.getId());
    }

    @Test
    void shouldNotRegisterPersonIfStreetAndZipCodeAlreadyExists(){
        //given
        final var form = new RegisterAddressForm("pogodna","warszawa", "22-123", CountryCode.PL);
        registration.registerAddress(form);

        //when & then
        assertThrows(CustomerAlreadyExistsException.class, () -> registration.registerAddress(form));
    }
}