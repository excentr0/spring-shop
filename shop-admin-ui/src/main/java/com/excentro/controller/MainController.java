package com.excentro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainController {

  @GetMapping
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String table() {
    return "login";
  }

  @GetMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  }
}
