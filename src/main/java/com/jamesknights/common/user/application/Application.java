/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package com.jamesknights.common.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.jamesknights.common.user.repository")
@ComponentScan(basePackages = { "com.jamesknights.*"})
@EntityScan("com.jamesknights.*") 
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
*/