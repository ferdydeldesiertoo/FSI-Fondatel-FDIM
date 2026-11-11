package com.app.dao;

import com.app.model.values.Puesto;
import com.app.model.values.Turno;

public record EmpleadoSesion (
        String codigoEmpleado,
        String nombreEmpleado,
        Puesto puestoEmpleado,
        Turno turno,
        String username

){
}
