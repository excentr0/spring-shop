package com.excentro.controller;

import com.excentro.service.RoleService;
import com.excentro.service.UserService;
import com.excentro.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
  private final UserService     userService;
  private final RoleService     roleService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping
  public String login(Model model) {
    model.addAttribute("users", userService.findAll());
    return "users";
  }

  @GetMapping("/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    model.addAttribute("user", userService.findById(id).orElse(new UserDto()));
    model.addAttribute("roles", roleService.findAll());
    return "userform";
  }

  @GetMapping("/add")
  public String addUser(Model model) {
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("user", new UserDto());
    return "userform";
  }

  @PostMapping("/update")
  public String updateUser(UserDto user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.save(user);
    return "redirect:/users";
  }
}
