package ar.edu.utnfrc.backend.repositories;

import ar.edu.utnfrc.backend.entities.Museo;
import ar.edu.utnfrc.backend.entities.ObraArtistica;

import java.util.List;

public class ObraArtisticaRepositoryImpl extends RepositoryImpl<ObraArtistica> {

    public ObraArtisticaRepositoryImpl() {
        super();
    }

    @Override
    public void save(ObraArtistica obraArtistica) {
        manager.getTransaction().begin();
        manager.merge(obraArtistica);
        manager.getTransaction().commit();
    }

    @Override
    public void saveAll(List<ObraArtistica> collection) {
        collection.forEach(this::save);
    }

    @Override
    public ObraArtistica findById(int id) {
        return manager.find(ObraArtistica.class, id);
    }

    public ObraArtistica findByName(String name) {
        List<ObraArtistica> result = manager.createNamedQuery("obra.findByName", ObraArtistica.class)
                .setParameter("nombre", name)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
