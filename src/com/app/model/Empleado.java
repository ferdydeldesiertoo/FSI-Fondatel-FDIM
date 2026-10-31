package com.app.model;

import com.app.model.values.EstadoEmpleado;
import com.app.model.values.Puesto;
import com.app.model.values.Turno;

import java.util.Objects;

public class Empleado {
    private String codigoEmpleado;
    private String nombreEmpleado;
    private Puesto puesto;
    private Turno turno;
    private String username;
    private String nip;
    private EstadoEmpleado estadoEmpleado;

    public Empleado(String codigoEmpleado, String nombreEmpleado, Puesto puesto,
                    Turno turno, String username, String nip, EstadoEmpleado estadoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.puesto = puesto;
        this.turno = turno;
        this.username = username;
        this.nip = nip;
        this.estadoEmpleado = estadoEmpleado;
    }

    // Getters y Setters
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }

    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }

    public Puesto getPuesto() { return puesto; }
    public void setPuesto(Puesto puesto) { this.puesto = puesto; }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    public EstadoEmpleado getEstadoEmpleado() { return estadoEmpleado; }
    public void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) { this.estadoEmpleado = estadoEmpleado; }

    @Override
    public String toString() {
        return "Empleado{" +
                "codigo='" + codigoEmpleado + '\'' +
                ", nombre='" + nombreEmpleado + '\'' +
                ", puesto=" + puesto +
                ", turno=" + turno +
                ", estado=" + estadoEmpleado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return codigoEmpleado != null && codigoEmpleado.equals(empleado.codigoEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEmpleado);
    }
}