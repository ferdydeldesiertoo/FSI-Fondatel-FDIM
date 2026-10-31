package com.app.model.values;

public enum Puesto {
    COCINERO("Cocinero"),
    MESERO("Mesero"),
    CAJERO("Cajero"),
    GERENTE("Gerente");

    private final String value;

    Puesto(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
