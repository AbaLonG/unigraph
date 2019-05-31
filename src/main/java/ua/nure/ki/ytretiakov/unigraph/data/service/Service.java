package ua.nure.ki.ytretiakov.unigraph.data.service;

import java.io.Serializable;
import java.util.List;

public interface Service<E, ID extends Serializable> {

    void save(E entity);

    E findById(ID id);

    void deleteById(ID id);

    void deleteAll();

    boolean existsById(ID id);

    List<E> findAll();
}
