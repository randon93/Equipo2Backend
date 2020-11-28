package com.labceiba.bibliotecaceiba.web.rest;

import com.labceiba.bibliotecaceiba.BibliotecaceibaApp;
import com.labceiba.bibliotecaceiba.domain.Libro;
import com.labceiba.bibliotecaceiba.repository.LibroRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LibroResource} REST controller.
 */
@SpringBootTest(classes = BibliotecaceibaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LibroResourceIT {

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANTIDAD_TOTAL = 1;
    private static final Integer UPDATED_CANTIDAD_TOTAL = 2;

    private static final Integer DEFAULT_CANTIDAD_DISPONIBLE = 1;
    private static final Integer UPDATED_CANTIDAD_DISPONIBLE = 2;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLibroMockMvc;

    private Libro libro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Libro createEntity(EntityManager em) {
        Libro libro = new Libro()
            .isbn(DEFAULT_ISBN)
            .nombre(DEFAULT_NOMBRE)
            .cantidadTotal(DEFAULT_CANTIDAD_TOTAL)
            .cantidadDisponible(DEFAULT_CANTIDAD_DISPONIBLE);
        return libro;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Libro createUpdatedEntity(EntityManager em) {
        Libro libro = new Libro()
            .isbn(UPDATED_ISBN)
            .nombre(UPDATED_NOMBRE)
            .cantidadTotal(UPDATED_CANTIDAD_TOTAL)
            .cantidadDisponible(UPDATED_CANTIDAD_DISPONIBLE);
        return libro;
    }

    @BeforeEach
    public void initTest() {
        libro = createEntity(em);
    }

    @Test
    @Transactional
    public void createLibro() throws Exception {
        int databaseSizeBeforeCreate = libroRepository.findAll().size();
        // Create the Libro
        restLibroMockMvc.perform(post("/api/libros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libro)))
            .andExpect(status().isCreated());

        // Validate the Libro in the database
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeCreate + 1);
        Libro testLibro = libroList.get(libroList.size() - 1);
        assertThat(testLibro.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testLibro.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testLibro.getCantidadTotal()).isEqualTo(DEFAULT_CANTIDAD_TOTAL);
        assertThat(testLibro.getCantidadDisponible()).isEqualTo(DEFAULT_CANTIDAD_DISPONIBLE);
    }

    @Test
    @Transactional
    public void createLibroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = libroRepository.findAll().size();

        // Create the Libro with an existing ID
        libro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibroMockMvc.perform(post("/api/libros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libro)))
            .andExpect(status().isBadRequest());

        // Validate the Libro in the database
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLibros() throws Exception {
        // Initialize the database
        libroRepository.saveAndFlush(libro);

        // Get all the libroList
        restLibroMockMvc.perform(get("/api/libros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(libro.getId().intValue())))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].cantidadTotal").value(hasItem(DEFAULT_CANTIDAD_TOTAL)))
            .andExpect(jsonPath("$.[*].cantidadDisponible").value(hasItem(DEFAULT_CANTIDAD_DISPONIBLE)));
    }
    
    @Test
    @Transactional
    public void getLibro() throws Exception {
        // Initialize the database
        libroRepository.saveAndFlush(libro);

        // Get the libro
        restLibroMockMvc.perform(get("/api/libros/{id}", libro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(libro.getId().intValue()))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.cantidadTotal").value(DEFAULT_CANTIDAD_TOTAL))
            .andExpect(jsonPath("$.cantidadDisponible").value(DEFAULT_CANTIDAD_DISPONIBLE));
    }
    @Test
    @Transactional
    public void getNonExistingLibro() throws Exception {
        // Get the libro
        restLibroMockMvc.perform(get("/api/libros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLibro() throws Exception {
        // Initialize the database
        libroRepository.saveAndFlush(libro);

        int databaseSizeBeforeUpdate = libroRepository.findAll().size();

        // Update the libro
        Libro updatedLibro = libroRepository.findById(libro.getId()).get();
        // Disconnect from session so that the updates on updatedLibro are not directly saved in db
        em.detach(updatedLibro);
        updatedLibro
            .isbn(UPDATED_ISBN)
            .nombre(UPDATED_NOMBRE)
            .cantidadTotal(UPDATED_CANTIDAD_TOTAL)
            .cantidadDisponible(UPDATED_CANTIDAD_DISPONIBLE);

        restLibroMockMvc.perform(put("/api/libros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLibro)))
            .andExpect(status().isOk());

        // Validate the Libro in the database
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeUpdate);
        Libro testLibro = libroList.get(libroList.size() - 1);
        assertThat(testLibro.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testLibro.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testLibro.getCantidadTotal()).isEqualTo(UPDATED_CANTIDAD_TOTAL);
        assertThat(testLibro.getCantidadDisponible()).isEqualTo(UPDATED_CANTIDAD_DISPONIBLE);
    }

    @Test
    @Transactional
    public void updateNonExistingLibro() throws Exception {
        int databaseSizeBeforeUpdate = libroRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibroMockMvc.perform(put("/api/libros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libro)))
            .andExpect(status().isBadRequest());

        // Validate the Libro in the database
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLibro() throws Exception {
        // Initialize the database
        libroRepository.saveAndFlush(libro);

        int databaseSizeBeforeDelete = libroRepository.findAll().size();

        // Delete the libro
        restLibroMockMvc.perform(delete("/api/libros/{id}", libro.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
