package com.mytaxi.repository;

import com.mytaxi.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import javax.transaction.Transactional;

/**
 * @author jeraldfdo
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = AbstractDaoTest.IntegrationTest.class)
public class AbstractDaoTest {

    @Configuration
    @EntityScan(basePackages = {"com.mytaxi.domain"})
    @EnableAutoConfiguration(exclude = {
        WebMvcAutoConfiguration.class
    })
    protected static class IntegrationTest {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }

}
