package ru.itmo.kotiki.cats.config;

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
    return new DirectExchange("cats-exchange");
  }

  @Bean
  public Queue saveCat() {
    return new Queue("saveCatQueue");
  }

  @Bean
  public Queue deleteCatById() {
    return new Queue("deleteByIdQueue");
  }

  @Bean
  public Queue findCatById() {
    return new Queue("findByIdQueue");
  }

  @Bean
  public Queue findAllCatsByOwner() {
    return new Queue("findAllByOwnerQueue");
  }

  @Bean
  public Binding bindSaveCat() {
    return BindingBuilder.bind(saveCat()).to(catsDirectExchange()).with("saveCat");
  }

  @Bean
  public Binding bindDeleteCatById() {
    return BindingBuilder.bind(deleteCatById()).to(catsDirectExchange()).with("deleteCatById");
  }

  @Bean
  public Binding bindFindCatById() {
    return BindingBuilder.bind(findCatById()).to(catsDirectExchange()).with("findCatById");
  }

  @Bean
  public Binding bindFindAllCatsByOwner() {
    return BindingBuilder.bind(findAllCatsByOwner()).to(catsDirectExchange()).with("findAllCatsByOwner");
  }
}
