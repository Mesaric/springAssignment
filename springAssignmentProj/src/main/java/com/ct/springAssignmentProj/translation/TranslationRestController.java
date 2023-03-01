package com.ct.springAssignmentProj.translation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TranslationRestController {
    private  final TranslationService translationService;

    @GetMapping(value = "hello-rest")
    public String feature01(@RequestParam(name="language", required=false, defaultValue="EN")
                            String language){
        return translationService.getText(language);
    }
    @GetMapping(value = "secure/hello")
    public String feature05(@RequestParam(name="language", required=false, defaultValue="EN")
                            String language){
        return translationService.getText(language);
    }
}
