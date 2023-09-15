package kr.co.mz.notification.domain.notify.dto;

import kr.co.mz.notification.constant.PriorityStatus;
import kr.co.mz.notification.constant.PublicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotifyDto {
    private Integer id;
    private String title;
    private String content;
    private String categoryCode;
    private String categoryName;
    private boolean topNotice;
    private PriorityStatus priorityStatus;
    private PublicationStatus publicationStatus;
    private Integer createBy;
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;
}
