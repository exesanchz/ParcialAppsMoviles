package com.example.parcialappsmoviles;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface AutoDao {
    @Insert
    void insertar(Auto auto);

    @Delete
    void borrar(Auto auto);

    @Update
    void actualizar(Auto auto);

    @Query("SELECT * FROM Auto WHERE id = :id")
    Auto buscar(String id);

    @Query("SELECT * FROM Auto")
    List<Auto> buscarTodos();
}


