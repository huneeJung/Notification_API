package kr.co.mz.notification.common.mapper.notification;

import javax.annotation.processing.Generated;
import kr.co.mz.notification.domain.notify.category.entity.CategoryEntity;
import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import kr.co.mz.notification.domain.notify.entity.NotifyEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T11:12:55+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotifyDto toNotifyDto(NotifyEntity notifyEntity) {
        if ( notifyEntity == null ) {
            return null;
        }

        NotifyDto notifyDto = new NotifyDto();

        notifyDto.setId( notifyEntity.getId() );
        notifyDto.setTitle( notifyEntity.getTitle() );
        notifyDto.setContent( notifyEntity.getContent() );
        notifyDto.setCategoryCode( notifyEntityCategoryEntityCode( notifyEntity ) );
        notifyDto.setCategoryName( notifyEntityCategoryEntityName( notifyEntity ) );
        notifyDto.setPriorityStatus( notifyEntity.getPriorityStatus() );
        notifyDto.setPublicationStatus( notifyEntity.getPublicationStatus() );
        notifyDto.setCreateBy( notifyEntity.getCreateBy() );
        notifyDto.setCreatedAt( notifyEntity.getCreatedAt() );
        notifyDto.setModifiedAt( notifyEntity.getModifiedAt() );
        notifyDto.setTopNotice( notifyEntity.isTopNotice() );

        return notifyDto;
    }

    private String notifyEntityCategoryEntityCode(NotifyEntity notifyEntity) {
        if ( notifyEntity == null ) {
            return null;
        }
        CategoryEntity categoryEntity = notifyEntity.getCategoryEntity();
        if ( categoryEntity == null ) {
            return null;
        }
        String code = categoryEntity.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String notifyEntityCategoryEntityName(NotifyEntity notifyEntity) {
        if ( notifyEntity == null ) {
            return null;
        }
        CategoryEntity categoryEntity = notifyEntity.getCategoryEntity();
        if ( categoryEntity == null ) {
            return null;
        }
        String name = categoryEntity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
