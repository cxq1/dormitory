package com.zjnu.dormitory.dormitory.controller;

import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("")
public class Index {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
