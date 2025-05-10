package ar.edu.utnfrc.backend.repositories.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbContext
{
    private static DbContext instance;

    private EntityManager manager;

    private DbContext(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aseguradora");
        manager = emf.createEntityManager();
    }

    public static DbContext getInstance(){
        if(instance == null){
            instance = new DbContext();
        }
        return instance;
    }

    public EntityManager getManager(){
        return this.manager;
    }
}
