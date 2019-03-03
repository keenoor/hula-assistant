package com.keenor.hulaassistant.pojo.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Price {
    private String _id;
    private int price;
    private String unit;
    private int week;
}
