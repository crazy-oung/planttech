package com.planttech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.planttech.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    
    @Autowired UserService userService;
    
	@SuppressWarnings("deprecation")
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//        .requestMatchers("/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
        .requestMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginProcessingUrl("/user/login/check"); //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
//        .defaultSuccessUrl("/");
        
   
        http.csrf().disable();
        return http.build();
    }

    
    @Bean	
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    


}