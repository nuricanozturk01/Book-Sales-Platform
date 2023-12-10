package nuricanozturk.dev.service.book.config;

import nuricanozturk.dev.service.book.dto.BookInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafka
{
    private final NewTopic m_bookTopic;
    private final KafkaTemplate<String, BookInfo> m_kafkaProducer;

    public ProducerKafka(NewTopic bookTopic, KafkaTemplate<String, BookInfo> kafkaProducer)
    {
        m_bookTopic = bookTopic;
        m_kafkaProducer = kafkaProducer;
    }

    public void sendBookInfo(BookInfo bookInfo)
    {
        var message = MessageBuilder.
                withPayload(bookInfo).
                setHeader(KafkaHeaders.TOPIC, m_bookTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}
