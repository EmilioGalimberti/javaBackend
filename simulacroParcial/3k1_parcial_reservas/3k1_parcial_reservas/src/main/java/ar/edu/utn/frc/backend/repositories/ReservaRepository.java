package ar.edu.utn.frc.backend.repositories;

//Explicación del repositorio:
//1. **¿Para qué sirven los repositories?**
//    - Los repositories son una capa de abstracción entre la lógica de negocio y la base de datos
//    - Encapsulan las operaciones CRUD (Create, Read, Update, Delete)
//    - Permiten cambiar la implementación del almacenamiento sin afectar el resto del código
//    - Siguen el patrón Repository que aísla los detalles de persistencia

import ar.edu.utn.frc.backend.entities.Reserva;
import jakarta.persistence.*;

public class ReservaRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReservasBD");

    public void save(Reserva reserva) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        em.close();
    }

    public Reserva find(Long reservaId) {
        EntityManager em = emf.createEntityManager();
        Reserva reserva = em.find(Reserva.class, reservaId);
        em.close();
        return reserva;
    }
}