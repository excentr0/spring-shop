package com.excentro.service.impl;

import com.excentro.dto.RoleDto;
import com.excentro.persist.model.Role;
import com.excentro.persist.repo.RoleRepository;
import com.excentro.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public List<RoleDto> findAll() {
    return roleRepository.findAll().stream().map(RoleDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public RoleDto findById(Long id) {
    return roleRepository.findById(id).map(RoleDto::new).orElse(new RoleDto());
  }

  @Override
  public void save(RoleDto roleDto) {
    Role role = new Role();
    role.setId(roleDto.getId());
    role.setAuthority(roleDto.getAuthority());
    role.setUsers(roleDto.getUsers());
    roleRepository.save(role);
  }
}
