package com.labceiba.bibliotecaceiba.web.rest;

import com.labceiba.bibliotecaceiba.BibliotecaceibaApp;
import com.labceiba.bibliotecaceiba.domain.Personas;
import com.labceiba.bibliotecaceiba.repository.PersonasRepository;

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
 * Integration tests for the {@link PersonasResource} REST controller.
 */
@SpringBootTest(classes = BibliotecaceibaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonasResourceIT {

    private static final String DEFAULT_TIPO_IDENTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_IDENTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonasMockMvc;

    private Personas personas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personas createEntity(EntityManager em) {
        Personas personas = new Personas()
            .tipoIdentificacion(DEFAULT_TIPO_IDENTIFICACION)
            .identificacion(DEFAULT_IDENTIFICACION)
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO);
        return personas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personas createUpdatedEntity(EntityManager em) {
        Personas personas = new Personas()
            .tipoIdentificacion(UPDATED_TIPO_IDENTIFICACION)
            .identificacion(UPDATED_IDENTIFICACION)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO);
        return personas;
    }

    @BeforeEach
    public void initTest() {
        personas = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonas() throws Exception {
        int databaseSizeBeforeCreate = personasRepository.findAll().size();
        // Create the Personas
        restPersonasMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personas)))
            .andExpect(status().isCreated());

        // Validate the Personas in the database
        List<Personas> personasList = personasRepository.findAll();
        assertThat(personasList).hasSize(databaseSizeBeforeCreate + 1);
        Personas testPersonas = personasList.get(personasList.size() - 1);
        assertThat(testPersonas.getTipoIdentificacion()).isEqualTo(DEFAULT_TIPO_IDENTIFICACION);
        assertThat(testPersonas.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testPersonas.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPersonas.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testPersonas.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testPersonas.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
    }

    @Test
    @Transactional
    public void createPersonasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personasRepository.findAll().size();

        // Create the Personas with an existing ID
        personas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonasMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personas)))
            .andExpect(status().isBadRequest());

        // Validate the Personas in the database
        List<Personas> personasList = personasRepository.findAll();
        assertThat(personasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonas() throws Exception {
        // Initialize the database
        personasRepository.saveAndFlush(personas);

        // Get all the personasList
        restPersonasMockMvc.perform(get("/api/personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personas.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoIdentificacion").value(hasItem(DEFAULT_TIPO_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)));
    }
    
    @Test
    @Transactional
    public void getPersonas() throws Exception {
        // Initialize the database
        personasRepository.saveAndFlush(personas);

        // Get the personas
        restPersonasMockMvc.perform(get("/api/personas/{id}", personas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personas.getId().intValue()))
            .andExpect(jsonPath("$.tipoIdentificacion").value(DEFAULT_TIPO_IDENTIFICACION))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO));
    }
    @Test
    @Transactional
    public void getNonExistingPersonas() throws Exception {
        // Get the personas
        restPersonasMockMvc.perform(get("/api/personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonas() throws Exception {
        // Initialize the database
        personasRepository.saveAndFlush(personas);

        int databaseSizeBeforeUpdate = personasRepository.findAll().size();

        // Update the personas
        Personas updatedPersonas = personasRepository.findById(personas.getId()).get();
        // Disconnect from session so that the updates on updatedPersonas are not directly saved in db
        em.detach(updatedPersonas);
        updatedPersonas
            .tipoIdentificacion(UPDATED_TIPO_IDENTIFICACION)
            .identificacion(UPDATED_IDENTIFICACION)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO);

        restPersonasMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonas)))
            .andExpect(status().isOk());

        // Validate the Personas in the database
        List<Personas> personasList = personasRepository.findAll();
        assertThat(personasList).hasSize(databaseSizeBeforeUpdate);
        Personas testPersonas = personasList.get(personasList.size() - 1);
        assertThat(testPersonas.getTipoIdentificacion()).isEqualTo(UPDATED_TIPO_IDENTIFICACION);
        assertThat(testPersonas.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testPersonas.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPersonas.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testPersonas.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testPersonas.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonas() throws Exception {
        int databaseSizeBeforeUpdate = personasRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonasMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personas)))
            .andExpect(status().isBadRequest());

        // Validate the Personas in the database
        List<Personas> personasList = personasRepository.findAll();
        assertThat(personasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonas() throws Exception {
        // Initialize the database
        personasRepository.saveAndFlush(personas);

        int databaseSizeBeforeDelete = personasRepository.findAll().size();

        // Delete the personas
        restPersonasMockMvc.perform(delete("/api/personas/{id}", personas.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Personas> personasList = personasRepository.findAll();
        assertThat(personasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
