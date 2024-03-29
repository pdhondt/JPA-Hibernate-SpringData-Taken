package be.vdab.keuken.controllers;

import be.vdab.keuken.exceptions.ArtikelGroepNietGevondenException;
import be.vdab.keuken.services.ArtikelGroepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("artikelGroepen")
class ArtikelGroepController {
    private final ArtikelGroepService artikelGroepService;

    ArtikelGroepController(ArtikelGroepService artikelGroepService) {
        this.artikelGroepService = artikelGroepService;
    }
    @GetMapping("{id}/artikels")
    Stream<String> findArtikelsVanById(@PathVariable long id) {
        return artikelGroepService.findById(id)
                .orElseThrow(() -> new ArtikelGroepNietGevondenException())
                .getArtikels()
                .stream()
                .map(artikel -> artikel.getNaam());
    }
}
