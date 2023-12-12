package nuricanozturk.dev.service.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * The executor configuration.
 * CopyRight(C) 2023 by Call Of Project Teams.
 */
@Component
public class ExecutorConfig
{
    /**
     * The default constructor.
     */
    public ExecutorConfig()
    {

    }

    /**
     * Creates a new executor service.
     *
     * @return The executor service.
     */
    @Bean
    public ExecutorService provideExecutorService()
    {
        return newCachedThreadPool();
    }
}