package com.excentro.controller;

import com.excentro.dto.ProductDto;
import com.excentro.persist.repo.BrandRepository;
import com.excentro.service.CategoryService;
import com.excentro.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {
  private final ProductService productService;
  private final BrandRepository brandRepository;
  private final CategoryService categoryService;

  @GetMapping
  public String products(Model model) {
    model.addAttribute("products", productService.findAll());
    return "products";
  }

  @GetMapping("/{id}")
  public String editProduct(@PathVariable Long id, Model model) {
    model.addAttribute("product", productService.findById(id));
    model.addAttribute("brands", brandRepository.findAll());
    model.addAttribute("categories", categoryService.findAll());
    return "productform";
  }

  @GetMapping("/add")
  public String addProduct(Model model) {
    model.addAttribute("product", new ProductDto());
    model.addAttribute("brands", brandRepository.findAll());
    model.addAttribute("categories", categoryService.findAll());
    return "productform";
  }

  @PostMapping("/update")
  public String updateProduct(ProductDto product, RedirectAttributes redirectAttributes) {
    try {
      productService.save(product);
    } catch (Exception e) {
      log.error("Can't create or update product", e);
      redirectAttributes.addFlashAttribute("error", true);
      if (product.getId() == null) {
        return "redirect:/products/add";
      }
      return "redirect:/products/" + product.getId();
    }
    return "redirect:/products";
  }
}
