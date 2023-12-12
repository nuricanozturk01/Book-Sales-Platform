package nuricanozturk.dev.service.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configure the email.
 * CopyRight(C) 2023 by Call Of Project Teams.
 */
@Configuration
public class EmailConfig
{
    @Value("${spring.mail.username}")
    private String m_email;

    @Value("${spring.mail.password}")
    private String m_password;

    /**
     * The default constructor.
     */
    public EmailConfig()
    {

    }

    /**
     * Configure the JavaMailSender.
     *
     * @return The JavaMailSender.
     */
    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(m_email);
        mailSender.setPassword(m_password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

}