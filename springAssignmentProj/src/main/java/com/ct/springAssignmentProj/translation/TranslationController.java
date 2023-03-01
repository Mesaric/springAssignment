package com.ct.springAssignmentProj.translation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TranslationController {
    private  final TranslationService translationService;
    @GetMapping(value = "hello")
    public String feature02(@RequestParam(name="language", required=false, defaultValue="EN")
                                String language, Model model){
        model.addAttribute("text", translationService.getText(language));
        model.addAttribute("language", language);
        return "helloWorld";
    }
}
