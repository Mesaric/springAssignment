package com.ct.springAssignmentProj.translation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationRestController {
    @GetMapping(value = "hello-rest")
    public String feature01(){
        return new String("Hello World");
    }
}
