package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Artikel a where a.id = :id")
    Optional<Artikel> findAndLockById(long id);

    @EntityGraph(attributePaths = "artikelGroep")
    List<Artikel> findByNaamContainsOrderByNaam(String tekst);

    @Query("""
            select a
            from Artikel a
            where a.verkoopprijs >= a.aankoopprijs + :bedrag
            order by a.verkoopprijs
            """)
    List<Artikel> findMetMinimumWinst(BigDecimal bedrag);

    @Query("""
            select min(a.verkoopprijs)
            from Artikel a
            """)
    BigDecimal findGoedkoopsteVerkoopprijs();

    @Query("""
            select a from Artikel a join fetch a.artikelGroep
            where a.id = :id
            """)
    Optional<Artikel> findByIdMetArtikelGroep(long id);
}
