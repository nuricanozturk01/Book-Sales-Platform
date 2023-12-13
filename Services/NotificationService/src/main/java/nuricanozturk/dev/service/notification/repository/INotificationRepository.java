package nuricanozturk.dev.service.notification.repository;

import nuricanozturk.dev.service.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends MongoRepository<Notification, String>
{
}
