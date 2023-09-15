package kr.co.mz.notification.domain.notify.service;

import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotifyService {

    public Page<NotifyDto> findAll(Pageable pageable, String categoryCode);
}
