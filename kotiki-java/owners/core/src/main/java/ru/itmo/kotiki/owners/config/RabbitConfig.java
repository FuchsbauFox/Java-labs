package ru.itmo.kotiki.owners.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public DirectExchange catsDirectExchange() {
    return new DirectExchange("owners-exchange");
  }

  @Bean
  public Queue findOwnerById() {
    return new Queue("findOwnerByIdQueue");
  }

  @Bean
  public Binding bindFindOwnerById() {
    return BindingBuilder.bind(findOwnerById()).to(catsDirectExchange()).with("findOwnerById");
  }
}
