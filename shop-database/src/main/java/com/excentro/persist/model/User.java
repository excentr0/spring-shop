package com.excentro.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
  private static final long serialVersionUID = -4056818542527772926L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String login;
  private String password;
  private boolean enabled;
  private String email;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_authority",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id"))
  private Set<Role> authority = new HashSet<>();

  public User(String login, String password) {
    this.login = login;
    this.password = password;
    this.enabled = true;
  }

  public void addRole(Role role) {
    authority.add(role);
    role.getUsers().add(this);
  }

  public String getRole() {
    return authority.iterator().next().toString();
  }
}
