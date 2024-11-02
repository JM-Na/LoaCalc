package jmna.loacalc.calculator;

import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.Weapon;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ArmoryEffect {

    private int weaponPower;
    private double addDmg;

    private int mainStat;
    private int phyDefense;
    private int magDefense;
    private int vitality;
    private int vigor;

    public void add(BaseArmory other) {
        if (other.getClass().equals(Armor.class)) {
            this.mainStat += ((Armor) other).getMainStat();
            this.phyDefense += ((Armor) other).getPhyDefense();
            this.magDefense += ((Armor) other).getMagDefense();
            this.vitality += ((Armor) other).getVitality();
            this.vigor += ((Armor) other).getVigor();
        }
        if (other.getClass().equals(Weapon.class)) {
            this.weaponPower += ((Weapon) other).getWeaponPower();
            this.addDmg += ((Weapon) other).getAddDmg();
        }
    }
}
