/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abdelrahman
 */
@Service
public class SecurityServiceImp  implements SecurityService{

     @Autowired
    private AuthenticationManager authmanager;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImp.class);
    @Override
    public String findLoggedInUsername() {
         Object userdetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
       
         
          if (userdetails instanceof UserDetails) {
            return ((UserDetails)userdetails).getUsername();
        }
      
        return null;
    }

    @Override
    public void autologin(String username, String password) {
        
         UserDetails userdetails = userDetailsService.loadUserByUsername(username);
         
  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                                    (userdetails, password, userdetails.getAuthorities());
    authmanager.authenticate(usernamePasswordAuthenticationToken);
    
    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }

        
        
    }
    
}
