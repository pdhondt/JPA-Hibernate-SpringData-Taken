package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.dto.NieuwArtikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("artikels")
class ArtikelController {
    private final ArtikelService artikelService;
    private record PrijsWijziging(@NotNull @PositiveOrZero BigDecimal bedrag) {}

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    @GetMapping("{id}")
    Artikel findById(@PathVariable long id) {
        return artikelService.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @PostMapping
    long create(@RequestBody @Valid NieuwArtikel nieuwArtikel) {
        return artikelService.create(nieuwArtikel);
    }
    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijs(@PathVariable long id, @RequestBody @Valid PrijsWijziging prijs) {
        artikelService.wijzigVerkoopprijs(id, prijs.bedrag());
    }
    @GetMapping(params = "naamBevat")
    List<Artikel> findByNaamBevat(String naamBevat) {
        return artikelService.findByNaamBevat(naamBevat);
    }
    @GetMapping(params = "minimumWinst")
    List<Artikel> findMetMiniumumWinst(BigDecimal minimumWinst) {
        return artikelService.findMetMinimumWinst(minimumWinst);
    }
}
