package com.adrian.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.adrian.pacientes", "com.adrian.commons"})
@EnableFeignClients(basePackages = "com.adrian.commons.clients")
public class MsvPacientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvPacientesApplication.class, args);
    }
}