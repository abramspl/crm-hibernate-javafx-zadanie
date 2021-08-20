package pl.sda.crm.entity;

import pl.sda.crm.util.ArgumentValidator;

import javax.persistence.Column;

import java.util.Objects;

import static pl.sda.crm.util.ArgumentValidator.*;

public class Nip {

    @Column(name = "NIP")
    private String value;

    private Nip(){
    }

    public Nip(String value) {
        validate(value != null && value.matches("\\d{10}"),
                "nip is invalid: " + value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nip nip = (Nip) o;
        return value.equals(nip.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
