package com.labceiba.bibliotecaceiba.web.rest;

import com.labceiba.bibliotecaceiba.BibliotecaceibaApp;
import com.labceiba.bibliotecaceiba.domain.Usuarios;
import com.labceiba.bibliotecaceiba.repository.UsuariosRepository;

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
 * Integration tests for the {@link UsuariosResource} REST controller.
 */
@SpringBootTest(classes = BibliotecaceibaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsuariosResourceIT {

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE = "BBBBBBBBBB";

    private static final String DEFAULT_ROL = "A";
    private static final String UPDATED_ROL = "B";

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuariosMockMvc;

    private Usuarios usuarios;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .correo(DEFAULT_CORREO)
            .clave(DEFAULT_CLAVE)
            .rol(DEFAULT_ROL);
        return usuarios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createUpdatedEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .correo(UPDATED_CORREO)
            .clave(UPDATED_CLAVE)
            .rol(UPDATED_ROL);
        return usuarios;
    }

    @BeforeEach
    public void initTest() {
        usuarios = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarios() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();
        // Create the Usuarios
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isCreated());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate + 1);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testUsuarios.getClave()).isEqualTo(DEFAULT_CLAVE);
        assertThat(testUsuarios.getRol()).isEqualTo(DEFAULT_ROL);
    }

    @Test
    @Transactional
    public void createUsuariosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();

        // Create the Usuarios with an existing ID
        usuarios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get all the usuariosList
        restUsuariosMockMvc.perform(get("/api/usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarios.getId().intValue())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].clave").value(hasItem(DEFAULT_CLAVE)))
            .andExpect(jsonPath("$.[*].rol").value(hasItem(DEFAULT_ROL)));
    }
    
    @Test
    @Transactional
    public void getUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", usuarios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuarios.getId().intValue()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.clave").value(DEFAULT_CLAVE))
            .andExpect(jsonPath("$.rol").value(DEFAULT_ROL));
    }
    @Test
    @Transactional
    public void getNonExistingUsuarios() throws Exception {
        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // Update the usuarios
        Usuarios updatedUsuarios = usuariosRepository.findById(usuarios.getId()).get();
        // Disconnect from session so that the updates on updatedUsuarios are not directly saved in db
        em.detach(updatedUsuarios);
        updatedUsuarios
            .correo(UPDATED_CORREO)
            .clave(UPDATED_CLAVE)
            .rol(UPDATED_ROL);

        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsuarios)))
            .andExpect(status().isOk());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testUsuarios.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testUsuarios.getRol()).isEqualTo(UPDATED_ROL);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarios() throws Exception {
        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeDelete = usuariosRepository.findAll().size();

        // Delete the usuarios
        restUsuariosMockMvc.perform(delete("/api/usuarios/{id}", usuarios.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
