package pl.sda.crm.service;

import com.neovisionaries.i18n.CountryCode;

import java.util.Objects;

import static java.util.Objects.*;

public class RegisterAddressForm {

    private final String street;
    private final String city;
    private final String zipCode;
    private final CountryCode countryCode;

    public RegisterAddressForm(String street, String city, String zipCode, CountryCode countryCode) {
        this.street = requireNonNull(street);
        this.city = requireNonNull(city);
        this.zipCode = requireNonNull(zipCode);
        this.countryCode = requireNonNull(countryCode);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterAddressForm that = (RegisterAddressForm) o;
        return street.equals(that.street) && city.equals(that.city) && zipCode.equals(that.zipCode) && countryCode == that.countryCode;
    }

    @Override
    public int hashCode() {
        return hash(street, city, zipCode, countryCode);
    }

    @Override
    public String toString() {
        return "RegisterAddressForm{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
