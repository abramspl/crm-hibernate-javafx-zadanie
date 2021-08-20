package pl.sda.crm.entity;

import com.neovisionaries.i18n.CountryCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private Session session;
    private Transaction tx;

    @BeforeEach
    void before() {
        session = factory.openSession();
        tx = session.beginTransaction();
    }

    @AfterEach
    void after() {
        tx.rollback(); // wycofanie transakcji aby jeden test nie wpływał na resztę testów
        session.close();
    }

    @Test
    void shouldSaveCompanyInDatabase(){
        // given
        final var company = new Company("TASK", new Nip("5212399995"));

        // when
        saveAndFlush(company);

        // then
        final var readCompany = session.get(Company.class, company.getId());
        assertEquals(company, readCompany);
    }

    @Test
    void shouldAddAddress() {
        // given
        final var customer = new Company("Microsoft", new Nip("1234567891"));
        final var address = new Address("marszałkowska", "Wawa", "11-300", CountryCode.PL);
        customer.addAddress(address);

        // when
        saveAndFlush(customer);

        // then
        final var readCustomer = session.get(Customer.class, customer.getId());
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }

    private void saveAndFlush(Company company){
        session.save(company);
        session.flush();
        session.clear();
    }
}