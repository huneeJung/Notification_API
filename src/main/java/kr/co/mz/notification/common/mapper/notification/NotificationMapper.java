package kr.co.mz.notification.common.mapper.notification;

import kr.co.mz.notification.domain.notify.dto.NotifyDto;
import kr.co.mz.notification.domain.notify.entity.NotifyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "categoryCode", source = "categoryEntity.code")
    @Mapping(target = "categoryName", source = "categoryEntity.name")
    @Mapping(target = "priorityStatus", source = "priorityStatus")
    @Mapping(target = "publicationStatus", source = "publicationStatus")
    @Mapping(target = "createBy", source = "createBy")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "modifiedAt", source = "modifiedAt")
    NotifyDto toNotifyDto(NotifyEntity notifyEntity);
}
