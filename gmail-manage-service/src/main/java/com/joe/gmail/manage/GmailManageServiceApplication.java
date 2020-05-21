package com.joe.gmail.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.joe.gmail.manage.mapper")
public class GmailManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailManageServiceApplication.class, args);
    }

}
