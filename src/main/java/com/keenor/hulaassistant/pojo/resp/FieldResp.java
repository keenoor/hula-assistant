package com.keenor.hulaassistant.pojo.resp;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FieldResp {

    private int count;
    private List<RawField> fields;

}
