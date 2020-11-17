package com.excentro.security;

import com.excentro.persist.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String ADMIN_ROLE = "ADMIN";

  @Autowired
  public void authConfigure(
      AuthenticationManagerBuilder auth,
      @Qualifier("userAuthService") UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder)
      throws Exception {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    auth.authenticationProvider(daoAuthenticationProvider);
    auth.inMemoryAuthentication().withUser("mike")
        .password(passwordEncoder.encode("password")).roles(ADMIN_ROLE);
  }

  @Bean(name = "userAuthService")
  public UserDetailsService userAuthService(UserRepository userRepository) {
    return new UserAuthService(userRepository);
  }

  @Configuration
  @Order(1)
  public static class UiWebSecurityConfigurationAdapter
      extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/").hasRole(ADMIN_ROLE).and().formLogin()
          .loginPage("/login").failureUrl("/login-error").and().logout()
          .logoutSuccessUrl("/");
    }
  }
}
