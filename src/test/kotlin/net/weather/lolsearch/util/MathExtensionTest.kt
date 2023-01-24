package net.weather.lolsearch.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MathExtensionTest{

    @Test
    fun `소수점 반올림`(){
        val value = 1.123456789
        assertThat(MathExtension.round(value, 1)).isEqualTo(1.1);
        assertThat(MathExtension.round(value, 2)).isEqualTo(1.12);
        assertThat(MathExtension.round(value, 3)).isEqualTo(1.123);
        assertThat(MathExtension.round(value, 4)).isEqualTo(1.1235);
        assertThat(MathExtension.round(value, 5)).isEqualTo(1.12346);
        assertThat(MathExtension.round(value, 6)).isEqualTo(1.123457);
        assertThat(MathExtension.round(value, 7)).isEqualTo(1.1234568);
        assertThat(MathExtension.round(value, 8)).isEqualTo(1.12345679);
        assertThat(MathExtension.round(value, 9)).isEqualTo(1.123456789);
    }
}

