package jmna.loacalc.repository;

import jmna.loacalc.entity.RelicEngravingBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelicEngravingBookRepository extends JpaRepository<RelicEngravingBook, Long> {
    Optional<RelicEngravingBook> findByName(String name);
}
