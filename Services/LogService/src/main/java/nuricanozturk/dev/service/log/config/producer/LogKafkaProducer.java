package nuricanozturk.dev.service.log.config.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogKafkaProducer
{
    private final NewTopic m_topic;
    private final KafkaTemplate<String, String> m_kafkaTemplate;

    public LogKafkaProducer(NewTopic topic, KafkaTemplate<String, String> kafkaTemplate)
    {
        m_topic = topic;
        m_kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String log)
    {
        m_kafkaTemplate.send(m_topic.name(), log);
    }
}
