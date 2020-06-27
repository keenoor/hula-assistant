package com.keenor.hulaassistant.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.GsonUtil;
import com.keenor.hulaassistant.config.ConfigProperties;
import com.keenor.hulaassistant.constants.Consts;
import com.keenor.hulaassistant.constants.TestJson;
import com.keenor.hulaassistant.handler.ReqHandler;
import com.keenor.hulaassistant.pojo.req.Field;
import com.keenor.hulaassistant.pojo.req.OrderReq;
import com.keenor.hulaassistant.pojo.resp.RawField;
import com.keenor.hulaassistant.pojo.resp.RawTime;
import com.keenor.hulaassistant.pojo.vo.MyOrderVo;
import com.keenor.hulaassistant.util.BizUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.LongBinaryOperator;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {

    @Resource
    private ConfigProperties configProperties;
    @Resource
    ReqHandler reqHandler;

    private final static int TIME_1 = 17;
    private final static int TIME_2 = 18;

    public Long getTodayTime() {
        return reqHandler.getNowTime();
    }

    public List<MyOrderVo> getMyOrders() {

        List<MyOrderVo> orderVoList = Lists.newArrayList();
        reqHandler.getOrder().getOrderLists()
                .forEach(i -> {
                    MyOrderVo vo = new MyOrderVo();
                    BeanUtils.copyProperties(i, vo);
                    orderVoList.add(vo);
                });

        return orderVoList;
    }

    public int order(int fieldNum) {
        log.info("ordering number ... {}", fieldNum);

        OrderReq req = new OrderReq();
        req.set_org(Consts.ORG);
        req.set_member(Consts.MEMBER);
        req.setOpenid(Consts.OPEN_ID);
        long orderDate = LocalDateTime.now()
                .plus(7, ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        req.setOrderDate(orderDate);

        Long nowTime = reqHandler.getNowTime();
        String randomStr = BizUtils.getRandomStr();
        String sign = BizUtils.getSign(Consts.MEMBER, nowTime, randomStr);
        req.setNowStr(nowTime);
        req.setRandomStr(randomStr);
        req.setSign(sign);

        RawField rawField = reqHandler.getFields().getFields().get(fieldNum - 1);
        List<RawTime> rawTimeList = reqHandler.getTimes().getTimes();

        OrderReq.OrderItem orderItem1 = new OrderReq.OrderItem();
        OrderReq.OrderItem orderItem2 = new OrderReq.OrderItem();
        orderItem1.set_field(rawField.get_id());
        orderItem1.setFName(rawField.getName());
        orderItem1.setPrice(54);
        BeanUtils.copyProperties(orderItem1, orderItem2);

        RawTime rawTime1 = rawTimeList.get(TIME_1 - 7);
        orderItem1.set_time(rawTime1.get_id());
        orderItem1.setShowDate(rawTime1.getShowDate());
        RawTime rawTime2 = rawTimeList.get(TIME_2 - 7);
        orderItem2.set_time(rawTime2.get_id());
        orderItem2.setShowDate(rawTime2.getShowDate());

        List<OrderReq.OrderItem> orderArr = Lists.newArrayList();
        orderArr.add(orderItem1);
        orderArr.add(orderItem2);
        req.setOrderArr(orderArr);

        int order;
        try {
            order = reqHandler.order(GsonUtil.toJson(req));
        } catch (Exception e) {
            log.error("", e);
            return 430;
        }

        log.info("ordering end ... {}", fieldNum);
        return order;
    }


}
