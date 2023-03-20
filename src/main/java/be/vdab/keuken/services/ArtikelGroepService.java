package be.vdab.keuken.services;

import be.vdab.keuken.domain.ArtikelGroep;
import be.vdab.keuken.repositories.ArtikelGroepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelGroepService {
    private final ArtikelGroepRepository artikelGroepRepository;

    public ArtikelGroepService(ArtikelGroepRepository artikelGroepRepository) {
        this.artikelGroepRepository = artikelGroepRepository;
    }
    public Optional<ArtikelGroep> findById(long id) {
        return artikelGroepRepository.findById(id);
    }
}
