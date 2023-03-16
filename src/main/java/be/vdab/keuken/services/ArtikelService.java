package be.vdab.keuken.services;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.domain.FoodArtikel;
import be.vdab.keuken.domain.NonFoodArtikel;
import be.vdab.keuken.dto.NieuwFoodArtikel;
import be.vdab.keuken.dto.NieuwNonFoodArtikel;
import be.vdab.keuken.exceptions.ArtikelBestaatAlException;
import be.vdab.keuken.exceptions.ArtikelGroepInArtikelNietGevondenException;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.repositories.ArtikelGroepRepository;
import be.vdab.keuken.repositories.ArtikelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;
    private final ArtikelGroepRepository artikelGroepRepository;

    public ArtikelService(ArtikelRepository artikelRepository, ArtikelGroepRepository artikelGroepRepository) {
        this.artikelRepository = artikelRepository;
        this.artikelGroepRepository = artikelGroepRepository;
    }
    public Optional<Artikel> findById(long id) {
        return artikelRepository.findById(id);
    }
    @Transactional
    public long create(NieuwFoodArtikel nieuwArtikel) {
        try {
            var artikelGroep = artikelGroepRepository.findById(nieuwArtikel.artikelgroepId())
                    .orElseThrow(() -> new ArtikelGroepInArtikelNietGevondenException());
            var artikel = new FoodArtikel(nieuwArtikel.naam(), nieuwArtikel.aankoopprijs(),
                    nieuwArtikel.verkoopprijs(), nieuwArtikel.houdbaarheid(), artikelGroep);
            artikelRepository.save(artikel);
            return artikel.getId();
        } catch (DataIntegrityViolationException ex) {
            throw new ArtikelBestaatAlException();
        }
    }
    @Transactional
    public long create(NieuwNonFoodArtikel nieuwArtikel) {
        try {
            var artikelGroep = artikelGroepRepository.findById(nieuwArtikel.artikelgroepId())
                    .orElseThrow(() -> new ArtikelGroepInArtikelNietGevondenException());
            var artikel = new NonFoodArtikel(nieuwArtikel.naam(), nieuwArtikel.aankoopprijs(),
                    nieuwArtikel.verkoopprijs(), nieuwArtikel.garantie(), artikelGroep);
            artikelRepository.save(artikel);
            return artikel.getId();
        } catch (DataIntegrityViolationException ex) {
            throw new ArtikelBestaatAlException();
        }
    }
    @Transactional
    public void wijzigVerkoopprijs(long id, BigDecimal prijs) {
        artikelRepository.findAndLockById(id)
                .orElseThrow(ArtikelNietGevondenException::new)
                .setVerkoopprijs(prijs);
    }
    public List<Artikel> findByNaamBevat(String tekst) {
        return artikelRepository.findByNaamContainsOrderByNaam(tekst);
    }
    public List<Artikel> findMetMinimumWinst(BigDecimal bedrag) {
        return artikelRepository.findMetMinimumWinst(bedrag);
    }
    public BigDecimal findGoedkoopsteVerkoopprijs() {
        return artikelRepository.findGoedkoopsteVerkoopprijs();
    }
    public Optional<Artikel> findByIdMetArtikelGroep(long id) {
        return artikelRepository.findByIdMetArtikelGroep(id);
    }
}
