package ar.edu.utnfrc.backend.repositories;

import ar.edu.utnfrc.backend.repositories.context.DbContext;
import ar.edu.utnfrc.backend.repositories.interfaces.Repository;
import jakarta.persistence.EntityManager;

public abstract class RepositoryImpl <T> implements Repository<T> {

    protected EntityManager manager;

    public RepositoryImpl() {
        this.manager = DbContext.getInstance().getManager();
    }

}
