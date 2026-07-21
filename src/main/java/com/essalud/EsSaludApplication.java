package com.essalud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// Le decimos a Spring Boot que NO intente conectarse a una base de datos todavía
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class EsSaludApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsSaludApplication.class, args);
    }
}