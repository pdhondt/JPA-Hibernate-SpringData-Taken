package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Artikel a where a.id = :id")
    Optional<Artikel> findAndLockById(long id);
}
