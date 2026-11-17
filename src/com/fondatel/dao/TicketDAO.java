package com.fondatel.dao;

import com.fondatel.model.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private final Connection con;

    public TicketDAO(Connection con) {
        this.con = con;
    }

    public List<Ticket> leerTodos() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY numero_mesa, silla, fecha_hora";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer tickets: " + e.getMessage());
        }
        return tickets;
    }

    public List<Ticket> leerPorMesa(int numeroMesa) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE numero_mesa = ? ORDER BY silla, fecha_hora";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numeroMesa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tickets.add(mapearTicket(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer tickets por mesa: " + e.getMessage());
        }
        return tickets;
    }

    public List<Ticket> leerPorMesaYSilla(int numeroMesa, int silla) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE numero_mesa = ? AND silla = ? ORDER BY fecha_hora";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numeroMesa);
            ps.setInt(2, silla);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tickets.add(mapearTicket(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer tickets por mesa y silla: " + e.getMessage());
        }
        return tickets;
    }

    public List<Integer> obtenerMesasPorCobrar() {
        List<Integer> mesas = new ArrayList<>();
        String sql = "SELECT DISTINCT numero_mesa FROM tickets ORDER BY numero_mesa";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                mesas.add(rs.getInt("numero_mesa"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mesas por cobrar: " + e.getMessage());
        }
        return mesas;
    }

    private Ticket mapearTicket(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("fecha_hora");
        LocalDateTime fechaHora = (ts != null) ? ts.toLocalDateTime() : null;

        return new Ticket(
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
                rs.getDouble("precio")
        );
    }
}