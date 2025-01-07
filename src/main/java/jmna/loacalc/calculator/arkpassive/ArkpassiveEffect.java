package jmna.loacalc.calculator.arkpassive;

import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;
import lombok.Data;

@Data
public class ArkpassiveEffect {
    ArkpassiveEvolutionEffect evolutionEffect;
    ArkpassiveEnlightenmentEffect enlightenmentEffect;

    public ArkpassiveEffect(ArkpassiveEvolutionEffect evolutionEffect, ArkpassiveEnlightenmentEffect enlightenmentEffect) {
        this.evolutionEffect = evolutionEffect;
        this.enlightenmentEffect = enlightenmentEffect;
    }
}
