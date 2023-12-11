package nuricanozturk.dev.service.payment.config;

import nuricanozturk.dev.service.payment.config.producer.PaymentInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer
{
    private final NewTopic m_paymentTopic;
    private final KafkaTemplate<String, PaymentInfo> m_kafkaProducer;

    public PaymentProducer(NewTopic paymentTopic, KafkaTemplate<String, PaymentInfo> kafkaProducer)
    {
        m_paymentTopic = paymentTopic;
        m_kafkaProducer = kafkaProducer;
    }

    public void publishPaymentInfo(PaymentInfo paymentInfo)
    {
        var message = MessageBuilder
                .withPayload(paymentInfo)
                .setHeader(KafkaHeaders.TOPIC, m_paymentTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}
