package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtikelGroepNietGevondenException extends RuntimeException {
    public ArtikelGroepNietGevondenException() {
        super("Artikelgroep niet gevonden.");
    }
}
