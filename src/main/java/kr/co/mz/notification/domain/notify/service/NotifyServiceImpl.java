package kr.co.mz.notification.domain.notify.service;

import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import kr.co.mz.notification.domain.notify.repository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NotifyServiceImpl implements NotifyService {

    private final NotifyRepository notifyRepository;

    public Page<NotifyDto> findAll(Pageable pageable, String categoryCode) {
        return (categoryCode == null || categoryCode.equals("AL01")) ?
                notifyRepository.findAllDto(pageable)
                : notifyRepository.findAllDtoByCategory(categoryCode, pageable);
    }
}
