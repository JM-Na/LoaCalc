package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryProfiles;
import jmna.loacalc.feign.client.armories.profiles.Stat;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProfileProcessor {

    public CharacterProfile processProfiles(ArmoryProfiles armoryProfiles) {
        String characterName = armoryProfiles.getCharacterName();
        String characterClassName = armoryProfiles.getCharacterClassName();
        String characterLevel = armoryProfiles.getCharacterLevel();
        String itemMaxLevel = armoryProfiles.getItemMaxLevel();
        String characterImage = armoryProfiles.getCharacterImage();
        String serverName = armoryProfiles.getServerName();

        List<Stat> stats = armoryProfiles.getStats();
        Map<String, Integer> statValue = new HashMap<>();

        CharacterProfile characterProfile = new CharacterProfile(characterName, characterClassName, characterLevel, itemMaxLevel, characterImage, serverName);

        for (Stat stat : stats) {
            String value = stat.getValue();
            switch (stat.getType()){
                case "치명" -> characterProfile.setCrit(Integer.parseInt(value));
                case "특화" -> characterProfile.setSpecialization(Integer.parseInt(value));
                case "제압" -> characterProfile.setDomination(Integer.parseInt(value));
                case "신속" -> characterProfile.setSwiftness(Integer.parseInt(value));
                case "인내" -> characterProfile.setEndurance(Integer.parseInt(value));
                case "숙련" -> characterProfile.setExpertise(Integer.parseInt(value));
                case "최대 생명력" -> characterProfile.setMaxHP(Integer.parseInt(value));
                case "공격력" -> characterProfile.setAtkPower(Integer.parseInt(value));
            }
        }

        return characterProfile;
    }

}
