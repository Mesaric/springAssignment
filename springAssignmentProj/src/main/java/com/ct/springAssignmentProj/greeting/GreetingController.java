package com.ct.springAssignmentProj.greeting;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class GreetingController {
    @GetMapping(path = "hello-rest")
    @ResponseBody
    public String feature01() {
        return "Hello World";
    }

    @GetMapping(path = "hello", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String feature02() {
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello World\n" + "</body>\n" + "</html>";
    }

}
