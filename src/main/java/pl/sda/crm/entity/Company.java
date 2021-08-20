package pl.sda.crm.entity;

import pl.sda.crm.service.RegisterCompanyForm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import java.util.Objects;

import static java.util.Objects.*;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@DiscriminatorValue("COMPANY") // ta wartość będzie wpisywana w bazie danych (DB) kolumnie customer_type
public class Company extends Customer {

    private String name;

    @Embedded
    private Nip nipNumber;

    private Company(){
    }

    public Company(String name, Nip nipNumber) {
        validate(name != null && !name.isBlank(), "company name is invalid: " + name);
        this.name = name;
        this.nipNumber = requireNonNull(nipNumber,"company nip is null");
    }

    public static Company from(RegisterCompanyForm form){
        return new Company(form.getName(), new Nip(form.getNipNumber()));
    }

    public String getName() {
        return name;
    }

    public Nip getNipNumber() {
        return nipNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return name.equals(company.name) && nipNumber.equals(company.nipNumber);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), name, nipNumber);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", nipNumber=" + nipNumber +
                "} " + super.toString();
    }
}