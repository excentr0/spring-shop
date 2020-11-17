package com.excentro.controller;

import com.excentro.service.RoleService;
import com.excentro.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
  private final RoleService roleService;

  @GetMapping
  public String roles(Model model) {
    model.addAttribute("roles", roleService.findAll());
    return "roles";
  }

  @GetMapping("/{id}")
  public String editRole(@PathVariable Long id, Model model) {
    RoleDto role = roleService.findById(id);
    model.addAttribute("role", role);
    return "roleform";
  }

  @GetMapping("/add")
  public String addRole(Model model) {
    RoleDto role = new RoleDto();
    model.addAttribute("role", role);
    return "roleform";
  }

  @PostMapping("/update")
  public String updateRole(RoleDto role) {
    roleService.save(role);
    return "redirect:/roles";
  }
}
