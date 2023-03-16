package be.vdab.keuken.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id
    private long id;
    private String naam;

    public ArtikelGroep(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }
    protected ArtikelGroep() {}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
