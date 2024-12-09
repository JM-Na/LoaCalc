package jmna.loacalc.calculator.hone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class T4ArmorHoneTest {

    @Test
    void of() {
        T4ArmorHone target = T4ArmorHone.of("머리 방어구", 21);

        String s = target.toString();

        System.out.println("s = " + s);
    }

    @Test
    void findIncrementByTargetLevel() {
        T4ArmorIncrement target = T4ArmorHone.findIncrementByTargetLevel("머리 방어구", 21);

        System.out.println("target = " + target);
    }

    @Test
    void findCostByTargetLevel() {
        double cost = T4ArmorHone.findCostByTargetLevel("머리 방어구", 21, true);

        System.out.println("cost = " + cost);
    }
}