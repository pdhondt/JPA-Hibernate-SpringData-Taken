package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}
