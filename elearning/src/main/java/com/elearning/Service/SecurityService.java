/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Abdelrahman
 */
@Service
public interface SecurityService {
    
    public String findLoggedInUsername();
    void autologin(String username, String password);
    
}
