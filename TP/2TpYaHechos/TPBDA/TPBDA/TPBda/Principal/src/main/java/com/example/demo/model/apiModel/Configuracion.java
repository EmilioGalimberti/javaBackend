package com.example.demo.model.apiModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class Configuracion {

    private Coordenada coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<ZonaRestringida> zonasRestringidas;

}

