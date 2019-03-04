package com.keenor.hulaassistant.pojo.req;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Time {

    private String _id;
    private int __v;
    private String _item;
    private String _organization;
    private String _venue;
    private String close_endDate;
    private String close_startDate;
    private int continueHours;
    private String createDate;
    private int endDate;
    private int endDateHours;
    private int endDateMins;
    private String showDate;
    private int startDate;
    private int startDateHours;
    private int startDateMins;
    private boolean isUsed;
    private String st;
    private String et;
    private List<Price> priceArr;
    private List<Field> fields;

}
