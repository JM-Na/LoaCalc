package jmna.loacalc.controller;

import jmna.loacalc.calculator.*;
import jmna.loacalc.calculator.specup.*;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
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
        TestDto dto = testService.test(characterName);

        CharacterEquipment characterEquipment = dto.getCharacterEquipment();
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        model.addAttribute("baseArmories", baseArmories);

        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        List<Accessory> accessoryList = subEquipments.stream().filter(value -> value.getClass().equals(Accessory.class)).map(value -> (Accessory) value).toList();
        model.addAttribute("accessoryList", accessoryList);

        CharacterProfile characterProfile = dto.getCharacterProfile();
        model.addAttribute("characterProfile", characterProfile);

        TotalArmoryEffect totalArmoryEffect = dto.getTotalArmoryEffect();
        model.addAttribute("totalArmoryEffect", totalArmoryEffect);

        // ------------------------------------------------------------------------------------------------------------
        SpecUpDto specUpDto = dto.getSpecUpDto();
        List<HoneSpecUp> armorSpecUp = specUpDto.getArmorSpecUp().stream().filter(value -> value.getExpectedSpecUp() > 0).toList();
        model.addAttribute("armorSpecUp", armorSpecUp);

        List<HoneSpecUp> weaponSpecUp = specUpDto.getWeaponSpecUp().stream().filter(value -> value.getExpectedSpecUp() > 0).toList();
        model.addAttribute("weaponSpecUp", weaponSpecUp);

        List<AccessorySpecUp> accessorySpecUpList = specUpDto.getAccessorySpecUpList();
        model.addAttribute("accessorySpecUpList", accessorySpecUpList);

        List<EngravingSpecUp> engravingSpecUpList = specUpDto.getEngravingSpecUpList().stream().filter(value -> value.getExpectedSpecUp() > 0).toList();
        model.addAttribute("engravingSpecUpList", engravingSpecUpList);

        List<GemSpecUp> gemSpecUpList = specUpDto.getGemSpecUpList();
        model.addAttribute("gemSpecUpList", gemSpecUpList);

        // ---------------------------------------------------------------------------------------------
        List<AccessorySpecUp> necklaceSpecUp = accessorySpecUpList.stream().filter(value -> value.getSeq() == 0).toList();
        List<AccessorySpecUp> earring1SpecUp = accessorySpecUpList.stream().filter(value -> value.getSeq() == 1).toList();
        List<AccessorySpecUp> earring2SpecUp = accessorySpecUpList.stream().filter(value -> value.getSeq() == 2).toList();
        List<AccessorySpecUp> ring1SpecUp = accessorySpecUpList.stream().filter(value -> value.getSeq() == 3).toList();
        List<AccessorySpecUp> ring2SpecUp = accessorySpecUpList.stream().filter(value -> value.getSeq() == 4).toList();

        return "main";
    }
}
