package com.app.dao;

import com.app.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente, String> {
    private final Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Cliente> leerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY nombre_cliente";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer clientes: " + e.getMessage());
        }

        return clientes;
    }

    @Override
    public boolean insertar(Cliente entidad) {
        String sql = "INSERT INTO clientes (codigo_cliente, nombre_cliente, rfc) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getCodigoCliente());
            ps.setString(2, entidad.getNombreCliente());
            ps.setString(3, entidad.getRfc());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Cliente entidad) {
        String sql = "UPDATE clientes SET nombre_cliente = ?, rfc = ? WHERE codigo_cliente = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombreCliente());
            ps.setString(2, entidad.getRfc());
            ps.setString(3, entidad.getCodigoCliente());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String codigoCliente) {
        String sql = "DELETE FROM clientes WHERE codigo_cliente = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoCliente);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo_cliente");
        String nombre = rs.getString("nombre_cliente");
        String rfc = rs.getString("rfc");

        return new Cliente(codigo, nombre, rfc);
    }
}