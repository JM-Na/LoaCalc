package jmna.loacalc.feign.client.armories;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ArmoryClient", url = "${smilegate.url}" + "/armories/characters")
public interface ArmoryClient {

    //    @GetMapping("/{characterName}")
    @GetMapping("/{characterName}/profiles")
    ArmoryProfiles getArmoryProfiles(@PathVariable String characterName);

    @GetMapping("/{characterName}/equipment")
    List<ArmoryEquipment> getArmoryEquipment(@PathVariable String characterName);

    @GetMapping("/{characterName}/avatars")
    List<ArmoryAvatar> getArmoryAvatar(@PathVariable String characterName);

    @GetMapping("/{characterName}/combat-skills")
    List<ArmoryCombatSkill> getArmoryCombatSkills(@PathVariable String characterName);

    @GetMapping("/{characterName}/engravings")
    ArmoryEngravings getArmoryEngravings(@PathVariable String characterName);

    @GetMapping("/{characterName}/cards")
    ArmoryCards getArmoryCards(@PathVariable String characterName);

    @GetMapping("/{characterName}/gems")
    ArmoryGems getArmoryGems(@PathVariable String characterName);

    @GetMapping("/{characterName}/colosseums")
    ArmoryColosseums getArmoryColosseums(@PathVariable String characterName);

    @GetMapping("/{characterName}/collectibles")
    List<ArmoryCollectible> getArmoryCollectibles(@PathVariable String characterName);

    @GetMapping("/{characterName}//arkpassive")
    ArmoryArkPassive getArmoryArkPassive(@PathVariable String characterName);
}
