package kr.co.mz.notification.domain.notify.category.repository;

import kr.co.mz.notification.domain.notify.category.dto.CategoryDto;
import kr.co.mz.notification.domain.notify.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("""
                select new kr.co.mz.notification.domain.notify.category.dto.CategoryDto(
                    ce.code, ce.name
                )
                from CategoryEntity ce
            """)
    List<CategoryDto> findAllCategoryDto();

}
