package nuricanozturk.dev.service.payment.config;

import nuricanozturk.dev.service.payment.config.producer.PaymentInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer
{
    private final NewTopic m_paymentTopic;
    private final KafkaTemplate<String, PaymentInfo> m_kafkaProducer;
    //@Value("${spring.kafka.log-topic-name}")
    private final NewTopic m_logTopic;
    private final KafkaTemplate<String, String> m_logProducer;

    public PaymentProducer(NewTopic paymentTopic, KafkaTemplate<String, PaymentInfo> kafkaProducer,
                           @Qualifier("log-topic") NewTopic logTopic, KafkaTemplate<String, String> logProducer)
    {
        m_paymentTopic = paymentTopic;
        m_kafkaProducer = kafkaProducer;
        m_logTopic = logTopic;
        m_logProducer = logProducer;
    }

    public void publishPaymentInfo(PaymentInfo paymentInfo)
    {
        var message = MessageBuilder
                .withPayload(paymentInfo)
                .setHeader(KafkaHeaders.TOPIC, m_paymentTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void publishLog(String log)
    {
        m_logProducer.send(m_logTopic.name(), log);
    }
}
