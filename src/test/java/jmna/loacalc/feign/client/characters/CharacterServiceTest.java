package jmna.loacalc.feign.client.characters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;


    @Test
    void characters() {
        java.lang.Character character = characterService.getCharacters("레게머리뿌뿌뿡")
                .get(0);

        System.out.println("characters = " + character);

        assertThat(character).isNotNull();
    }
}