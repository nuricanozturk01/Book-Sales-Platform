package nuricanozturk.dev.data.usermanagement.config.kafka;

import nuricanozturk.dev.data.usermanagement.dto.UserInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaProducer
{
    private final NewTopic m_userInfoTopic;
    @Value("${spring.kafka.log-topic-name}")
    private String m_logTopic;
    private final KafkaTemplate<String, UserInfo> m_kafkaProducer;
    private final KafkaTemplate<String, String> m_logKafkaProducer;

    public UserKafkaProducer(NewTopic userInfoTopic, KafkaTemplate<String, UserInfo> kafkaProducer, KafkaTemplate<String, String> logKafkaProducer)
    {
        m_userInfoTopic = userInfoTopic;
        m_kafkaProducer = kafkaProducer;
        m_logKafkaProducer = logKafkaProducer;
    }

    public void sendUserInfo(UserInfo userInfo)
    {
        var message = MessageBuilder.
                withPayload(userInfo).
                setHeader(KafkaHeaders.TOPIC, m_userInfoTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void sendUserInfo(String log)
    {
        m_logKafkaProducer.send(m_logTopic, log);
    }
}