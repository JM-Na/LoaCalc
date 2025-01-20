package jmna.loacalc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoneIngredient {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String icon;

    @Column
    private LocalDateTime lastUpdated;

    @Builder
    public HoneIngredient(String name, String icon, double price) {
        this.name = name;
        this.price = (int) price;
        this.icon = icon;
        this.lastUpdated = LocalDateTime.now();
    }
}
