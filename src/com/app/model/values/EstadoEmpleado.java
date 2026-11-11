package com.app.model.values;

public enum EstadoEmpleado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String value;

    EstadoEmpleado(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
