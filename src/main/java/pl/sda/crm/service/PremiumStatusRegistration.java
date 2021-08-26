package pl.sda.crm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.crm.entity.PremiumStatus;
import pl.sda.crm.exception.CustomerAlreadyExistsException;

public class PremiumStatusRegistration {

    private final SessionFactory sessionFactory;

    public PremiumStatusRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RegisteredCustomerId registerPremiumStatus(RegisterPremiumStatusForm form) {
        final var session = sessionFactory.openSession();
        final var tx = session.beginTransaction();

        //1.
        if (premiumStatus(form, session)) {
            throw new CustomerAlreadyExistsException("");
        }

        //2.
        final var premiumStatus = PremiumStatus.from(form);

        //3.
        session.save(premiumStatus);

        //4.
        tx.commit();
        session.close();
        return new RegisteredCustomerId(premiumStatus.getId());
    }

    private boolean premiumStatus(RegisterPremiumStatusForm form, Session session) {
        return session.createQuery(
                        "select count(p) > 0 " +
                                "from PremiumStatus p " +
                                "where p.active = ?1 and p.expireAt = ?2 and p.type = ?3",
                        Boolean.class)
                .setParameter(1, form.isActive())
                .setParameter(2, form.getExpireAt())
                .setParameter(3, form.getType())
                .getSingleResult();
    }
}
