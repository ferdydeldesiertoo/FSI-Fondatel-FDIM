package com.app.dao;

import java.util.List;

public interface DAO<T, K>{
    List<T> leerTodos();
    boolean insertar(T entidad);
    boolean actualizar(T entidad);
    boolean eliminar(K id);
}
