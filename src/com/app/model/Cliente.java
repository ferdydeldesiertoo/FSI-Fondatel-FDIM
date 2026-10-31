package com.app.model;

import java.util.Objects;

public class Cliente {
    private String codigoCliente;
    private String nombreCliente;
    private String rfc;

    public Cliente(String codigoCliente, String nombreCliente, String rfc) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.rfc = rfc;
    }

    public String getCodigoCliente() { return codigoCliente; }
    public void setCodigoCliente(String codigoCliente) { this.codigoCliente = codigoCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo='" + codigoCliente + '\'' +
                ", nombre='" + nombreCliente + '\'' +
                ", rfc='" + rfc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return codigoCliente != null && codigoCliente.equals(cliente.codigoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCliente);
    }
}