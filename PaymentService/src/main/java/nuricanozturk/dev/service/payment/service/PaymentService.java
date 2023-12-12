package nuricanozturk.dev.service.payment.service;

import callofproject.dev.library.exception.service.DataServiceException;
import jakarta.transaction.Transactional;
import nuricanozturk.dev.service.payment.config.PaymentProducer;
import nuricanozturk.dev.service.payment.config.consumer.dto.StockInfo;
import nuricanozturk.dev.service.payment.config.consumer.dto.UserInfo;
import nuricanozturk.dev.service.payment.config.producer.PaymentInfo;
import nuricanozturk.dev.service.payment.dto.EPaymentStatus;
import nuricanozturk.dev.service.payment.entity.Payment;
import nuricanozturk.dev.service.payment.entity.User;
import nuricanozturk.dev.service.payment.repository.IPaymentRepository;
import nuricanozturk.dev.service.payment.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;

@Service
public class PaymentService
{
    private final IUserRepository m_userRepository;
    private final IPaymentRepository m_paymentRepository;
    private final PaymentProducer m_paymentProducer;

    public PaymentService(IUserRepository userRepository, IPaymentRepository paymentRepository, PaymentProducer paymentProducer)
    {
        m_userRepository = userRepository;
        m_paymentRepository = paymentRepository;
        m_paymentProducer = paymentProducer;
    }

    public void creatUser(UserInfo userInfo)
    {
        var user = new User(userInfo.userId(), userInfo.username(), userInfo.budget());
        doForDataService(() -> m_userRepository.save(user), "User already exists!");
    }

    private User findUserIfExists(UUID userId)
    {
        var user = doForDataService(() -> m_userRepository.findById(userId), "PaymentService::findUserIfExists");

        if (user.isEmpty())
            throw new DataServiceException("User not found!");

        return user.get();
    }

    public void updateUser(UserInfo userInfo)
    {
        var user = findUserIfExists(userInfo.userId());

        user.setBudget(userInfo.budget());

        doForDataService(() -> m_userRepository.save(user), "PaymentService::updateUser");
    }

    public void removeUser(UserInfo userInfo)
    {
        doForDataService(() -> m_userRepository.delete(findUserIfExists(userInfo.userId())), "PaymentService::removeUser");
    }

    @Transactional
    public void pay(StockInfo stockInfo)
    {
        var user = findUserIfExists(stockInfo.userId());

        if (user.getBudget() < stockInfo.price())
            rejectPayment(stockInfo, user);

        // set new budget
        user.setBudget(user.getBudget() - stockInfo.price());
        var payment = new Payment(stockInfo.bookId(), stockInfo.bookName(), stockInfo.price(), EPaymentStatus.SUCCESS, user);

        doForDataService(() -> m_paymentRepository.save(payment), "PaymentService::payment::pay");
        doForDataService(() -> m_userRepository.save(user), "PaymentService::user::pay");

        var paymentInfo = new PaymentInfo(user.getUserId(), stockInfo.bookId(), stockInfo.bookName(), stockInfo.price(), user.getBudget());
        m_paymentProducer.publishPaymentInfo(paymentInfo);
    }

    private void rejectPayment(StockInfo stockInfo, User user)
    {
        var payment = new Payment(stockInfo.bookId(), stockInfo.bookName(), stockInfo.price(), EPaymentStatus.FAIL, user);
        doForDataService(() -> m_paymentRepository.save(payment), "PaymentService::payment::pay");

        var paymentInfo = new PaymentInfo(user.getUserId(), stockInfo.bookId(), stockInfo.bookName(), stockInfo.price(), user.getBudget());
        m_paymentProducer.publishPaymentInfo(paymentInfo);
    }
}
