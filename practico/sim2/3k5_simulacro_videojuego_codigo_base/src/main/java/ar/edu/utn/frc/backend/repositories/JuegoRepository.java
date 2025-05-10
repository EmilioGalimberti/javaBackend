// JuegoRepository.java
package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Juego;
import jakarta.persistence.*;

public class JuegoRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Juego juego) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (em.find(Juego.class, juego.getJuegoId()) == null) {
                em.persist(juego);
            } else {
                em.merge(juego);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Juego find(Long juegoId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Juego.class, juegoId);
        } finally {
            em.close();
        }
    }
}