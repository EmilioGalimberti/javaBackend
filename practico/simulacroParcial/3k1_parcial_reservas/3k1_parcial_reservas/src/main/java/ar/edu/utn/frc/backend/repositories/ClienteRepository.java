package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Cliente;
import jakarta.persistence.*;

public class ClienteRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReservasBD");

    public void save(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (em.find(Cliente.class, cliente.getId()) == null) {
                em.persist(cliente);
            } else {
                em.merge(cliente);
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

    public Cliente find(Long clienteId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cliente.class, clienteId);
        } finally {
            em.close();
        }
    }
}