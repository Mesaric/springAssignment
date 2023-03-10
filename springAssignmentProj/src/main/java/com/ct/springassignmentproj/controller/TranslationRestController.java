package com.ct.springassignmentproj.controller;

import com.ct.springassignmentproj.service.TranslationService;
import com.ct.springassignmentproj.service.TranslationServiceExternal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TranslationRestController {
    private  final TranslationService translationService;
    private  final TranslationServiceExternal translationServiceExternal;


    @GetMapping(value = "hello-rest")
    public String feature01(@RequestParam(name="language", required=false, defaultValue="en")
                            String language){
        return translationService.getText(language);
    }
    @GetMapping(value = "secure/hello")
    public String feature05(@RequestParam(name="language", required=false, defaultValue="en")
                            String language){
        return translationService.getText(language);
    }

    @GetMapping(value = "external/hello")
    public String feature09(@RequestParam(name="language", required=false, defaultValue="en")
                            String language){
        return translationServiceExternal.getText(language);
    }


}
