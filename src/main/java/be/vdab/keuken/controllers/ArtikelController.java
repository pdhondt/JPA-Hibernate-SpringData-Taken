package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.dto.NieuwFoodArtikel;
import be.vdab.keuken.dto.NieuwNonFoodArtikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping("artikels")
class ArtikelController {
    private final ArtikelService artikelService;
    private record PrijsWijziging(@NotNull @PositiveOrZero BigDecimal bedrag) {}
    private record ArtikelBeknopt(long id, String naam, BigDecimal verkoopprijs) {
        ArtikelBeknopt(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs());
        }
    }
    private record ArtikelBeknoptMetArtikelGroepNaam(long id, String naam, BigDecimal verkoopprijs,
                                                     String artikelGroepNaam) {
        ArtikelBeknoptMetArtikelGroepNaam(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs(), artikel.getArtikelGroep().getNaam());
        }
    }

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    @GetMapping("{id}")
    ArtikelBeknopt findById(@PathVariable long id) {
        return artikelService.findById(id)
                .map(artikel -> new ArtikelBeknopt(artikel))
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @PostMapping("food")
    long create(@RequestBody @Valid NieuwFoodArtikel nieuwFoodArtikel) {
        return artikelService.create(nieuwFoodArtikel);
    }
    @PostMapping("nonfood")
    long create(@RequestBody @Valid NieuwNonFoodArtikel nieuwNonFoodArtikel) {
        return artikelService.create(nieuwNonFoodArtikel);
    }
    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijs(@PathVariable long id, @RequestBody @Valid PrijsWijziging prijs) {
        artikelService.wijzigVerkoopprijs(id, prijs.bedrag());
    }
    @GetMapping(params = "naamBevat")
    Stream<ArtikelBeknoptMetArtikelGroepNaam> findByNaamBevat(String naamBevat) {
        return artikelService.findByNaamBevat(naamBevat)
                .stream()
                .map(ArtikelBeknoptMetArtikelGroepNaam::new);
    }
    @GetMapping(params = "minimumWinst")
    Stream<ArtikelBeknopt> findMetMiniumumWinst(BigDecimal minimumWinst) {
        return artikelService.findMetMinimumWinst(minimumWinst)
                .stream()
                .map(ArtikelBeknopt::new);
    }
    @GetMapping("verkoopprijzen/goedkoopste")
    BigDecimal findGoedkoopsteVerkoopprijs() {
        return artikelService.findGoedkoopsteVerkoopprijs();
    }
    @GetMapping("{id}/artikelGroepNaam")
    ArtikelBeknoptMetArtikelGroepNaam findMetArtikelGroepNaam(@PathVariable long id) {
        return artikelService.findByIdMetArtikelGroep(id)
                .map(artikel -> new ArtikelBeknoptMetArtikelGroepNaam(artikel))
                .orElseThrow(() -> new ArtikelNietGevondenException());
    }
}
