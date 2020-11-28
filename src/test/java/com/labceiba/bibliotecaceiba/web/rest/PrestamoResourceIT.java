package com.labceiba.bibliotecaceiba.web.rest;

import com.labceiba.bibliotecaceiba.BibliotecaceibaApp;
import com.labceiba.bibliotecaceiba.domain.Prestamo;
import com.labceiba.bibliotecaceiba.repository.PrestamoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PrestamoResource} REST controller.
 */
@SpringBootTest(classes = BibliotecaceibaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrestamoResourceIT {

    private static final LocalDate DEFAULT_FECHA_PRESTAMO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_PRESTAMO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_ENTREGA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ENTREGA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_ENTREGADO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ENTREGADO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrestamoMockMvc;

    private Prestamo prestamo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prestamo createEntity(EntityManager em) {
        Prestamo prestamo = new Prestamo()
            .fechaPrestamo(DEFAULT_FECHA_PRESTAMO)
            .fechaEntrega(DEFAULT_FECHA_ENTREGA)
            .fechaEntregado(DEFAULT_FECHA_ENTREGADO)
            .observaciones(DEFAULT_OBSERVACIONES);
        return prestamo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prestamo createUpdatedEntity(EntityManager em) {
        Prestamo prestamo = new Prestamo()
            .fechaPrestamo(UPDATED_FECHA_PRESTAMO)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .fechaEntregado(UPDATED_FECHA_ENTREGADO)
            .observaciones(UPDATED_OBSERVACIONES);
        return prestamo;
    }

    @BeforeEach
    public void initTest() {
        prestamo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrestamo() throws Exception {
        int databaseSizeBeforeCreate = prestamoRepository.findAll().size();
        // Create the Prestamo
        restPrestamoMockMvc.perform(post("/api/prestamos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestamo)))
            .andExpect(status().isCreated());

        // Validate the Prestamo in the database
        List<Prestamo> prestamoList = prestamoRepository.findAll();
        assertThat(prestamoList).hasSize(databaseSizeBeforeCreate + 1);
        Prestamo testPrestamo = prestamoList.get(prestamoList.size() - 1);
        assertThat(testPrestamo.getFechaPrestamo()).isEqualTo(DEFAULT_FECHA_PRESTAMO);
        assertThat(testPrestamo.getFechaEntrega()).isEqualTo(DEFAULT_FECHA_ENTREGA);
        assertThat(testPrestamo.getFechaEntregado()).isEqualTo(DEFAULT_FECHA_ENTREGADO);
        assertThat(testPrestamo.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void createPrestamoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prestamoRepository.findAll().size();

        // Create the Prestamo with an existing ID
        prestamo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrestamoMockMvc.perform(post("/api/prestamos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestamo)))
            .andExpect(status().isBadRequest());

        // Validate the Prestamo in the database
        List<Prestamo> prestamoList = prestamoRepository.findAll();
        assertThat(prestamoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPrestamos() throws Exception {
        // Initialize the database
        prestamoRepository.saveAndFlush(prestamo);

        // Get all the prestamoList
        restPrestamoMockMvc.perform(get("/api/prestamos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prestamo.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaPrestamo").value(hasItem(DEFAULT_FECHA_PRESTAMO.toString())))
            .andExpect(jsonPath("$.[*].fechaEntrega").value(hasItem(DEFAULT_FECHA_ENTREGA.toString())))
            .andExpect(jsonPath("$.[*].fechaEntregado").value(hasItem(DEFAULT_FECHA_ENTREGADO.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES.toString())));
    }
    
    @Test
    @Transactional
    public void getPrestamo() throws Exception {
        // Initialize the database
        prestamoRepository.saveAndFlush(prestamo);

        // Get the prestamo
        restPrestamoMockMvc.perform(get("/api/prestamos/{id}", prestamo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prestamo.getId().intValue()))
            .andExpect(jsonPath("$.fechaPrestamo").value(DEFAULT_FECHA_PRESTAMO.toString()))
            .andExpect(jsonPath("$.fechaEntrega").value(DEFAULT_FECHA_ENTREGA.toString()))
            .andExpect(jsonPath("$.fechaEntregado").value(DEFAULT_FECHA_ENTREGADO.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPrestamo() throws Exception {
        // Get the prestamo
        restPrestamoMockMvc.perform(get("/api/prestamos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrestamo() throws Exception {
        // Initialize the database
        prestamoRepository.saveAndFlush(prestamo);

        int databaseSizeBeforeUpdate = prestamoRepository.findAll().size();

        // Update the prestamo
        Prestamo updatedPrestamo = prestamoRepository.findById(prestamo.getId()).get();
        // Disconnect from session so that the updates on updatedPrestamo are not directly saved in db
        em.detach(updatedPrestamo);
        updatedPrestamo
            .fechaPrestamo(UPDATED_FECHA_PRESTAMO)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .fechaEntregado(UPDATED_FECHA_ENTREGADO)
            .observaciones(UPDATED_OBSERVACIONES);

        restPrestamoMockMvc.perform(put("/api/prestamos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrestamo)))
            .andExpect(status().isOk());

        // Validate the Prestamo in the database
        List<Prestamo> prestamoList = prestamoRepository.findAll();
        assertThat(prestamoList).hasSize(databaseSizeBeforeUpdate);
        Prestamo testPrestamo = prestamoList.get(prestamoList.size() - 1);
        assertThat(testPrestamo.getFechaPrestamo()).isEqualTo(UPDATED_FECHA_PRESTAMO);
        assertThat(testPrestamo.getFechaEntrega()).isEqualTo(UPDATED_FECHA_ENTREGA);
        assertThat(testPrestamo.getFechaEntregado()).isEqualTo(UPDATED_FECHA_ENTREGADO);
        assertThat(testPrestamo.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void updateNonExistingPrestamo() throws Exception {
        int databaseSizeBeforeUpdate = prestamoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrestamoMockMvc.perform(put("/api/prestamos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestamo)))
            .andExpect(status().isBadRequest());

        // Validate the Prestamo in the database
        List<Prestamo> prestamoList = prestamoRepository.findAll();
        assertThat(prestamoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrestamo() throws Exception {
        // Initialize the database
        prestamoRepository.saveAndFlush(prestamo);

        int databaseSizeBeforeDelete = prestamoRepository.findAll().size();

        // Delete the prestamo
        restPrestamoMockMvc.perform(delete("/api/prestamos/{id}", prestamo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prestamo> prestamoList = prestamoRepository.findAll();
        assertThat(prestamoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
