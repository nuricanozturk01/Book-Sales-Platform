package nuricanozturk.dev.service.payment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "user_id", unique = true)
    private UUID userId;

    @Column(name = "username", nullable = false, length = 80)
    private String username;

    @Column(name = "budget", nullable = false)
    private double budget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Payment> payments;

    public User(UUID userId, String username, double budget)
    {
        this.userId = userId;
        this.username = username;
        this.budget = budget;
    }

    public void addPayment(Payment payment)
    {
        if (payments == null)
            payments = new HashSet<>();

        payment.setUser(this);
        payments.add(payment);
    }
}
