package net.weather.lolsearch.riot.repository

class MemoryMapRepository<TYPE, ID>(override val store: MutableMap<ID, TYPE> = mutableMapOf()) : Repository<TYPE, ID>  {

    override fun findById(id: ID): TYPE? {
        return store[id];
    }

    override fun save(id: ID, value: TYPE) {
        store[id] = value;
    }
}