package com.excentro.service.impl;

import com.excentro.dto.UserDto;
import com.excentro.persist.model.User;
import com.excentro.persist.repo.UserRepository;
import com.excentro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Optional<Object> findById(Long id) {
    return userRepository.findById(id).map(UserDto::new);
  }

  public List<UserDto> findAll() {
    return userRepository.findAll().stream().map(UserDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Созраняем полькователя.
   */
  public void save(UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setEmail(userDto.getEmail());
    user.setLogin(userDto.getLogin());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setAuthority(userDto.getAuthority());
  }

  public Optional<User> findByLogin(String login) {
    return userRepository.findByLogin(login);
  }
}
