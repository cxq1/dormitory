package com.zjnu.dormitory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
@SpringBootApplication
public class DormitoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryApplication.class, args);
    }

}
