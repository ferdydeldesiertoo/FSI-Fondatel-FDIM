package com.app.model.values;

public enum EstadoAlimento {
    DISPONIBLE("Disponible"),
    AGOTADO("Agotado");

    private final String value;

    EstadoAlimento(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
