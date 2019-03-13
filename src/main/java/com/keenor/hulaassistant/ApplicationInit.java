package com.keenor.hulaassistant;

import com.keenor.hulaassistant.service.FieldTask;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class ApplicationInit implements CommandLineRunner {

    @Resource
    FieldTask fieldTask;

    @Override
    public void run(String... args) {
        fieldTask.fieldJob();
    }
}
