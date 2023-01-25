package net.weather.lolsearch.riot.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemoryMapRepositoryTest {

    private val repository = MemoryMapRepository<String, Int>()

    @Test
    fun `save and find`(){
        repository.save(1, "VALUE1")
        repository.save(1, "VALUE2")
        repository.save(2, "VALUE3")

        val value = repository.findById(1)
        assertThat(value).isEqualTo("VALUE2")

    }
}