package com.excentro.controller;

import com.excentro.persist.model.Brand;
import com.excentro.persist.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController {
  private final BrandRepository brandRepository;

  @Autowired
  public BrandController(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @GetMapping
  public String brands(Model model) {
    model.addAttribute("brands", brandRepository.findAll());
    return "brands";
  }

  @GetMapping("/{id}")
  public String editBrand(@PathVariable Long id, Model model) {
    model.addAttribute("brand", brandRepository.findById(id).orElse(new Brand()));
    return "brandform";
  }

  @GetMapping("/add")
  public String addBrand(Model model) {
    model.addAttribute("brand", new Brand());
    return "brandform";
  }

  @PostMapping("/update")
  public String updateBrand(Brand brand) {
    brandRepository.save(brand);
    return "redirect:/brands";
  }
}
