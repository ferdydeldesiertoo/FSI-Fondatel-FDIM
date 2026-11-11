package com.app.dao;

import com.app.model.Empleado;
import com.app.model.values.EstadoEmpleado;
import com.app.model.values.Puesto;
import com.app.model.values.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements DAO<Empleado, String> {
    private final Connection con;

    public EmpleadoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Empleado> leerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados ORDER BY nombre_empleado";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                empleados.add(mapearEmpleado(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al leer empleados: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public boolean insertar(Empleado entidad) {
        String sql = "INSERT INTO empleados (codigo_empleado, nombre_empleado, puesto, turno, username, nip, estado_empleado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getCodigoEmpleado());
            ps.setString(2, entidad.getNombreEmpleado());
            ps.setString(3, entidad.getPuesto().name());
            ps.setString(4, entidad.getTurno().name());
            ps.setString(5, entidad.getUsername());
            ps.setString(6, entidad.getNip());
            ps.setString(7, entidad.getEstadoEmpleado().name());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Empleado entidad) {
        String sql = "UPDATE empleados SET nombre_empleado = ?, puesto = ?, turno = ?, " +
                "username = ?, nip = ?, estado_empleado = ? WHERE codigo_empleado = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombreEmpleado());
            ps.setString(2, entidad.getPuesto().name());
            ps.setString(3, entidad.getTurno().name());
            ps.setString(4, entidad.getUsername());
            ps.setString(5, entidad.getNip());
            ps.setString(6, entidad.getEstadoEmpleado().name());
            ps.setString(7, entidad.getCodigoEmpleado());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    public EmpleadoSesion iniciarSesion(String username, String password) {
        String sql = "SELECT codigo_empleado, nombre_empleado, puesto, turno, username FROM empleados WHERE " +
                "username = ? AND nip = ? AND estado_empleado = 'ACTIVO'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            EmpleadoSesion sesion = null;

            while (rs.next()) {
                sesion = mapearEmpleadoSesion(rs);
            }

            return sesion;
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminar(String codigoEmpleado) {
        String sql = "DELETE FROM empleados WHERE codigo_empleado = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoEmpleado);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo_empleado");
        String nombre = rs.getString("nombre_empleado");
        Puesto puesto = Puesto.valueOf(rs.getString("puesto").toUpperCase());
        Turno turno = Turno.valueOf(rs.getString("turno").toUpperCase());
        String username = rs.getString("username");
        String nip = rs.getString("nip");
        EstadoEmpleado estado = EstadoEmpleado.valueOf(rs.getString("estado_empleado").toUpperCase());

        return new Empleado(codigo, nombre, puesto, turno, username, nip, estado);
    }

    private EmpleadoSesion mapearEmpleadoSesion(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo_empleado");
        String nombre = rs.getString("nombre_empleado");
        Puesto puesto = Puesto.valueOf(rs.getString("puesto").toUpperCase());
        Turno turno = Turno.valueOf(rs.getString("turno").toUpperCase());
        String username = rs.getString("username");

        return new EmpleadoSesion(codigo, nombre, puesto, turno, username);
    }


}