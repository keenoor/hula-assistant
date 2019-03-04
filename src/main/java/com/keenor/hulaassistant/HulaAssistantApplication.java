package com.keenor.hulaassistant;

import com.keenor.hulaassistant.service.BookingService;
import com.keenor.hulaassistant.service.FieldTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
public class HulaAssistantApplication {


    public static void main(String[] args) {
        SpringApplication.run(HulaAssistantApplication.class, args);

    }

}
