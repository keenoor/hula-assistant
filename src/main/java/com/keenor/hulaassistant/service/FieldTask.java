package com.keenor.hulaassistant.service;

import com.google.gson.JsonElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FieldTask {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    @Resource
    BookingService bookingService;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private boolean ifSuccess = false;

//    @Scheduled(cron = "0 26 2 ? * 3")
    @Scheduled(cron = "0/5 * * * * *")
    public void fieldJob() {
        log.info("start fieldJob... ");
//        startJob();
    }

    //    @Scheduled(fixedRate = 1000)
    //    public void repeatJob() {
    //        System.out.println("每隔 1 秒钟执行一次： "
    //                + DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date()));
    //    }

    public boolean startJob() {
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/10 * * * * *"));
        log.info("DynamicTask.startJob() ...");
        return future.isDone();
    }

    public boolean stopJob() {
        if (future != null) {
            future.cancel(true);
        }
        log.info("DynamicTask.stopJob() ...");
        return future.isCancelled();
    }


    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            log.info("job run... {}", new Date());

            if (ifSuccess) {
                return;
            }
            int fieldNum = 15;
            for (int i = 0; i < 5; i++) {
                int code = bookingService.order(fieldNum);
                if (code == 200) {
                    stopJob();
                    ifSuccess = true;
                    break;
                } else {
                    bookingService.order(fieldNum - 3);
                }
            }
        }
    }

}
