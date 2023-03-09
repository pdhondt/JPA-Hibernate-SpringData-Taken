package be.vdab.keuken.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/artikels.sql")
class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final ArtikelRepository artikelRepository;

    ArtikelRepositoryTest(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }
    private long findIdTestArtikel() {
        return jdbcTemplate.queryForObject(
                "select id from artikels where naam = 'testartikel'", Long.class);
    }
    @Test
    void findById() {
        var id = findIdTestArtikel();
        assertThat(artikelRepository.findById(id).get().getNaam())
                .isEqualTo("testartikel");
    }
    @Test
    void findByOnbestaandeIdVindtGeenArtikel() {
        assertThat(artikelRepository.findById(Long.MAX_VALUE)).isEmpty();
    }
}
