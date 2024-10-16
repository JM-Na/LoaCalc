package jmna.loacalc.feign.client.characters;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "CharacterClient", url = "${smilegate.url}" + "/characters")
public interface CharacterClient {
    @RequestMapping("{characterName}/siblings")
    List<Character> getCharacter(@PathVariable("characterName") String characterName);
}
