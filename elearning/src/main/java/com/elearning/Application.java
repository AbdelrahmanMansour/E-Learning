/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
//extends SpringBootServletInitializer

/**
 *
 * @author Abdelrahman
 */
@SpringBootApplication
public class Application  {

    /**
     * @param args the command line arguments
     */
    
//     @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
