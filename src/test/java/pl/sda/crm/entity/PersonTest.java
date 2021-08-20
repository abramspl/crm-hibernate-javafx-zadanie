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

class PersonTest {

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
    void shouldSavePersonInDatabase() {
        // given
        final var person = new Person("Jan", "Kowalski", new Pesel("92030202211"));

        // when
        saveAndFlush(person);

        // then
        // pobierz Person po jego id.
        final var readPerson = session.get(Person.class, person.getId());
        assertEquals(person, readPerson);
    }

    @Test
    void shouldAddAddress() {
        // given
        final var customer = new Person("Jan", "Kowalski", new Pesel("92020303441"));
        final var address = new Address("str", "Wawa", "11-300", CountryCode.PL);
        customer.addAddress(address);

        // when
        // session.save(address); ręczne zapisanie adres
        saveAndFlush(customer);

        // then
        final var readCustomer = session.get(Customer.class, customer.getId());
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }

    private void saveAndFlush(Person person) {
        session.save(person); // dodanie encji do cache -> Map<ID, ENTITY> = person.id, person
        // poniższego nie używaj w kodzie produkcyjnym, to jest tylko do testów
        session.flush(); // zapisz encje w bazie danych
        session.clear(); // czyści cache
    }
}