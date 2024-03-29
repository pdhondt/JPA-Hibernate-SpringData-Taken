package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtikelGroepBevatDitArtikelAlException extends RuntimeException {
    public ArtikelGroepBevatDitArtikelAlException() {
        super("ArtikelGroep bevat dit artikel al.");
    }
}
