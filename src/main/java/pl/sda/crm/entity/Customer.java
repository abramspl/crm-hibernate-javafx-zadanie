package pl.sda.crm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // standardowa strategia dziedziczenia
@DiscriminatorColumn(name = "customer_type") // ustala nazwę kolumny określającą typ obiektu
public abstract class Customer {

    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;
//    @OneToOne
//    @JoinColumn(name = "customer_id")
//    private PremiumStatus premiumStatus;

    public Customer() {
        this.id = UUID.randomUUID();
        this.addresses = new ArrayList<>();
//        this.premiumStatus = premiumStatus;
    }

    public UUID getId() {
        return id;
    }

    public void addAddress(Address address) {
        if (!this.addresses.contains(address)) {
            this.addresses.add(address);
        }
    }

    public List<Address> getAddresses() {
        return new ArrayList<>(addresses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
