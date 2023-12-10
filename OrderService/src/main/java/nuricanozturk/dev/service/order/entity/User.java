package nuricanozturk.dev.service.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import nuricanozturk.dev.service.order.dto.UserOperationStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "user_id")
    private UUID userId;
    private String username;
    private double budget;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_status")
    private UserOperationStatus operationStatus;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Order> orders;

    public User()
    {
    }

    public void addOrder(Order order)
    {
        if (orders == null)
            orders = new HashSet<>();

        orders.add(order);
    }

    public User(UUID userId, String username, double budget, UserOperationStatus operationStatus)
    {
        this.userId = userId;
        this.username = username;
        this.budget = budget;
        this.operationStatus = operationStatus;
    }

    public UserOperationStatus getOperationStatus()
    {
        return operationStatus;
    }

    public void setOperationStatus(UserOperationStatus operationStatus)
    {
        this.operationStatus = operationStatus;
    }

    public Set<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }
}
