package com.adrian.citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.adrian.citas", "com.adrian.commons"})
@EnableFeignClients(basePackages = "com.adrian.commons.clients")
public class MsvCitasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvCitasApplication.class, args);
    }
}