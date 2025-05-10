package ar.edu.utnfrc.backend.repositories;

import ar.edu.utnfrc.backend.entities.Museo;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MuseoRepositoryImpl extends RepositoryImpl<Museo> {
    @Override
    public void save(Museo museo) {
        try {
            manager.getTransaction().begin();
            manager.persist(museo);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    @Override
    public void saveAll(List<Museo> collection) {
        collection.forEach(this::save);
    }

    @Override
    public Museo findById(int id) {
        return this.manager.find(Museo.class, id);
    }


    public Museo findByName(String name) {
        List<Museo> result = manager.createNamedQuery("museos.findByName", Museo.class)
                .setParameter("nombre", name)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
