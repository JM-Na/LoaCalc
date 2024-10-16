package jmna.loacalc.feign.client.characters;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterClient characterClient;

    public CharacterService(CharacterClient characterClient) {
        this.characterClient = characterClient;
    }

    public List<Character> getCharacters(String characterName) {
        return characterClient.getCharacter(characterName);
    }
}
