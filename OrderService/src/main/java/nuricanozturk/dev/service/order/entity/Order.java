package nuricanozturk.dev.service.order.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "book_id")
    private UUID bookId;

    public Order()
    {
    }

    public Order(User user, UUID bookId)
    {
        this.user = user;
        this.bookId = bookId;
    }

    public UUID getBookId()
    {
        return bookId;
    }

    public void setBookId(UUID bookId)
    {
        this.bookId = bookId;
    }

    public Order(User user)
    {
        this.user = user;
    }


    public UUID getOrderId()
    {
        return orderId;
    }

    public void setOrderId(UUID orderId)
    {
        this.orderId = orderId;
    }


    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
