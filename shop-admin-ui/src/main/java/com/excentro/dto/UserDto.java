package com.excentro.dto;

import com.excentro.persist.model.Role;
import com.excentro.persist.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String login;
  private String password;
  private boolean enabled;
  private String email;
  private Set<Role> authority;

  public UserDto(User user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.password = user.getPassword();
    this.enabled = user.isEnabled();
    this.email = user.getEmail();
    this.authority = user.getAuthority();
  }
}
