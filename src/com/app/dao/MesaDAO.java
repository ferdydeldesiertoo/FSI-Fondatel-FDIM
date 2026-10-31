package com.app.dao;

import com.app.model.Mesa;
import com.app.model.values.EstadoMesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO implements DAO<Mesa, Integer> {
    private final Connection con;

    public MesaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Mesa> leerTodos() {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesas ORDER BY numero";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mesas.add(mapearMesa(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer mesas: " + e.getMessage());
        }

        return mesas;
    }

    @Override
    public boolean insertar(Mesa entidad) {
        String sql = "INSERT INTO mesas (numero, estado_mesa, capacidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entidad.getNumero());
            ps.setString(2, entidad.getEstadoMesa().name());
            ps.setInt(3, entidad.getCapacidad());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar mesa: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Mesa entidad) {
        String sql = "UPDATE mesas SET estado_mesa = ?, capacidad = ? WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getEstadoMesa().name());
            ps.setInt(2, entidad.getCapacidad());
            ps.setInt(3, entidad.getNumero());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar mesa: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer numero) {
        String sql = "DELETE FROM mesas WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numero);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar mesa: " + e.getMessage());
            return false;
        }
    }

    private Mesa mapearMesa(ResultSet rs) throws SQLException {
        int numero = rs.getInt("numero");
        EstadoMesa estado = EstadoMesa.valueOf(rs.getString("estado_mesa").toUpperCase());
        int capacidad = rs.getInt("capacidad");

        return new Mesa(numero, estado, capacidad);
    }
}