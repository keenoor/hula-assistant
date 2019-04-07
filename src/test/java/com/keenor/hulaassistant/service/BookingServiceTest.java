package com.keenor.hulaassistant.service;

import com.google.gson.JsonElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Resource
    BookingService bookingService;
    @Resource
    VoteService voteService;

    @Test
    public void order() {
        int code = bookingService.order(12);
        System.out.println(code);
    }

    @Test
    public void voteInfo() {
        int count = voteService.statistic();
        log.info(count + " +++");
    }

}

