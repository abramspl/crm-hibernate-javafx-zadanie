package pl.sda.crm.service;

import pl.sda.crm.entity.PremiumStatusType;

import java.time.LocalDate;
import java.util.Objects;

public class RegisterPremiumStatusForm {

    private final boolean active;
    private final LocalDate expireAt;
    private final PremiumStatusType type;


    public RegisterPremiumStatusForm(boolean active, LocalDate expireAt, PremiumStatusType type) {
        this.active = active;
        this.expireAt = expireAt;
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public PremiumStatusType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterPremiumStatusForm that = (RegisterPremiumStatusForm) o;
        return active == that.active && expireAt.equals(that.expireAt) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, expireAt, type);
    }

    @Override
    public String toString() {
        return "RegisterPremiumStatusForm{" +
                "active=" + active +
                ", expireAt=" + expireAt +
                ", type=" + type +
                '}';
    }
}
