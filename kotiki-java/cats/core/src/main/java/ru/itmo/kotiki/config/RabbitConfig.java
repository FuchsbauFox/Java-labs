package ru.itmo.kotiki.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {



  @Bean
  public Queue saveCat() {
    return new Queue("saveCatQueue");
  }

  @Bean
  public Queue deleteCatById() {
    return new Queue("deleteCatByIdQueue");
  }

  @Bean
  public Queue findCatById() {
    return new Queue("findCatByIdQueue");
  }

  @Bean
  public Queue findAllCatsByOwner() {
    return new Queue("findAllCatsByOwnerQueue");
  }
}
