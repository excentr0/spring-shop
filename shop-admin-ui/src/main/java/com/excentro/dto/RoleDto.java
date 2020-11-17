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
public class RoleDto {
  private Long id;
  private String authority;
  private Set<User> users;

  public RoleDto(Role role) {
    this.id = role.getId();
    this.authority = role.getAuthority();
    this.users = role.getUsers();
  }
}
