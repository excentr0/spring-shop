package com.excentro.shopui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.excentro")
public class ShopUiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopUiApplication.class, args);
  }
}
