package com.app.dao;

import com.app.model.Menu;
import com.app.model.values.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private final Connection con;

    public MenuDAO(Connection con) {
        this.con = con;
    }

    public List<Menu> leerTodos() {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu ORDER BY categoria, nombre_alimento";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                menuList.add(mapearMenu(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el menú: " + e.getMessage());
        }

        return menuList;
    }

    public List<Menu> leerPorCategoria(Categoria categoria) {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE categoria = ? ORDER BY nombre_alimento";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    menuList.add(mapearMenu(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el menú por categoría: " + e.getMessage());
        }

        return menuList;
    }

    private Menu mapearMenu(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre_alimento");
        String descripcion = rs.getString("descripcion");
        double precio = rs.getDouble("precio");
        byte[] foto = rs.getBytes("foto");
        Categoria categoria = Categoria.valueOf(rs.getString("categoria").toUpperCase());

        return new Menu(nombre, descripcion, precio, foto, categoria);
    }
}