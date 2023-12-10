package nuricanozturk.dev.data.usermanagement.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "surname", nullable = false, length = 80)
    private String surname;

    @Column(name = "username", nullable = false, length = 80)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "budget", nullable = false)
    private double budget;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User()
    {
    }

    public User(String name, String surname, String username, String password, double budget, Gender gender)
    {
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.budget = budget;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
