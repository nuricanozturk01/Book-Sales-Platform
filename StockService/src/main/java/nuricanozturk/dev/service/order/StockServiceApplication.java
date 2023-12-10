package nuricanozturk.dev.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("nuricanozturk.dev.service.order.repository")
public class StockServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(StockServiceApplication.class, args);
    }

}
