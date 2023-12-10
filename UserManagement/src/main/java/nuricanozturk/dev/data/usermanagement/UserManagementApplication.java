package nuricanozturk.dev.data.usermanagement;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nuricanozturk.dev.data.usermanagement", "callofproject.dev"})
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class UserManagementApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(UserManagementApplication.class, args);
    }

}
