package nuricanozturk.dev.service.payment.config.consumer;

import lombok.RequiredArgsConstructor;
import nuricanozturk.dev.service.payment.config.consumer.dto.StockInfo;
import nuricanozturk.dev.service.payment.config.consumer.dto.UserInfo;
import nuricanozturk.dev.service.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer
{
    private final PaymentService m_paymentService;


    @KafkaListener(topics = "${spring.kafka.user-topic-name}", groupId = "${spring.kafka.consumer.user-group-id}", containerFactory = "configUserInfoKafkaListener")
    public void consumeOrderInfo(UserInfo userInfo)
    {
        System.err.println("User info: " + userInfo);

        switch (userInfo.operationStatus())
        {
            case CREATE -> m_paymentService.creatUser(userInfo);
            case UPDATE -> m_paymentService.updateUser(userInfo);
            case REMOVE -> m_paymentService.removeUser(userInfo);
            default -> throw new UnsupportedOperationException("Unsupported operation!");
        }
    }


    @KafkaListener(topics = "${spring.kafka.stock-topic-name}", groupId = "${spring.kafka.consumer.stock-group-id}", containerFactory = "configStockInfoKafkaListener")
    public void doPayment(StockInfo stockInfo)
    {
        //System.err.println("Stock info: " + stockInfo);
        m_paymentService.pay(stockInfo);
    }
}
