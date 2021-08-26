package pl.sda.crm.entity;

import com.sun.istack.NotNull;
import pl.sda.crm.service.RegisterPremiumStatusForm;
import pl.sda.crm.util.ArgumentValidator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static pl.sda.crm.util.ArgumentValidator.*;

@Entity
@Table(name = "premiumStatuses")
public class PremiumStatus {

    @Id
    private UUID id;
    private boolean active;
    private LocalDate expireAt;
    @NotNull
    private PremiumStatusType type;

    private PremiumStatus(){}

    public PremiumStatus(boolean active, LocalDate expireAt, PremiumStatusType type) {
//        validate(active != null, "sssss" );
        validate(expireAt != null && !expireAt.isAfter(LocalDate.now()), "zła data" );
        validate(type != null, "zły status type");
        this.id = UUID.randomUUID();
        this.active = active;
        this.expireAt = expireAt;
        this.type = type;
    }

    public static PremiumStatus from(RegisterPremiumStatusForm form){
        return new PremiumStatus(form.isActive(), form.getExpireAt(), form.getType());
    }

    public UUID getId() {
        return id;
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
        PremiumStatus that = (PremiumStatus) o;
        return active == that.active && id.equals(that.id) && expireAt.equals(that.expireAt) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, expireAt, type);
    }
}
