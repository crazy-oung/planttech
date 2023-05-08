package com.planttech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.planttech.domain.User;
import com.planttech.service.UserService;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	System.out.println("::: AuthProvider.authenticate :::");
    	
    	UsernamePasswordAuthenticationToken token;
    	System.out.println(authentication.toString());
    	PasswordEncoder passwordEncoder = userService.passwordEncoder();    
        String userId 					= (String) authentication.getPrincipal(); 
        String userPw 					= (String) authentication.getCredentials(); 
        User user 						= userService.getUserByUserId(userId);
        
        if(user != null) System.out.println(user);
        else 			 System.out.println("user null");
        System.out.println(userId + " " + userPw);


        if (user != null && passwordEncoder.matches(userPw, user.getUserPw())) { 
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("USER")); // 권한 부여

            token = new UsernamePasswordAuthenticationToken(user.getUserId(), null, roles); 
            // 인증된 user 정보를 담아 SecurityContextHolder에 저장되는 token

            return token;
        }

        throw new BadCredentialsException("No such user or wrong password."); 
        // Exception을 던지지 않고 다른 값을 반환하면 authenticate() 메서드는 정상적으로 실행된 것이므로 인증되지 않았다면 Exception을 throw 해야 한다.
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}