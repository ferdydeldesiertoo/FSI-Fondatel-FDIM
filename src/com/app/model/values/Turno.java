package com.app.model.values;

public enum Turno {
    MATUTINO("Matutino"),
    VESPERTINO("Vespertino"),
    NOCTURNO("Nocturno"),
    COMPLETO("Completo");

    private final String value;

    Turno(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
