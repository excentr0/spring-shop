package com.excentro.shopui.controllers;

import com.excentro.persist.repo.BrandRepository;
import com.excentro.service.CategoryService;
import com.excentro.service.ProductService;
import com.excentro.shopui.service.CartService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ShopUiController {
  private final CategoryService categoryService;
  private final ProductService productService;
  private final BrandRepository brandService;
  private final CartService cartService;

  @GetMapping
  public String index(Model model) {
    modelAddAttributes(model);
    model.addAttribute("products", productService.findAll());
    return "shop-index";
  }

  private void modelAddAttributes(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("brands", brandService.findAll());
    model.addAttribute("items", cartService.getLineItems().size());
    model.addAttribute("subTotal", cartService.getSubTotal());
  }

  @GetMapping(path = "/shop-product-list.html")
  public String category(Model model) {
    modelAddAttributes(model);
    model.addAttribute("products", productService.findAll());
    return "shop-product-list";
  }

  @GetMapping(path = "/product/{id}")
  public String product(Model model, @PathVariable Long id) {
    modelAddAttributes(model);
    model.addAttribute("product", productService.findById(id));
    return "shop-item";
  }
}
