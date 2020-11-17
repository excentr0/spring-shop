package com.excentro.shopui.controllers;

import com.excentro.service.ProductService;
import com.excentro.shopui.service.CartService;
import com.excentro.shopui.service.model.LineItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
  private final CartService cartService;
  private final ProductService productService;

  @GetMapping
  public String cartPage(Model model) {
    model.addAttribute("lineItems", cartService.getLineItems());
    model.addAttribute("subTotal", cartService.getSubTotal());
    model.addAttribute("items", cartService.getLineItems().size());
    return "shop-shopping-cart";
  }

  @PostMapping
  public String updateCart(LineItem lineItem) {
    lineItem.setProductDto(productService.findById(lineItem.getProductId()));
    cartService.updateCart(lineItem);
    return "redirect:/cart";
  }

  @DeleteMapping
  public String deleteLineItem(LineItem lineItem) {
    lineItem.setProductDto(productService.findById(lineItem.getProductId()));
    cartService.removeProduct(lineItem);
    return "redirect:/cart";
  }
}
