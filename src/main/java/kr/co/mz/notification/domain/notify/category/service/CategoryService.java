package kr.co.mz.notification.domain.notify.category.service;

import kr.co.mz.notification.domain.notify.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}
