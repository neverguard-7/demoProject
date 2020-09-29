package com.example.demo.wechatMessage.aes;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

//@Configuration
//@EnableScheduling
public class TimeTask {
    @Scheduled(cron = "0/20 * * * * ?")
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

        new Program().subscribeMessage();
        System.err.println(LocalDateTime.now());
    }
}
