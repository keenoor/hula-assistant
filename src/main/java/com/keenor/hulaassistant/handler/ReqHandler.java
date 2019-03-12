package com.keenor.hulaassistant.handler;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.httpclient.HttpClientUtil;
import com.keenoor.toolkit.utils.httpclient.HttpCodeException;
import com.keenor.hulaassistant.config.BizException;
import com.keenor.hulaassistant.config.ResponseCode;
import com.keenor.hulaassistant.constants.Consts;
import com.keenor.hulaassistant.pojo.base.OldResult;
import com.keenor.hulaassistant.pojo.resp.FieldResp;
import com.keenor.hulaassistant.pojo.resp.MyOrderResp;
import com.keenor.hulaassistant.pojo.resp.TimeResp;
import com.keenor.hulaassistant.service.BookingService;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import static com.keenor.hulaassistant.constants.HulaUrls.API_FIELDS;
import static com.keenor.hulaassistant.constants.HulaUrls.API_ORDERLISTS;
import static com.keenor.hulaassistant.constants.HulaUrls.API_ORDERLISTS_ORDERS;
import static com.keenor.hulaassistant.constants.HulaUrls.API_TIMES;
import static com.keenor.hulaassistant.constants.HulaUrls.API_TIMES_TODAY;
import static com.keenor.hulaassistant.constants.HulaUrls.HOST;

@Slf4j
@Component
public class ReqHandler {

    public MyOrderResp getOrder() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("_org", Consts.ORG);
        map.put("_member", Consts.MEMBER);

        OldResult<MyOrderResp> result;
        try {
            result = HttpClientUtil
                    .<OldResult<MyOrderResp>>get(HOST + API_ORDERLISTS)
                    .params(map).type(new TypeToken<OldResult<MyOrderResp>>() {
                    })
                    .execute();
        } catch (HttpCodeException e) {
            log.error("--> {}", e.getStatusLine());
            throw new BizException(ResponseCode.ERROR, e);
        }

        return result.getData();
    }

    public Long getNowTime() {
        OldResult<Long> result;
        try {
            result = HttpClientUtil
                    .<OldResult<Long>>get(HOST + API_TIMES_TODAY)
                    .type(new TypeToken<OldResult<Long>>() {
                    })
                    .execute();
        } catch (HttpCodeException e) {
            log.error("--> {}", e.getStatusLine());
            throw new BizException(ResponseCode.ERROR, e);
        }
        return result.getData();
    }

    public FieldResp getFields() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("_org", Consts.ORG);
        map.put("_venue", Consts.VENUE);
        map.put("_item", Consts.ITEM);
        map.put("sortValue", "asc");

        OldResult<FieldResp> result;
        try {
            result = HttpClientUtil
                    .<OldResult<FieldResp>>get(HOST + API_FIELDS)
                    .params(map)
                    .type(new TypeToken<OldResult<FieldResp>>() {
                    })
                    .execute();
        } catch (HttpCodeException e) {
            log.error("--> {}", e.getStatusLine());
            throw new BizException(ResponseCode.ERROR, e);
        }
        return result.getData();
    }

    public TimeResp getTimes() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("_item", Consts.ITEM);
        map.put("_org", Consts.ORG);
        map.put("_venue", Consts.VENUE);
        map.put("orderDate", BookingService.ORDER_DATE);
        map.put("sortBy", "startDate");

        OldResult<TimeResp> result;
        try {
            result = HttpClientUtil
                    .<OldResult<TimeResp>>get(HOST + API_TIMES)
                    .params(map).type(new TypeToken<OldResult<TimeResp>>() {
                    })
                    .execute();
        } catch (HttpCodeException e) {
            log.error("--> {}", e.getStatusLine());
            throw new BizException(ResponseCode.ERROR, e);
        }
        return result.getData();
    }

    public Integer order(String json) {
        OldResult<JsonElement> result;
        try {
            result = HttpClientUtil
                    .<OldResult<JsonElement>>post(HOST + API_ORDERLISTS_ORDERS)
                    .json(json)
                    .type(new TypeToken<OldResult<JsonElement>>() {
                    })
                    .execute();
        } catch (HttpCodeException e) {
            log.error("--> {}", e.getStatusLine());
            throw new BizException(ResponseCode.ERROR, e);
        }
        log.info(result.getData().toString());
        return result.getCode();
    }


}
