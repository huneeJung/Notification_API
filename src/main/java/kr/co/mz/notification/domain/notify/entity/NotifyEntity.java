package kr.co.mz.notification.domain.notify.entity;

import jakarta.persistence.*;
import kr.co.mz.notification.common.entity.BaseEntity;
import kr.co.mz.notification.config.auditor.AuditorAwareImpl;
import kr.co.mz.notification.constant.PriorityStatus;
import kr.co.mz.notification.constant.PublicationStatus;
import kr.co.mz.notification.domain.notify.category.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notify")
@EntityListeners(AuditorAwareImpl.class)
public class NotifyEntity extends BaseEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "top_notice")
    private boolean topNotice;
    @Enumerated(EnumType.ORDINAL)
    private PriorityStatus priorityStatus;
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;
    @CreatedBy
    @Column(name = "create_by")
    private Integer createBy;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
}
