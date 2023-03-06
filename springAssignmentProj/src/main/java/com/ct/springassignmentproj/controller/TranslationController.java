package com.ct.springassignmentproj.controller;

import com.ct.springassignmentproj.model.Translation;
import com.ct.springassignmentproj.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TranslationController {
    private  final TranslationService translationService;
    @GetMapping(value = "hello")
    public String feature02(@RequestParam(name="language", required=false, defaultValue="en")
                                String language, Model model){
        model.addAttribute("text", translationService.getText(language));
        model.addAttribute("language", language);
        return "helloWorld";
    }
    @GetMapping(value = "secure/admin")
    public String feature06(Model model){
        model.addAttribute("translation", new Translation());
        return "addTranslation";
    }

    @PostMapping(value = "secure/addTranslation")
    public String feature06Add(@ModelAttribute Translation translation, Model model){
        translationService.addTranslation(translation);
        model.addAttribute("translation", new Translation());
        return "redirect:/secure/admin";

    }

}
