package com.fondatel.model.dto;

import com.fondatel.model.enums.Puesto;
import com.fondatel.model.enums.Turno;

public record EmpleadoSesion (
        String codigoEmpleado,
        String nombreEmpleado,
        Puesto puestoEmpleado,
        Turno turno,
        String username

){
}
