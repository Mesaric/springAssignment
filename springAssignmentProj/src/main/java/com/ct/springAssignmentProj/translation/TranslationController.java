package com.ct.springAssignmentProj.translation;

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
    public String feature02(@RequestParam(name="language", required=false, defaultValue="EN")
                                String language, Model model){
        model.addAttribute("text", translationService.getText(language));
        model.addAttribute("language", language);
        return "helloWorld";
    }
    @GetMapping(value = "secure/admin")
    public String feature06(Model model){
        model.addAttribute("translation", new Translation());
        model.addAttribute("statusMessage", "");
        return "addTranslation";
    }

    @PostMapping(value = "secure/addTranslation")
    public String addEntry(@ModelAttribute Translation translation, Model model){

        String statusMessage = translationService.addTranslation(translation);

        model.addAttribute("translation", new Translation());
        model.addAttribute("statusMessage", statusMessage);
        return "redirect:/secure/admin";

    }

}
