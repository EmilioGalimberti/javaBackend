package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Capacitacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CapacitacionRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CapacitacionesDB");

    public void save(Capacitacion capacitacion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(capacitacion);
        em.getTransaction().commit();
        em.close();
    }
}