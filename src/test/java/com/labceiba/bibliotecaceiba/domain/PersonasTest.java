package com.labceiba.bibliotecaceiba.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.labceiba.bibliotecaceiba.web.rest.TestUtil;

public class PersonasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personas.class);
        Personas personas1 = new Personas();
        personas1.setId(1L);
        Personas personas2 = new Personas();
        personas2.setId(personas1.getId());
        assertThat(personas1).isEqualTo(personas2);
        personas2.setId(2L);
        assertThat(personas1).isNotEqualTo(personas2);
        personas1.setId(null);
        assertThat(personas1).isNotEqualTo(personas2);
    }
}
