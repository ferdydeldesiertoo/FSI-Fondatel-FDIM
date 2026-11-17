package com.fondatel.dao;

import com.fondatel.model.Comanda;
import com.fondatel.model.enums.EstadoOrden;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAO {
    private final Connection con;

    public ComandaDAO(Connection con) {
        this.con = con;
    }

    public List<Comanda> leerTodos() {
        List<Comanda> comandas = new ArrayList<>();
        String sql = "SELECT * FROM comandas";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                comandas.add(mapearComanda(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer comandas: " + e.getMessage());
        }
        return comandas;
    }

    private Comanda mapearComanda(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("fecha_hora");
        LocalDateTime fechaHora = (ts != null) ? ts.toLocalDateTime() : null;
        EstadoOrden estado = EstadoOrden.valueOf(rs.getString("estado_orden").toUpperCase());

        return new Comanda(
                rs.getInt("folio"),
                fechaHora,
                rs.getString("observaciones"),
                estado,
                rs.getInt("id_alimento"),
                rs.getString("nombre_alimento"),
                rs.getString("descripcion")
        );
    }
}