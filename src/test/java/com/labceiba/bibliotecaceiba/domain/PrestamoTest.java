package com.labceiba.bibliotecaceiba.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.labceiba.bibliotecaceiba.web.rest.TestUtil;

public class PrestamoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prestamo.class);
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setId(1L);
        Prestamo prestamo2 = new Prestamo();
        prestamo2.setId(prestamo1.getId());
        assertThat(prestamo1).isEqualTo(prestamo2);
        prestamo2.setId(2L);
        assertThat(prestamo1).isNotEqualTo(prestamo2);
        prestamo1.setId(null);
        assertThat(prestamo1).isNotEqualTo(prestamo2);
    }
}
