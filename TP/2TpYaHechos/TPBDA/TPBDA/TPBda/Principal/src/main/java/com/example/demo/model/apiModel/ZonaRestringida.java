package com.example.demo.model.apiModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ZonaRestringida {

    private Coordenada noroeste;
    private Coordenada sureste;
}
