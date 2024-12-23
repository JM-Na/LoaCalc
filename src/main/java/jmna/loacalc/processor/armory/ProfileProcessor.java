package jmna.loacalc.processor.armory;

import jmna.loacalc.feign.client.armories.ArmoryProfile;
import jmna.loacalc.feign.client.armories.profiles.Stat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileProcessor {

    public CharacterProfile processProfiles(ArmoryProfile armoryProfile) {
        String characterName = armoryProfile.getCharacterName();
        String characterClassName = armoryProfile.getCharacterClassName();
        String characterLevel = armoryProfile.getCharacterLevel();
        String itemMaxLevel = armoryProfile.getItemMaxLevel();
        String characterImage = armoryProfile.getCharacterImage();
        String serverName = armoryProfile.getServerName();

        List<Stat> stats = armoryProfile.getStats();

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
