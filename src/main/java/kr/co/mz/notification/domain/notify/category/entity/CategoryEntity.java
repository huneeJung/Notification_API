package kr.co.mz.notification.domain.notify.category.entity;

import jakarta.persistence.*;
import kr.co.mz.notification.common.entity.BaseEntity;
import kr.co.mz.notification.domain.notify.entity.NotifyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notify_category")
public class CategoryEntity extends BaseEntity {
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.PERSIST)
    private List<NotifyEntity> notifyEntityList = new ArrayList<>();

    public void addNotifyEntity(NotifyEntity notifyEntity) {
        this.notifyEntityList.add(notifyEntity);
        notifyEntity.setCategoryEntity(this);
    }
}
