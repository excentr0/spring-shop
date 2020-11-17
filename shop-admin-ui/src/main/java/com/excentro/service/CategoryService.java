package com.excentro.service;

import com.excentro.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
  List<CategoryDto> findAll();

  CategoryDto findById(Long id);

  void save(CategoryDto category);
}
