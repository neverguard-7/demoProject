package com.example.demo.sendMessage;


import com.example.demo.wechatMessage.aes.Program;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

//@Configuration
//@EnableScheduling
public class TimeTask {
    @Scheduled(cron = "0/5 * * * * ?")
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        new SendSubMessage().subscribeMessage();
        System.err.println(LocalDateTime.now());
    }
}
