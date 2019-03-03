package com.keenor.hulaassistant.pojo.req;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Height {
    private String height;
    @SerializedName("line-height")
    private String lineHeight;
    private String width;
}

