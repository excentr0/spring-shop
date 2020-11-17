package com.excentro.service.impl;

import com.excentro.dto.CategoryDto;
import com.excentro.persist.model.Category;
import com.excentro.persist.repo.CategoryRepository;
import com.excentro.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public List<CategoryDto> findAll() {
    return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
  }

  @Override
  public CategoryDto findById(Long id) {
    return categoryRepository.findById(id).map(CategoryDto::new).orElse(new CategoryDto());
  }

  @Override
  public void save(CategoryDto categoryDto) {
    Category category = new Category();
    category.setId(categoryDto.getId());
    category.setName(categoryDto.getName());
    categoryRepository.save(category);
  }
}
