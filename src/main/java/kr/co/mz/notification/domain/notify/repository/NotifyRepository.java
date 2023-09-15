package kr.co.mz.notification.domain.notify.repository;

import kr.co.mz.notification.domain.notify.entity.NotifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyRepository extends JpaRepository<NotifyEntity, Integer>, CustomNotifyRepository {
}
