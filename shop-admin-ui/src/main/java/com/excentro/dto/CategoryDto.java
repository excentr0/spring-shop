package com.excentro.dto;

import com.excentro.persist.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
  private Long id;
  private String name;
  private long productCount;

  public CategoryDto(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }
}
