package com.fondatel.model;

import com.fondatel.model.enums.EstadoOrden;
import java.time.LocalDateTime;

public record Pedido(
        int folio,
        LocalDateTime fechaHora,
        int silla,
        int numeroMesa,
        String codigoCliente,
        String nombreCliente,
        String codigoEmpleadoMesero,
        String nombreEmpleado,
        int idAlimento,
        String nombreAlimento,
        double precio,
        String observaciones,
        EstadoOrden estadoOrden
) {
    @Override
    public String toString() {
        return "Pedido{" +
                "folio=" + folio +
                ", mesa=" + numeroMesa +
                ", silla=" + silla +
                ", alimento='" + nombreAlimento + '\'' +
                ", estado=" + estadoOrden +
                '}';
    }
}