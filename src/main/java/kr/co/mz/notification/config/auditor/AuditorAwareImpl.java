package kr.co.mz.notification.config.auditor;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {
    public @Nonnull Optional<Integer> getCurrentAuditor() {
        return Optional.of(1);
    }
}
