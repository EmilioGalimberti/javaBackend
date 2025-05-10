package ar.edu.utnfrc.backend.repositories;

import ar.edu.utnfrc.backend.entities.EstiloArtistico;
import ar.edu.utnfrc.backend.entities.Museo;

import java.util.List;

public class EstiloArtisticoRepositoryImpl extends RepositoryImpl<EstiloArtistico> {
    @Override
    public void save(EstiloArtistico estiloArtistico) {
        try {
            manager.getTransaction().begin();
            manager.persist(estiloArtistico);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    @Override
    public void saveAll(List<EstiloArtistico> collection) {
        collection.forEach(this::save);
    }

    @Override
    public EstiloArtistico findById(int id) {
        return this.manager.find(EstiloArtistico.class, id);
    }

    public EstiloArtistico findByName(String name) {
        List<EstiloArtistico> result = manager.createNamedQuery("estilos.findByName", EstiloArtistico.class)
                .setParameter("nombre", name)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
