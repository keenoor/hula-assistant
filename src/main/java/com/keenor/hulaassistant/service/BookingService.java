package com.keenor.hulaassistant.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.GsonUtil;
import com.keenor.hulaassistant.constants.Consts;
import com.keenor.hulaassistant.constants.TestJson;
import com.keenor.hulaassistant.handler.ReqHandler;
import com.keenor.hulaassistant.pojo.req.OrderReq;
import com.keenor.hulaassistant.pojo.vo.MyOrderVo;
import com.keenor.hulaassistant.util.BizUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {

    @Resource
    ReqHandler reqHandler;

    public List<MyOrderVo> getMyOrders() {

        List<MyOrderVo> orderVoList = Lists.newArrayList();
        reqHandler.getOrder().getOrderLists()
                .forEach(i->{
                    MyOrderVo vo = new MyOrderVo();
                    BeanUtils.copyProperties(i, vo);
                    orderVoList.add(vo);
                });

        return orderVoList;
    }

    public JsonElement order(){
        Long nowTime = reqHandler.getNowTime();
        String randomStr = BizUtils.getRandomStr();
        String sign = BizUtils.getSign(Consts.MEMBER, nowTime, randomStr);

        OrderReq req = GsonUtil.parseObj(TestJson.postJson, OrderReq.class);
        req.setNowStr(nowTime);
        req.setRandomStr(randomStr);
        req.setSign(sign);
        JsonElement order = reqHandler.order(GsonUtil.toJson(req));
        return order;
    }

}
