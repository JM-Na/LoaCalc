package jmna.loacalc.processor.market;

import jmna.loacalc.calculator.engraving.RelicEngravingBook;
import jmna.loacalc.calculator.hone.HoneIngredients;
import jmna.loacalc.feign.client.markets.MarketClient;
import jmna.loacalc.feign.client.markets.MarketItems;
import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import jmna.loacalc.processor.armory.avatar.Avatar;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class MarketProcessorTest {

    @Autowired
    private MarketClient marketClient;
    @Autowired
    private MarketProcessor marketProcessor;

    @Test
    void 강화재료() {
        marketProcessor.initPrice();

        HoneIngredients[] values = HoneIngredients.values();
        for (HoneIngredients value : values) {
            System.out.println("value = " + value);
            System.out.println("value.getPrice() = " + value.getPrice());
            System.out.println("value.getName() = " + value.getName());
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
        CharacterAvatar characterAvatar = new CharacterAvatar(new ArrayList<Avatar>(), 2,2);
        characterAvatar.setWeapon(true);
        characterAvatar.setHead(true);
        characterAvatar.setChest(false);
        characterAvatar.setPants(false);

        marketProcessor.getLegendaryAvatar("워로드", characterAvatar);

    }

    @Test
    void 유물각인서_초기화() {
        marketProcessor.initEngravingBookPrice();

        RelicEngravingBook[] values = RelicEngravingBook.values();
        for (RelicEngravingBook value : values) {
            System.out.println("value = " + value);
            System.out.println("value.getPrice() = " + value.getPrice());
        }

    }
}