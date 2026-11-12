package com.app.model;

import com.app.model.values.Categoria;

public class Menu {
    private String nombreAlimento;
    private String descripcion;
    private double precio;
    private byte[] foto;
    private Categoria categoria;

    public Menu(String nombreAlimento, String descripcion, double precio, byte[] foto, Categoria categoria) {
        this.nombreAlimento = nombreAlimento;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoria = categoria;
    }

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                ", nombre='" + nombreAlimento + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}';
    }
}
