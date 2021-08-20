package pl.sda.crm.service;

import org.junit.jupiter.api.Test;
import pl.sda.crm.exception.CustomerAlreadyExistsException;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyCustomerRegistrationTest {

    private final CompanyCustomerRegistration registration =
            new CompanyCustomerRegistration(HibernateUtil.getSessionFactory());

    @Test
    void shouldRegisterCompanyCustomer() {
        //given
        final var form = new RegisterCompanyForm("AAAA", "7894561231");

        //when
        final var registeredCustomerId = registration.registerCompany(form);

        //then
        assertNotNull(registeredCustomerId.getId());
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