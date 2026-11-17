package com.fondatel.model;

import com.fondatel.model.enums.Categoria;

public record Menu(
        String nombreAlimento,
        String descripcion,
        double precio,
        byte[] foto,
        Categoria categoria
) {
    @Override
    public String toString() {
        return "Menu{" +
                "nombreAlimento='" + nombreAlimento + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}';
    }
}