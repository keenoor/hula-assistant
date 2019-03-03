package com.keenor.hulaassistant.pojo.resp;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyOrderResp {

    private Integer count;
    private List<OrderListsBean> orderLists;

}
