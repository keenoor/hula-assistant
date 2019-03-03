package com.keenor.hulaassistant.pojo.resp;

import java.util.List;

import lombok.Data;

@Data
public class OrderListsBean {
    /**
     * __v : 0
     * _field : 59cded386f1d65ba08316193
     * _id : 5c6b4f187bf0451811660d87
     * _venue : 59cc969742fa6b6703843bbe
     * code : 160254
     * cost : 108
     * createDate : 2019-02-19T00:34:30.426Z
     * description : 2019年2月26日老年体育活动中心羽毛球11 18:00--20:00
     * endDate : 2019-02-26T12:00:00.000Z
     * endHours : 20
     * fieldName : 羽毛球11
     * isCancel : false
     * levelDes : 白银会员
     * name : 姚志英
     * openDate : 2019-02-26T10:29:54.823Z
     * orderDateNum : 20190226
     * payment : member
     * remark :
     * showDate : 18:00--20:00
     * startDate : 2019-02-26T10:00:00.000Z
     * startHours : 18
     * venueName : 老年体育活动中心
     * workerName : 微信
     * openInvoice : false
     * isUsed : true
     * itemNames : ["羽毛球"]
     * items : ["59cc95c342fa6b6703843bb9"]
     */

    private int __v;
    private String _field;
    private String _id;
    private String _venue;
    private String code;
    private int cost;
    private String createDate;
    private String description;
    private String endDate;
    private int endHours;
    private String fieldName;
    private boolean isCancel;
    private String levelDes;
    private String name;
    private String openDate;
    private int orderDateNum;
    private String payment;
    private String remark;
    private String showDate;
    private String startDate;
    private int startHours;
    private String venueName;
    private String workerName;
    private boolean openInvoice;
    private boolean isUsed;
    private List<String> itemNames;
    private List<String> items;

}
