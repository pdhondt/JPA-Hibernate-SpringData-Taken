package be.vdab.keuken.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NieuwNonFoodArtikel(@NotBlank String naam,
                                  @NotNull @PositiveOrZero BigDecimal aankoopprijs,
                                  @NotNull @PositiveOrZero BigDecimal verkoopprijs,
                                  @Positive int garantie,
                                  @Positive long artikelgroepId) {
}
