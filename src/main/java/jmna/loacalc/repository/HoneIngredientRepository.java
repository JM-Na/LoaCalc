package jmna.loacalc.repository;

import jmna.loacalc.entity.HoneIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoneIngredientRepository extends JpaRepository<HoneIngredient, Long> {
    Optional<HoneIngredient> findByName(String name);
}
