package ar.edu.utnfrc.backend.repositories;

import ar.edu.utnfrc.backend.entities.Autor;
import ar.edu.utnfrc.backend.entities.EstiloArtistico;

import java.util.List;

public class AutorRepositoryImpl extends RepositoryImpl<Autor> {


    @Override
    public void save(Autor autor) {

        manager.getTransaction().begin();
        manager.persist(autor);
        manager.getTransaction().commit();

    }

    @Override
    public void saveAll(List<Autor> collection) {
        collection.forEach(this::save);
    }

    @Override
    public Autor findById(int id) {
        return this.manager.find(Autor.class, id);
    }

    public Autor findByName(String name) {
        List<Autor> result = manager.createNamedQuery("autor.findByName", Autor.class)
                .setParameter("nombre", name)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
