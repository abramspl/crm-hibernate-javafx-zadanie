package pl.sda.crm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.crm.entity.Address;
import pl.sda.crm.exception.CustomerAlreadyExistsException;

public class AddressRegistration {

    private final SessionFactory sessionFactory;

    public AddressRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RegisteredCustomerId registerAddress(RegisterAddressForm form){
        final var session = sessionFactory.openSession();
        final var tx = session.beginTransaction();

        //1.
        if(addressExists(form, session)){
            throw new CustomerAlreadyExistsException("");
        }

        //2.
        final var address = Address.from(form);

        //3.
        session.save(address);

        //4.
        tx.commit();
        session.close();
        return new RegisteredCustomerId(address.getId());
    }

    private boolean addressExists(RegisterAddressForm form, Session session) {
        return session.createQuery(
                "select count(a) > 0 " +
                        "from Address a " +
                        "where a.street = ?1 and a.city = ?2 and a.zipCode = ?3",
                Boolean.class)
                .setParameter(1,form.getStreet())
                .setParameter(2,form.getCity())
                .setParameter(3,form.getZipCode())
                .getSingleResult();
    }
}