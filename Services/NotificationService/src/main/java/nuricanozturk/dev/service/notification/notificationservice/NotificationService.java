package nuricanozturk.dev.service.notification.notificationservice;

import nuricanozturk.dev.service.notification.dto.NotificationSaveDTO;
import nuricanozturk.dev.service.notification.entity.Notification;
import nuricanozturk.dev.service.notification.repository.INotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService
{
    private final INotificationRepository m_notificationRepository;

    public NotificationService(INotificationRepository notificationRepository)
    {
        m_notificationRepository = notificationRepository;
    }

    public void saveNotification(NotificationSaveDTO saveDTO)
    {
        var notification = new Notification(saveDTO.userId(), saveDTO.bookId(), saveDTO.bookName(), saveDTO.cost(),
                saveDTO.availableBalance());

        m_notificationRepository.save(notification);
    }
}
