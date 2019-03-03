package com.keenor.hulaassistant.pojo.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Field {

    private int order;
    private int or;
    private boolean flag;
    private boolean isOpen;
    private String _id;
    private String name;
    private String unit;
    private boolean isChoose;
    private int p;
    private Height height;

}
