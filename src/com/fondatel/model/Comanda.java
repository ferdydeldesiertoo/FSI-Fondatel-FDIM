package com.fondatel.model;

import com.fondatel.model.enums.EstadoOrden;
import java.time.LocalDateTime;

public record Comanda(
        int folio,
        LocalDateTime fechaHora,
        String observaciones,
        EstadoOrden estadoOrden,
        int idAlimento,
        String nombreAlimento,
        String descripcion
) {
    @Override
    public String toString() {
        return "Comanda{" +
                "folio=" + folio +
                ", alimento='" + nombreAlimento + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado=" + estadoOrden +
                '}';
    }
}