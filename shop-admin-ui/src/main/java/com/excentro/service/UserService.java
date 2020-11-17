package com.excentro.service;

import com.excentro.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<Object> findById(Long id);

  List<UserDto> findAll();

  void save(UserDto user);
}
