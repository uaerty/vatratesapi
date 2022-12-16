package com.country.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
This class is the starting point of the application. It displays three EU countries with highest standard rates and
lowest standard rate. Right Click and Run the class to execute the application
 */
@ComponentScan(basePackages = { "com.country"} )
@SpringBootApplication
public class VatRatesApplication {

    public static void main(String[] args) {

        SpringApplication.run(VatRatesApplication.class, args);
        System.out.println("This is testing");
    }

}
