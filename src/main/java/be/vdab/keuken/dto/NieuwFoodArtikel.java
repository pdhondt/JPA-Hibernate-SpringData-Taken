package be.vdab.keuken.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NieuwFoodArtikel(@NotBlank String naam,
                               @NotNull @PositiveOrZero BigDecimal aankoopprijs,
                               @NotNull @PositiveOrZero BigDecimal verkoopprijs,
                               @Positive int houdbaarheid,
                               @Positive long artikelgroepId) {
}
