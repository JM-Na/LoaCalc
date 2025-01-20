package jmna.loacalc.repository;

import jmna.loacalc.entity.T4Gem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface T4GemRepository extends JpaRepository<T4Gem, Long> {
    Optional<T4Gem> findByName(String name);
}
