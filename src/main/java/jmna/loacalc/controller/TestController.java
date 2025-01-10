package jmna.loacalc.controller;

import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

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

        List<String> testList = List.of("A", "B", "C", "D");
        model.addAttribute("testList", testList);

        CharacterEquipment characterEquipment = testService.test(characterName);
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        model.addAttribute("baseArmories", baseArmories);


        return "main";
    }
}
