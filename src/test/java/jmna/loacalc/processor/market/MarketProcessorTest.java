package jmna.loacalc.processor.market;

import jmna.loacalc.entity.HoneIngredient;
import jmna.loacalc.entity.RelicEngravingBook;
import jmna.loacalc.feign.client.markets.MarketClient;
import jmna.loacalc.feign.client.markets.MarketItems;
import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.repository.HoneIngredientRepository;
import jmna.loacalc.repository.RelicEngravingBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MarketProcessorTest {

    @Autowired
    private MarketClient marketClient;
    @Autowired
    private MarketProcessor marketProcessor;
    @Autowired
    private RelicEngravingBookRepository repository;
    @Autowired
    private HoneIngredientRepository ingredientRepository;


    @Test
    void 강화재료_JPA() {
        marketProcessor.initHoneIngredientsPrice();

        List<HoneIngredient> all = ingredientRepository.findAll();
        for (HoneIngredient honeIngredient : all) {
            System.out.println("honeIngredient.getName() = " + honeIngredient.getName());
            System.out.println("honeIngredient.getPrice() = " + honeIngredient.getPrice());
        }
    }

    @Test
    void 파편() {
        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("GRADE");
        requestMarketItems.setCategoryCode(50000);
        requestMarketItems.setCharacterClass(null);
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade(null);
        requestMarketItems.setSortCondition("ASC");

        requestMarketItems.setItemName("파편");

        MarketItems marketItems = marketClient.getMarketItems(requestMarketItems);
        System.out.println("marketItems = " + marketItems);
    }

    @Test
    void 전설아바타() {
        CharacterAvatar characterAvatar = new CharacterAvatar(new ArrayList<>(), 2,2);
        characterAvatar.setWeapon(true);
        characterAvatar.setHead(true);
        characterAvatar.setChest(false);
        characterAvatar.setPants(false);

        marketProcessor.getLegendaryAvatar("워로드", characterAvatar);

    }

    @Test
    void 유물각인서_초기화_JPA() {
        marketProcessor.initEngravingBookPrice();
        List<RelicEngravingBook> all = repository.findAll();

        for (RelicEngravingBook relicEngravingBook : all) {
            System.out.println("name = " + relicEngravingBook.getName());
            System.out.println("price = " + relicEngravingBook.getPrice());
            System.out.println("lastUpdated = " + relicEngravingBook.getLastUpdated());
        }
    }
}