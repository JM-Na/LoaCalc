package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import org.springframework.stereotype.Component;

@Component
public class TotalEffectCalculator {

    public TotalEffect calculateTotalEffect(ArmoryEffect armoryEffect, ElixirEffect elixirEffect,
                                            TranscendenceEffect transcendenceEffect, EngravingEffect engravingEffect,
                                            AccessoryEffect accessoryEffect) {

        TotalEffect totalEffect = new TotalEffect();
        totalEffect.merge(armoryEffect, elixirEffect, transcendenceEffect, engravingEffect, accessoryEffect);

        return totalEffect;
    }
}
