package nuricanozturk.dev.service.log.config.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumer(SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "${spring.kafka.log-topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeLog(String log)
    {
        System.err.println(log);
        messagingTemplate.convertAndSend("/log/receivedMessage", log);
    }
}
