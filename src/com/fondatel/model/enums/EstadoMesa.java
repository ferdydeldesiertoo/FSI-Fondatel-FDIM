package com.fondatel.model.enums;

public enum EstadoMesa {
    OCUPADA("Ocupada"),
    LIBRE("Libre"),
    RESERVADA("Reservada");

    private final String value;

    EstadoMesa(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
