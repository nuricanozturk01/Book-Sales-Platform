package nuricanozturk.dev.data.usermanagement.config.kafka;

import nuricanozturk.dev.data.usermanagement.dto.UserInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaProducer
{
    private final NewTopic m_userInfoTopic;
    private final KafkaTemplate<String, UserInfo> m_kafkaProducer;

    public UserKafkaProducer(NewTopic userInfoTopic, KafkaTemplate<String, UserInfo> kafkaProducer)
    {
        m_userInfoTopic = userInfoTopic;
        m_kafkaProducer = kafkaProducer;
    }

    public void sendUserInfo(UserInfo userInfo)
    {
        var message = MessageBuilder.
                withPayload(userInfo).
                setHeader(KafkaHeaders.TOPIC, m_userInfoTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}
