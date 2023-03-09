package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtikelBestaatAlException extends RuntimeException {
    public ArtikelBestaatAlException() {
        super("Artikel bestaat al.");
    }
}
