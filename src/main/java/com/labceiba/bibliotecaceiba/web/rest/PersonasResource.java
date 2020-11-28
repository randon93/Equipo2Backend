package com.labceiba.bibliotecaceiba.web.rest;

import com.labceiba.bibliotecaceiba.domain.Personas;
import com.labceiba.bibliotecaceiba.repository.PersonasRepository;
import com.labceiba.bibliotecaceiba.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.labceiba.bibliotecaceiba.domain.Personas}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonasResource {

    private final Logger log = LoggerFactory.getLogger(PersonasResource.class);

    private static final String ENTITY_NAME = "bibliotecaceibaPersonas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonasRepository personasRepository;

    public PersonasResource(PersonasRepository personasRepository) {
        this.personasRepository = personasRepository;
    }

    /**
     * {@code POST  /personas} : Create a new personas.
     *
     * @param personas the personas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personas, or with status {@code 400 (Bad Request)} if the personas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personas")
    public ResponseEntity<Personas> createPersonas(@Valid @RequestBody Personas personas) throws URISyntaxException {
        log.debug("REST request to save Personas : {}", personas);
        if (personas.getId() != null) {
            throw new BadRequestAlertException("A new personas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Personas result = personasRepository.save(personas);
        return ResponseEntity.created(new URI("/api/personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personas} : Updates an existing personas.
     *
     * @param personas the personas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personas,
     * or with status {@code 400 (Bad Request)} if the personas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personas")
    public ResponseEntity<Personas> updatePersonas(@Valid @RequestBody Personas personas) throws URISyntaxException {
        log.debug("REST request to update Personas : {}", personas);
        if (personas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Personas result = personasRepository.save(personas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personas} : get all the personas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personas in body.
     */
    @GetMapping("/personas")
    public List<Personas> getAllPersonas() {
        log.debug("REST request to get all Personas");
        return personasRepository.findAll();
    }

    /**
     * {@code GET  /personas/:id} : get the "id" personas.
     *
     * @param id the id of the personas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personas/{id}")
    public ResponseEntity<Personas> getPersonas(@PathVariable Long id) {
        log.debug("REST request to get Personas : {}", id);
        Optional<Personas> personas = personasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personas);
    }

    /**
     * {@code DELETE  /personas/:id} : delete the "id" personas.
     *
     * @param id the id of the personas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Void> deletePersonas(@PathVariable Long id) {
        log.debug("REST request to delete Personas : {}", id);
        personasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
