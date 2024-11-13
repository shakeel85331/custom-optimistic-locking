package com.shaan.customoptimisticlocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CustomOptimisticLockingApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomOptimisticLockingApplication.class, args);
  }

}
