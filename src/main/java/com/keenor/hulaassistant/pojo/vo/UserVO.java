package com.keenor.hulaassistant.pojo.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-10 10:50
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

@Getter
public class UserVO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", password='" + "***" + '\'' +
                '}';
    }

}
