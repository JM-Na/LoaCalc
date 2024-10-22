package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArkpassiveProcessor {

    private final ArmoryClient armoryClient;

    public ArmoryArkPassive getArkpassiveData(String characterName) {

        return armoryClient.getArmoryArkPassive(characterName);
    }

}
