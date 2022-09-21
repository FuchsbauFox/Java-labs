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
  public DirectExchange ownersDirectExchange() {
    return new DirectExchange("owners-exchange");
  }

  @Bean
  public Queue saveOwner() {
    return new Queue("saveOwnerQueue");
  }

  @Bean
  public Queue findOwnerById() {
    return new Queue("findOwnerByIdQueue");
  }

  @Bean
  public Queue addCat() {
    return new Queue("addCatQueue");
  }

  @Bean
  public Binding bindSave() {
    return BindingBuilder.bind(saveOwner()).to(ownersDirectExchange()).with("saveOwner");
  }

  @Bean
  public Binding bindFindOwnerById() {
    return BindingBuilder.bind(findOwnerById()).to(ownersDirectExchange()).with("findOwnerById");
  }

  @Bean
  public Binding bindAddCat() {
    return BindingBuilder.bind(addCat()).to(ownersDirectExchange()).with("addCat");
  }
}
