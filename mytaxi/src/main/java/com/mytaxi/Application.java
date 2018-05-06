package com.mytaxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main class which starts the Spring Boot
 * 
 * @author jeraldfdo
 */

@EnableSwagger2
@SpringBootApplication
public class Application
{

    public static void main(String... args)
    {
        SpringApplication.run(Application.class, args);
    }

}
