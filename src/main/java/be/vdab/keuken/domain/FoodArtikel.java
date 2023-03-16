package be.vdab.keuken.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
    private int houdbaarheid;

    public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs,
                       int houdbaarheid, ArtikelGroep artikelGroep) {
        super(naam, aankoopprijs, verkoopprijs, artikelGroep);
        this.houdbaarheid = houdbaarheid;
    }

    protected FoodArtikel() {
    }

    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}
