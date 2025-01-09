package jmna.loacalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("query");
        return "search-result";
    }

    @GetMapping("/search")
    public String search(@RequestParam String characterName, Model model) {
        System.out.println("query = " + characterName);
        model.addAttribute("characterName", characterName);
        return "main";
    }
}
