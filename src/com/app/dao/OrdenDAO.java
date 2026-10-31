package com.app.dao;

import com.app.model.Orden;
import com.app.model.values.EstadoOrden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenDAO implements DAO<Orden, Integer> {
    private final Connection con;

    public OrdenDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Orden> leerTodos() {
        List<Orden> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenes ORDER BY fecha_hora DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ordenes.add(mapearOrden(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer órdenes: " + e.getMessage());
        }

        return ordenes;
    }

    @Override
    public boolean insertar(Orden entidad) {
        String sql = "INSERT INTO ordenes (fecha_hora, silla, observaciones, estado_orden, " +
                "codigo_cliente, numero_mesa, id_alimento, codigo_empleado_mesero, codigo_empleado_cocinero) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(entidad.getFechaHora()));
            ps.setInt(2, entidad.getSilla());
            ps.setString(3, entidad.getObservaciones());
            ps.setString(4, entidad.getEstadoOrden().name());
            ps.setString(5, entidad.getCodigoCliente());
            ps.setInt(6, entidad.getNumeroMesa());
            ps.setInt(7, entidad.getAlimento());
            ps.setString(8, entidad.getMesero());
            ps.setString(9, entidad.getCocinero());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar orden: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Orden entidad) {
        String sql = "UPDATE ordenes SET fecha_hora = ?, silla = ?, observaciones = ?, estado_orden = ?, " +
                "codigo_cliente = ?, numero_mesa = ?, id_alimento = ?, codigo_empleado_mesero = ?, " +
                "codigo_empleado_cocinero = ? WHERE folio = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(entidad.getFechaHora()));
            ps.setInt(2, entidad.getSilla());
            ps.setString(3, entidad.getObservaciones());
            ps.setString(4, entidad.getEstadoOrden().name());
            ps.setString(5, entidad.getCodigoCliente());
            ps.setInt(6, entidad.getNumeroMesa());
            ps.setInt(7, entidad.getAlimento());
            ps.setString(8, entidad.getMesero());
            ps.setString(9, entidad.getCocinero());

            // El folio va al final para el WHERE
            ps.setInt(10, entidad.getFolio());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar orden: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer folio) {
        String sql = "DELETE FROM ordenes WHERE folio = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, folio);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar orden: " + e.getMessage());
            return false;
        }
    }

    private Orden mapearOrden(ResultSet rs) throws SQLException {
        int folio = rs.getInt("folio");

        LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

        int silla = rs.getInt("silla");
        String observaciones = rs.getString("observaciones");
        EstadoOrden estado = EstadoOrden.valueOf(rs.getString("estado_orden").toUpperCase());
        String codigoCliente = rs.getString("codigo_cliente");
        int numeroMesa = rs.getInt("numero_mesa");
        int alimento = rs.getInt("id_alimento");
        String mesero = rs.getString("codigo_empleado_mesero");
        String cocinero = rs.getString("codigo_empleado_cocinero");

        return new Orden(folio, fechaHora, silla, observaciones, estado, codigoCliente, numeroMesa, alimento, mesero, cocinero);
    }
}