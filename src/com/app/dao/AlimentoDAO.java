package com.app.dao;

import com.app.model.Alimento;
import com.app.model.values.Categoria;
import com.app.model.values.EstadoAlimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO implements DAO<Alimento, Integer> {
    private final Connection con;

    public AlimentoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Alimento> leerTodos() {
        List<Alimento> alimentos = new ArrayList<>();
        String sql = "SELECT * FROM alimentos ORDER BY categoria, nombre_alimento";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alimentos.add(mapearAlimento(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer alimentos: " + e.getMessage());
        }

        return alimentos;
    }

    @Override
    public boolean insertar(Alimento entidad) {
        String sql = "INSERT INTO alimentos (nombre_alimento, descripcion, precio, foto, categoria, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombreAlimento());
            ps.setString(2, entidad.getDescripcion());
            ps.setDouble(3, entidad.getPrecio());
            ps.setBytes(4, entidad.getFoto());
            ps.setString(5, entidad.getCategoria().name());
            ps.setString(6, entidad.getEstado().name());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar alimento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Alimento entidad) {
        String sql = "UPDATE alimentos SET nombre_alimento = ?, descripcion = ?, precio = ?, " +
                "foto = ?, categoria = ?, estado = ? WHERE id_alimento = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombreAlimento());
            ps.setString(2, entidad.getDescripcion());
            ps.setDouble(3, entidad.getPrecio());
            ps.setBytes(4, entidad.getFoto());
            ps.setString(5, entidad.getCategoria().name());
            ps.setString(6, entidad.getEstado().name());
            ps.setInt(7, entidad.getIdAlimento());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar alimento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer idAlimento) {
        String sql = "DELETE FROM alimentos WHERE id_alimento = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlimento);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar alimento: " + e.getMessage());
            return false;
        }
    }

    private Alimento mapearAlimento(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_alimento");
        String nombre = rs.getString("nombre_alimento");
        String descripcion = rs.getString("descripcion");
        double precio = rs.getDouble("precio");
        byte[] foto = rs.getBytes("foto");
        Categoria categoria = Categoria.valueOf(rs.getString("categoria").toUpperCase());
        EstadoAlimento estado = EstadoAlimento.valueOf(rs.getString("estado").toUpperCase());

        return new Alimento(id, nombre, descripcion, precio, foto, categoria, estado);
    }
}