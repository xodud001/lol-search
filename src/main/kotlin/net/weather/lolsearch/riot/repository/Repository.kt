package net.weather.lolsearch.riot.repository

interface Repository<TYPE, ID> {

    val store: MutableMap<ID, TYPE>

    fun findById(id: ID): TYPE?

    fun save(id: ID, value: TYPE);

}