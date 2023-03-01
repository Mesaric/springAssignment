package com.ct.springAssignmentProj.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {
    @GetMapping(value = "hello-rest")
    public String feature01(){
        return new String("Hello World");
    }
}
