package pl.sda.crm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.crm.entity.Company;
import pl.sda.crm.exception.CustomerAlreadyExistsException;

import static java.util.Objects.requireNonNull;

public class CompanyCustomerRegistration {

    private final SessionFactory sessionFactory;

    public CompanyCustomerRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    public RegisteredCustomerId registerCompany(RegisterCompanyForm form) {
        final var session = sessionFactory.openSession();
        final var tx = session.beginTransaction();

        //1.
        if (companyExists(form, session)) {
           throw new CustomerAlreadyExistsException("customer exists, check data: " + form);
        }

        //2.
        final  var company = Company.from(form);

        //3.
        session.save(company);

        //4.
        tx.commit();
        session.close();
        return new RegisteredCustomerId(company.getId());
    }

    private Boolean companyExists(RegisterCompanyForm form, Session session) {
        return session.createQuery(
                "select count (c) > 0 " +
                        "from Company c " +
                        "where c.name = ?1 and c.nipNumber.value = ?2",
                Boolean.class)
                .setParameter(1, form.getName())
                .setParameter(2, form.getNipNumber())
                .getSingleResult();
    }
}