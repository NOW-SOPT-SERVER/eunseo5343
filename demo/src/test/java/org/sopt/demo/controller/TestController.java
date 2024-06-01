package org.sopt.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test(){
        return "test Api";
    }
//    @GetMapping("/test/json")
//    public ApiResponse testJson() {
//        return ApiResponse.create("1차 세미나 테스트 API - JSON");
//    }
}



