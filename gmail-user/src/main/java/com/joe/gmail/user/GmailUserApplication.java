package com.joe.gmail.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.joe.gmail.user.mapper")
public class GmailUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailUserApplication.class, args);
    }

}
