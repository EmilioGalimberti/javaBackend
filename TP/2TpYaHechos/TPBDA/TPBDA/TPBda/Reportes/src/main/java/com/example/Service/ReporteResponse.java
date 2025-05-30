package com.example.Service;


import com.example.model.Prueba;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReporteResponse {
    private List<Prueba> incidentes;
    private String rutaArchivo;
}

