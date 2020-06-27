package com.keenor.hulaassistant.service;

import com.google.gson.JsonElement;
import com.keenoor.toolkit.utils.GsonUtil;
import com.keenor.hulaassistant.pojo.vo.MyOrderVo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    public void myOrders() {
        List<MyOrderVo> myOrders = bookingService.getMyOrders();
        System.out.println(GsonUtil.toJson(myOrders));
    }

    @Test
    public void order2() {
        long orderDate = LocalDateTime.now()
                .plus(7, ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(orderDate);
    }

}