package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VisitControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindVisit() throws Exception {

        String NAME = "Carlos";
        String APELLIDO = "Pérez";
        String DIRECCION = "Av. Siempre Viva 123";
        Long id_visit = 1L;

        mockMvc.perform(get("/visits/{id}", id_visit))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id_visit)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.apellido", is(APELLIDO)))
                .andExpect(jsonPath("$.direccion", is(DIRECCION)));
    }

    @Test
    public void testCreateVisit() throws Exception {

        Visit newVisit = new Visit();
        newVisit.setName("Carlos");
        newVisit.setApellido("Pérez");
        newVisit.setDireccion("Av. Siempre Viva 123");

        mockMvc.perform(post("/visits")
                        .content(om.writeValueAsString(newVisit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Pérez")))
                .andExpect(jsonPath("$.direccion", is("Av. Siempre Viva 123")));
    }

    @Test
    public void testUpdateVisit() throws Exception {
        Visit updatedVisit = new Visit();
        updatedVisit.setId(2L);
        updatedVisit.setName("Ana");
        updatedVisit.setApellido("García");
        updatedVisit.setDireccion("Calle Falsa 456");

        mockMvc.perform(put("/visits/{id}", 1L)
                        .content(om.writeValueAsString(updatedVisit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Pérez")))
                .andExpect(jsonPath("$.direccion", is("Av. Nueva 123")));
    }

    @Test
    public void testDeleteVisit() throws Exception {
        mockMvc.perform(delete("/visits/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
