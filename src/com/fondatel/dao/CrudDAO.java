package com.fondatel.dao;

import java.util.List;

public interface CrudDAO<T, K>{
    List<T> leerTodos();
    boolean insertar(T entidad);
    boolean actualizar(T entidad);
    boolean eliminar(K id);
}
