package ru.fonzy.fnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.fonzy.fnotes"})
public class FnotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FnotesApplication.class, args);
    }
}
