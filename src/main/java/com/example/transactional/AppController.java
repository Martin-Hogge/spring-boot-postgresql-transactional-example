package com.example.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private MyTestService testService;

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        testService.test();
        return ResponseEntity.ok().build();
    }
}
