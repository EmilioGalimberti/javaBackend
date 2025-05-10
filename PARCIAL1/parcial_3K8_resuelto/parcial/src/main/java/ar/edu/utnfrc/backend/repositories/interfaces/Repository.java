package ar.edu.utnfrc.backend.repositories.interfaces;

import java.util.Collection;
import java.util.List;

public interface Repository <T> {

    void save(T t);
    void saveAll(List<T> collection);
    T findById(int id);

}
