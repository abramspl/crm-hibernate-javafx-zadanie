package pl.sda.crm.service;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class RegisterCompanyForm {

    private final String name;
    private final String nipNumber;

    public RegisterCompanyForm(String name, String nipNumber) {
        this.name = requireNonNull(name);
        this.nipNumber = requireNonNull(nipNumber);
    }

    public String getName() {
        return name;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterCompanyForm that = (RegisterCompanyForm) o;
        return name.equals(that.name) && nipNumber.equals(that.nipNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nipNumber);
    }

    @Override
    public String toString() {
        return "RegisterCompanyForm{" +
                "name='" + name + '\'' +
                ", nipNumber='" + nipNumber + '\'' +
                '}';
    }
}
