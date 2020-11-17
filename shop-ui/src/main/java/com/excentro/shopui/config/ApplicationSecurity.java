package com.excentro.shopui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(10)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
  // Разрешаем все
  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/**");
  }
}
