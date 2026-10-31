package com.app.model;

import com.app.model.values.EstadoMesa;

import java.util.Objects;

public class Mesa {
    private int numero;
    private EstadoMesa estadoMesa;
    private int capacidad;

    public Mesa(int numero, EstadoMesa estado_mesa, int capacidad) {
        this.numero = numero;
        this.estadoMesa = estado_mesa;
        this.capacidad = capacidad;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public EstadoMesa getEstadoMesa() { return estadoMesa; }
    public void setEstadoMesa(EstadoMesa estadoMesa) { this.estadoMesa = estadoMesa; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() {
        return "Mesa{" +
                "numero=" + numero +
                ", estado=" + estadoMesa +
                ", capacidad=" + capacidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mesa)) return false;
        Mesa mesa = (Mesa) o;
        return numero == mesa.numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}