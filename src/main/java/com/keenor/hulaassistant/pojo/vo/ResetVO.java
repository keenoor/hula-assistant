package com.keenor.hulaassistant.pojo.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-11 20:29
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

@Data
public class ResetVO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
}
