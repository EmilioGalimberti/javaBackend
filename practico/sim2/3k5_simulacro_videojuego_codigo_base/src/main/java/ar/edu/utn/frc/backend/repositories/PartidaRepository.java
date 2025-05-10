// PartidaRepository.java
package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Partida;
import jakarta.persistence.*;

public class PartidaRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Partida partida) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (em.find(Partida.class, partida.getPartidaId()) == null) {
                em.persist(partida);
            } else {
                em.merge(partida);
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

    public Partida find(Long partidaId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Partida.class, partidaId);
        } finally {
            em.close();
        }
    }
}