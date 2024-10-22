package jmna.loacalc.calculator;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Math.sqrt;

@Component
@RequiredArgsConstructor
public class AttackPowerCalculator {

    private final MainStatCalculator mainStatCalculator;
    private final WeaponPowerCalculator weaponPowerCalculator;

    public void calculateBasicAttackPower(String characterName) {
        Double mainStat = (double) mainStatCalculator.calculateMainStat(characterName);
        Double weaponPower = (double) weaponPowerCalculator.calculateTotalWeaponPower(characterName);

        int basicAttackPower = (int) sqrt((mainStat * weaponPower) / 6.0);
        System.out.println("basicAttackPower = " + basicAttackPower);
    }

    public void calculateBasicAttackPowerIncreaseByGem(String characterName) {

    }

}
