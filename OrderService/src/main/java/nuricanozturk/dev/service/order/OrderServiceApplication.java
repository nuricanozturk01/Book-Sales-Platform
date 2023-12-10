package nuricanozturk.dev.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"nuricanozturk.dev.service.order", "callofproject.dev"})
@EntityScan({"nuricanozturk.dev.service.order.entity", "callofproject.dev"})
@EnableJpaRepositories(basePackages = {"nuricanozturk.dev.service.order.repository", "callofproject.dev"})
public class OrderServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
