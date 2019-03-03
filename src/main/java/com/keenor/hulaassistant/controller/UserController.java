package com.keenor.hulaassistant.controller;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-10 10:44
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */


import com.keenor.hulaassistant.pojo.base.Result;
import com.keenor.hulaassistant.pojo.vo.ResetVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
////    UserService userService;
//
//    @PostMapping("/resetPwd")
//    public Result<Void> resetPwd(@RequestBody ResetVO resetVO) {
//        String errorCode = "";
//        if (errorCode != null) {
//            return Result.ofErrorCode(errorCode);
//        }
//        return Result.ofSuccess();
//    }


}