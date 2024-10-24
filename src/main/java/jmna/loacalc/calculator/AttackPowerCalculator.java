package jmna.loacalc.calculator;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.processor.GemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Math.sqrt;

@Component
@RequiredArgsConstructor
public class AttackPowerCalculator {

    private final MainStatCalculator mainStatCalculator;
    private final WeaponPowerCalculator weaponPowerCalculator;
    private final GemProcessor gemProcessor;

    public void calculateBasicAttackPower(String characterName) {
        Double mainStat = (double) mainStatCalculator.calculateMainStat(characterName);
        Double weaponPower = (double) weaponPowerCalculator.calculateTotalWeaponPower(characterName);

        int basicAttackPower = (int) sqrt((mainStat * weaponPower) / 6.0);
        System.out.println("basicAttackPower = " + basicAttackPower);
        double basicAttackPower2 = calculateBasicAttackPowerIncreaseByGem(characterName, basicAttackPower);
        System.out.println("basicAttackPower2 = " + basicAttackPower2);
    }

    public double calculateBasicAttackPowerIncreaseByGem(String characterName, int basicAttackPower) {
        double basicWeaponPowerIncrease = gemProcessor.getGemBasicWeaponPowerIncrease(characterName);

        return basicAttackPower * (100 + basicWeaponPowerIncrease) / 100;
    }

}
