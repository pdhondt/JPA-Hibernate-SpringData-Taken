package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artikels")
class ArtikelController {
    private final ArtikelService artikelService;

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    @GetMapping("{id}")
    Artikel findById(@PathVariable long id) {
        return artikelService.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new);
    }
}
