package nuricanozturk.dev.service.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nuricanozturk.dev.service.payment.dto.EPaymentStatus;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "price", nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private EPaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Payment(UUID bookId, String bookName, double price, EPaymentStatus paymentStatus, User user)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.user = user;
    }
}
