package com.example.springecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringecommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringecommerceApplication.class, args);
    }

}
