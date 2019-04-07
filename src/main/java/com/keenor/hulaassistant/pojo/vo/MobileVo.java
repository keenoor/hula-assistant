package com.keenor.hulaassistant.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MobileVo {

    private int error_code;
    private String reason;
    private Result result;

    @NoArgsConstructor
    @Data
    public static class Result {
        /**
         * mobile : 17602171031
         * status : 1
         * area : 上海-上海
         * numberType : 联通176卡
         */

        private String mobile;
        private String status;
        private String area;
        private String numberType;
    }
}
