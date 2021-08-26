package pl.sda.crm.service;

import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.Test;
import pl.sda.crm.entity.*;
import pl.sda.crm.exception.CustomerAlreadyExistsException;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyCustomerAndAddressRegistrationTest {

    private final CompanyCustomerRegistration registration =
            new CompanyCustomerRegistration(HibernateUtil.getSessionFactory());
    private final AddressRegistration registration2 =
            new AddressRegistration(HibernateUtil.getSessionFactory());

    @Test
    void shouldRegisterCompanyCustomer() {
        //given
        final var form = new RegisterCompanyForm("AAAA", "7894561231");
        final var form2 = new RegisterAddressForm("kolejowa", "warszawa","00-001", CountryCode.PL);

//        final Company company = new Company("xcx", new Nip("7894561231"));
        //when
        final var registeredCustomerId = registration.registerCompany(form);
        final var registeredAddressId = registration2.registerAddress(form2);

        //then
        assertNotNull(registeredCustomerId.getId());
//        System.out.println(company.getId());
        System.out.println(registeredCustomerId.getId());
        System.out.println(registeredAddressId.getId());
        System.out.println(registeredCustomerId.getId());

        //when
        //then
        assertNotNull(registeredAddressId.getId());

    }


    @Test
    void shouldNotRegisterCompanyIfLastNameAndPeselAlreadyExists() {
        // given
        final var form = new RegisterCompanyForm("BBBB", "7894561231");
        registration.registerCompany(form);

        // when & then
        assertThrows(CustomerAlreadyExistsException.class, () -> registration.registerCompany(form));
    }



}
