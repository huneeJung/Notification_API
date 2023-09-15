package kr.co.mz.notification.domain.notify.repository;

import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomNotifyRepository {

    @Query("""
                select new kr.co.mz.notification.domain.notify.dto.NotifyDto(
                    ne.id,ne.title,ne.content,ne.categoryEntity.code,ne.categoryEntity.name,ne.topNotice,
                    ne.priorityStatus,ne.publicationStatus,ne.createBy,ne.createdAt,ne.modifiedAt
                )
                from NotifyEntity ne
                where ne.categoryEntity.code = :categoryCode or ne.topNotice = true
                order by ne.topNotice desc, ne.priorityStatus desc
            """)
    Page<NotifyDto> findAllDtoByCategory(@Param("categoryCode") String categoryCode, Pageable pageable);

    @Query("""
                    select new kr.co.mz.notification.domain.notify.dto.NotifyDto(
                        ne.id,ne.title,ne.content,ne.categoryEntity.code,ne.categoryEntity.name,ne.topNotice,
                        ne.priorityStatus,ne.publicationStatus,ne.createBy,ne.createdAt,ne.modifiedAt
                    )
                    from NotifyEntity ne
                    order by ne.topNotice desc, ne.priorityStatus desc
            """)
    Page<NotifyDto> findAllDto(Pageable pageable);

}
