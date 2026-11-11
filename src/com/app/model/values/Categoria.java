package com.app.model.values;

public enum Categoria {
    ENTRADA("Entrada"),
    PLATO_PRINCIPAL("Plato Principal"),
    POSTRE("Postre"),
    BEBIDA("Bebida");

    private final String value;

    Categoria(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
