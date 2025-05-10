package ar.edu.utnfrc.backend;

import ar.edu.utnfrc.backend.services.ObraArtisticaService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ObraArtisticaService service = new ObraArtisticaService();
        URL path = App.class.getResource("/paintings_data.csv");

        try {
            service.bulkInsert(path.toURI());
            var lista = service.getObrasSeguroParcialOrderByDescendingAnio();
            lista.forEach(System.out::println);
            // llamar a todos los metodos del servicio para cumplir los puntos
            service.saveToDb();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
