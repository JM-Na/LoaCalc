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
public class RelicEngravingBook {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private LocalDateTime lastUpdated;

    @Builder
    public RelicEngravingBook(String name, double price) {
        this.name = name;
        this.price = price;
        this.lastUpdated = LocalDateTime.now();
    }
}
