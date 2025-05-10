// JugadorRepository.java
package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Jugador;
import jakarta.persistence.*;

public class JugadorRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (em.find(Jugador.class, jugador.getJugadorId()) == null) {
                em.persist(jugador);
            } else {
                em.merge(jugador);
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

    public Jugador find(Long jugadorId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Jugador.class, jugadorId);
        } finally {
            em.close();
        }
    }
}