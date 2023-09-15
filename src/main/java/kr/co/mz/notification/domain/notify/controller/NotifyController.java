package kr.co.mz.notification.domain.notify.controller;

import jakarta.annotation.Nullable;
import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import kr.co.mz.notification.domain.notify.service.NotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotifyController {

    private final NotifyService notifyService;

    @GetMapping
    public ResponseEntity<Page<NotifyDto>> findAll(
            @PageableDefault Pageable pageable,
            @Nullable @Param("categoryCode") String categoryCode
    ) {
        return ResponseEntity.ok(notifyService.findAll(pageable, categoryCode));
    }
}
