package com.keenor.hulaassistant.controller;


import com.keenor.hulaassistant.service.FieldTask;
import com.sun.org.apache.xpath.internal.operations.Bool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    FieldTask fieldTask;

    @GetMapping("/go")
    public Boolean startJob() {
        return fieldTask.startJob();
    }

    @GetMapping("/stop")
    public Boolean stopJob() {
        return fieldTask.stopJob();
    }

}
