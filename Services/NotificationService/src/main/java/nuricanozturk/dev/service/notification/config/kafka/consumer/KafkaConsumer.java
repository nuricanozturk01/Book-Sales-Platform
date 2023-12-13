package nuricanozturk.dev.service.notification.config.kafka.consumer;

import nuricanozturk.dev.service.notification.dto.NotificationSaveDTO;
import nuricanozturk.dev.service.notification.notificationservice.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final NotificationService m_notificationService;

    public KafkaConsumer(NotificationService notificationService)
    {
        m_notificationService = notificationService;
    }

    @KafkaListener(topics = "${spring.kafka.order-status-info-topic-name}",
            groupId = "${spring.kafka.consumer.order-info-group-id}",
            containerFactory = "configOrderInfoKafkaListener")
    public void consumeOrderStockInfo(StockInfo stockInfo)
    {
        if (stockInfo.bookStatus() == BookStatus.FINISHED)
        {
            var saveDTO = new NotificationSaveDTO(stockInfo.userId(),
                    stockInfo.bookId(),
                    stockInfo.bookName(),
                    EPaymentStatus.FAIL, 0, -1, stockInfo.message());

            m_notificationService.saveNotification(saveDTO);
            System.err.println(saveDTO);
        }

    }

    @KafkaListener(topics = "${spring.kafka.payment-topic-name}",
            groupId = "${spring.kafka.consumer.payment-group-id}",
            containerFactory = "configPaymentInfoKafkaListener")
    public void consumePaymentInfo(PaymentInfo paymentInfo)
    {
        var saveDTO = new NotificationSaveDTO(paymentInfo.userId(),
                paymentInfo.bookId(),
                paymentInfo.bookName(),
                paymentInfo.paymentStatus(), paymentInfo.cost(),
                paymentInfo.availableBalance(), paymentInfo.message());

        m_notificationService.saveNotification(saveDTO);
        System.err.println(saveDTO);
    }
}
