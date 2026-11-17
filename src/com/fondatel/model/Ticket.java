package com.fondatel.model;

import java.time.LocalDateTime;

public record Ticket(
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
        double precio
) {
    @Override
    public String toString() {
        return "Ticket{" +
                "folio=" + folio +
                ", mesa=" + numeroMesa +
                ", silla=" + silla +
                ", cliente='" + nombreCliente + '\'' +
                ", alimento='" + nombreAlimento + '\'' +
                ", precio=" + precio +
                '}';
    }
}