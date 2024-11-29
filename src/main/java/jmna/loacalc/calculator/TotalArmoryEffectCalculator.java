package jmna.loacalc.calculator;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEvolutionEffect;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.processor.armory.CharacterProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Math.min;

@Component
@RequiredArgsConstructor
public class TotalArmoryEffectCalculator {

    private final StatEffectCalculator statEffectCalculator;

    public TotalArmoryEffect calculateTotalArmoryEffect(ArmoryEffect armoryEffect, ElixirEffect elixirEffect,
                                                        TranscendenceEffect transcendenceEffect, EngravingEffect engravingEffect,
                                                        AccessoryEffect accessoryEffect) {

        TotalArmoryEffect totalArmoryEffect = new TotalArmoryEffect();
        totalArmoryEffect.merge(armoryEffect, elixirEffect, transcendenceEffect, engravingEffect, accessoryEffect);

        return totalArmoryEffect;
    }

    public void calculateTotalCritRate(CharacterProfile characterProfile, TotalArmoryEffect totalArmoryEffect,
                                       ArkpassiveEvolutionEffect arkpassiveEvolutionEffect) {
        double critByStat = statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
        double critRate = totalArmoryEffect.getCritRate();
        double critRateByArkpassive = arkpassiveEvolutionEffect.getCritRate();

        // 치명타 확률 총합
        double totalCrit = critByStat + critRate + critRateByArkpassive;
    }

    public void calculateTotalEvolutionDmg(ArkpassiveEvolutionEffect arkpassiveEvolutionEffect, Double totalCritRate, int manaCost, Double moveSpeed, Double atkSpeed) {

        double evolutionDmg = arkpassiveEvolutionEffect.getEvolutionDmg();
        double manaEvolutionDmg = arkpassiveEvolutionEffect.getManaEvolutionDmg();
        double totalEvolutionDmg = evolutionDmg + manaEvolutionDmg;

        int bluntSpike = arkpassiveEvolutionEffect.getBluntSpike();
        int manaForge = arkpassiveEvolutionEffect.getManaForge();
        int sonicBreakThrough = arkpassiveEvolutionEffect.getSonicBreakThrough();
        // 뭉툭한 가시 노드 사용 시
        if (bluntSpike > 0) {
            if (bluntSpike == 1)
                totalEvolutionDmg += min(((totalCritRate - 80) * 1.2 + 7.5), 50);
            else if (bluntSpike == 2)
                totalEvolutionDmg += min(((totalCritRate - 80) * 1.4 + 15), 70);
        } 
        // 마나 용광로 노드 사용 시
        else if (manaForge > 0) {
            if (manaForge == 1)
                totalEvolutionDmg += min((manaCost / 10.0 * 0.25), 12);
            else if (manaForge == 2)
                totalEvolutionDmg += min((manaCost / 10.0 * 0.5), 24);
        } 
        // 음속 돌파 노드 사용 시
        else if (sonicBreakThrough > 0) {
            if (sonicBreakThrough == 1) {
                double increment = (moveSpeed + atkSpeed - 200) * 0.05;
                if (moveSpeed > 140 && atkSpeed > 140)
                    increment += 4 + (moveSpeed + atkSpeed - 280) * 0.1;
                totalEvolutionDmg += min(increment, 12);
            } else if (sonicBreakThrough == 2) {
                double increment = (moveSpeed + atkSpeed - 200) * 0.1;
                if (moveSpeed > 140 && atkSpeed > 140)
                    increment += 8 + (moveSpeed + atkSpeed - 280) * 0.2;
                totalEvolutionDmg += min(increment, 24);
            }
        }
    }
}
