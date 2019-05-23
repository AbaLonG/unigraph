package ua.nure.ki.ytretiakov.unigraph.data.service;

import java.io.Serializable;

public interface Service<E, ID extends Serializable> {

    void save(E entity);

    E findById(ID id);

    void deleteById(ID id);

    void deleteAll();
}
