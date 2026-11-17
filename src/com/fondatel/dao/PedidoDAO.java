package com.fondatel.dao;

import com.fondatel.model.Pedido;
import com.fondatel.model.enums.EstadoOrden;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private final Connection con;

    public PedidoDAO(Connection con) {
        this.con = con;
    }

    public List<Pedido> leerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos ORDER BY fecha_hora DESC";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pedidos.add(mapearPedido(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public List<Pedido> leerPorMesero(String codigoEmpleadoMesero) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE codigo_empleado_mesero = ? ORDER BY fecha_hora DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoEmpleadoMesero);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(mapearPedido(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer pedidos del mesero: " + e.getMessage());
        }
        return pedidos;
    }

    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("fecha_hora");
        LocalDateTime fechaHora = (ts != null) ? ts.toLocalDateTime() : null;
        EstadoOrden estado = EstadoOrden.valueOf(rs.getString("estado_orden"));

        return new Pedido(
                rs.getInt("folio"),
                fechaHora,
                rs.getInt("silla"),
                rs.getInt("numero_mesa"),
                rs.getString("codigo_cliente"),
                rs.getString("nombre_cliente"),
                rs.getString("codigo_empleado_mesero"),
                rs.getString("nombre_empleado"),
                rs.getInt("id_alimento"),
                rs.getString("nombre_alimento"),
                rs.getDouble("precio"),
                rs.getString("observaciones"),
                estado
        );
    }
}