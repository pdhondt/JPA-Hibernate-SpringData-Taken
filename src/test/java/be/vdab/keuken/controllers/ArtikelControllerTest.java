package be.vdab.keuken.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql("/artikels.sql")
@AutoConfigureMockMvc
class ArtikelControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mockMvc;

    ArtikelControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    private long findIdTestArtikel() {
        return jdbcTemplate.queryForObject(
                "select id from artikels where naam = 'testartikel'", Long.class);
    }
    @Test
    void findById() throws Exception {
        var id = findIdTestArtikel();
        mockMvc.perform(get("/artikels/{id}", id))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("naam").value("testartikel"));
    }
    @Test
    void findByOnbestaandeIdVindtGeenArtikels() throws Exception {
        mockMvc.perform(get("/artikels/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
