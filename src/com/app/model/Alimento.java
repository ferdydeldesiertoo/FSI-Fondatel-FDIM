package com.app.model;

import com.app.model.values.Categoria;
import com.app.model.values.EstadoAlimento;

public class Alimento {
    private int idAlimento;
    private String nombreAlimento;
    private String descripcion;
    private double precio;
    private byte[] foto;
    private Categoria categoria;
    private EstadoAlimento estado;

    public Alimento(int idAlimento, String nombre_alimento, String descripcion,
                    double precio, byte[] foto, Categoria categoria, EstadoAlimento estado) {
        this.idAlimento = idAlimento;
        this.nombreAlimento = nombre_alimento;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Alimento(String nombre_alimento, String descripcion,
                    double precio, byte[] foto, Categoria categoria, EstadoAlimento estado) {
        this.nombreAlimento = nombre_alimento;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoria = categoria;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdAlimento() { return idAlimento; }
    public void setIdAlimento(int idAlimento) { this.idAlimento = idAlimento; }

    public String getNombreAlimento() { return nombreAlimento; }
    public void setNombreAlimento(String nombreAlimento) { this.nombreAlimento = nombreAlimento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public EstadoAlimento getEstado() { return estado; }
    public void setEstado(EstadoAlimento estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Alimento{" +
                "id_alimento=" + idAlimento +
                ", nombre='" + nombreAlimento + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alimento)) return false;
        Alimento alimento = (Alimento) o;
        return idAlimento == alimento.idAlimento;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idAlimento);
    }
}