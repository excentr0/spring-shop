package com.excentro.service;

import com.excentro.dto.RoleDto;

import java.util.List;

public interface RoleService {
  List<RoleDto> findAll();

  RoleDto findById(Long id);

  void save(RoleDto role);
}
