package nuricanozturk.dev.service.book.config;

import nuricanozturk.dev.service.book.dto.BookInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafka
{
    private final NewTopic m_bookTopic;

    @Value("${spring.kafka.log-topic-name}")
    private String m_logTopic;
    private final KafkaTemplate<String, String> m_logKafkaProducer;
    private final KafkaTemplate<String, BookInfo> m_kafkaProducer;


    public ProducerKafka(NewTopic bookTopic, KafkaTemplate<String, BookInfo> kafkaProducer, KafkaTemplate<String, String> logKafkaProducer)
    {
        m_bookTopic = bookTopic;
        m_kafkaProducer = kafkaProducer;
        m_logKafkaProducer = logKafkaProducer;
    }

    public void sendBookInfo(BookInfo bookInfo)
    {
        System.err.println("Sending book info to kafka: " + bookInfo);
        var message = MessageBuilder.
                withPayload(bookInfo).
                setHeader(KafkaHeaders.TOPIC, m_bookTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void sendLog(String log)
    {
        m_logKafkaProducer.send(m_logTopic, log);
    }
}
