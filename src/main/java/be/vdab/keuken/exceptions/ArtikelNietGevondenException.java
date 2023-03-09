package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtikelNietGevondenException extends RuntimeException {
    public ArtikelNietGevondenException() {
        super("Artikel niet gevonden.");
    }
}
