package jmna.loacalc.repository;

import jmna.loacalc.entity.HoneIngredient;
import jmna.loacalc.entity.T4Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface T4AccessoryRepository extends JpaRepository<T4Accessory, Long> {
    Optional<T4Accessory> findByName(String name);
}
