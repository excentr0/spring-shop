package com.excentro.controller;

import com.excentro.persist.model.Category;
import com.excentro.persist.repo.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
  private final CategoryRepository categoryService;

  @GetMapping
  public String categories(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    return "categories";
  }

  @GetMapping("/{id}")
  public String editCategory(@PathVariable Long id, Model model) {
    Category category = categoryService.findById(id).orElse(new Category());
    model.addAttribute("category", category);
    return "categoryform";
  }

  @GetMapping("/add")
  public String addCategory(Model model) {
    Category category = new Category();
    model.addAttribute("category", category);
    return "categoryform";
  }

  @PostMapping("/update")
  public String updateCategory(Category category) {
    categoryService.save(category);
    return "redirect:/categories";
  }
}
