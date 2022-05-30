package ru.itmo.kotiki.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    String authoritiesByUsernameQuery = "select username, role from users " +
        "inner join user_roles on users.user_id = user_roles.user_id " +
        "inner join roles on roles.role_id = user_roles.role_id " +
        "where username = ?";

    auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from users where username=?")
        .authoritiesByUsernameQuery(authoritiesByUsernameQuery);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/myCat/**").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout().permitAll();
  }
}