package com.app.model;

import com.app.model.values.EstadoOrden;

import java.time.LocalDateTime;

public class Orden {
    private int folio;
    private LocalDateTime fechaHora;
    private int silla;
    private String observaciones;
    private EstadoOrden estadoOrden;
    private String codigoCliente;
    private int numeroMesa;
    private int alimento;
    private String mesero;
    private String cocinero;

    public Orden(int folio, LocalDateTime fechaHora, int silla, String observaciones,
                 EstadoOrden estadoOrden, String codigoCliente, int numeroMesa,
                 int alimento, String mesero, String cocinero) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.silla = silla;
        this.observaciones = observaciones;
        this.estadoOrden = estadoOrden;
        this.codigoCliente = codigoCliente;
        this.numeroMesa = numeroMesa;
        this.alimento = alimento;
        this.mesero = mesero;
        this.cocinero = cocinero;
    }

    // Getters y Setters
    public int getFolio() { return folio; }
    public void setFolio(int folio) { this.folio = folio; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public int getSilla() { return silla; }
    public void setSilla(int silla) { this.silla = silla; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public EstadoOrden getEstadoOrden() { return estadoOrden; }
    public void setEstadoOrden(EstadoOrden estadoOrden) { this.estadoOrden = estadoOrden; }

    public String getCodigoCliente() { return codigoCliente; }
    public void setCodigoCliente(String codigoCliente) { this.codigoCliente = codigoCliente; }

    public int getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(int numeroMesa) { this.numeroMesa = numeroMesa; }

    public int getAlimento() { return alimento; }
    public void setAlimento(int alimento) { this.alimento = alimento; }

    public String getMesero() { return mesero; }
    public void setMesero(String mesero) { this.mesero = mesero; }

    public String getCocinero() { return cocinero; }
    public void setCocinero(String cocinero) { this.cocinero = cocinero; }

    @Override
    public String toString() {
        return "Orden{" +
                "folio=" + folio +
                ", fecha=" + fechaHora +
                ", estado=" + estadoOrden +
                ", cliente='" + codigoCliente + '\'' +
                ", mesa=" + numeroMesa +
                ", alimento=" + alimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orden)) return false;
        Orden orden = (Orden) o;
        return folio == orden.folio;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(folio);
    }
}