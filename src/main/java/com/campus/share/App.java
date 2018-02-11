package com.campus.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.campus.share.dao")
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }

}
