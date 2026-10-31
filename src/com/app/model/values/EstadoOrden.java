package com.app.model.values;

public enum EstadoOrden {
    ORDENADO("Ordenado"),
    PREPARANDO("Preparando"),
    ENTREGADO("Entregado"),
    DEVUELTO("Devuelto"),
    CANCELADO("Cancelado"),
    PAGADO("Pagado");

    private final String value;

    EstadoOrden(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
