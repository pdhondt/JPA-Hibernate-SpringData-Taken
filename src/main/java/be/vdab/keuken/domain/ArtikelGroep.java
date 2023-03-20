package be.vdab.keuken.domain;

import be.vdab.keuken.exceptions.ArtikelGroepBevatDitArtikelAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artikelGroep")
    @OrderBy("naam, verkoopprijs")
    private Set<Artikel> artikels;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
    public Set<Artikel> getArtikels() {
        return Collections.unmodifiableSet(artikels);
    }
    public void voegArtikelToe(Artikel artikel) {
        if (!artikels.add(artikel)) {
            throw new ArtikelGroepBevatDitArtikelAlException();
        }
    }

}
