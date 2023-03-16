package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtikelGroepInArtikelNietGevondenException extends RuntimeException {
    public ArtikelGroepInArtikelNietGevondenException() {
        super("Artikelgroep niet gevonden.");
    }
}
