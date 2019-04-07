package com.keenor.hulaassistant.service;

import com.google.gson.JsonElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Resource
    BookingService bookingService;

    @Test
    public void order() {
        int code = bookingService.order(12);
        System.out.println(code);
    }

    @Test
    public void order2() {
        long orderDate = LocalDateTime.now()
                .plus(7, ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(orderDate);
    }

}