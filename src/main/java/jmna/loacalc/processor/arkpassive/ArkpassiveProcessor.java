package jmna.loacalc.processor.arkpassive;

import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.arkpassives.Effect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArkpassiveProcessor {

    public List<CharacterArkpassive> processArkpassiveData(ArmoryArkPassive armoryArkPassive) {
        Boolean isArkPassive = armoryArkPassive.getIsArkPassive();
        List<CharacterArkpassive> characterArkpassiveList = new ArrayList<>();
        if (isArkPassive) {
            List<Effect> effects = armoryArkPassive.getEffects();
            for (Effect effect : effects) {
                String description = effect.getDescription();
                List<String> text = htmlTagSlicer(description);
                String type = text.get(0);
                int tier = Integer.parseInt(text.get(1).trim().replace("티어", ""));
                String[] split = text.get(2).split(" Lv.");
                String name = split[0].trim();
                int lvl = Integer.parseInt(split[1].trim());
                String icon = effect.getIcon();
                characterArkpassiveList.add(new CharacterArkpassive(type, tier, name, lvl, icon));
            }
        }
        else {
            System.out.println("아크패시브가 활성화되지 않은 캐릭터입니다.");
            return null;
        }
        return characterArkpassiveList;
    }

    public List<String> htmlTagSlicer(String input) {
        return Arrays.stream(input.split("<[^>]*>"))
                .filter(text -> !text.isEmpty())
                .toList();
    }
}
