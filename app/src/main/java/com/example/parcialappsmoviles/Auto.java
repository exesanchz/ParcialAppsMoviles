package com.example.parcialappsmoviles;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Auto")
public class Auto {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    public long id;
    @ColumnInfo(name="modelo")
    public String modelo;
    @ColumnInfo(name="usado")
    public Boolean usado;

    public Auto(String modelo, Boolean usado) {
        this.modelo = modelo;
        this.usado = usado;
    }
    @Override
    public String toString() {
        String estado;
        if(usado) estado = "USADO";
        else {
            estado = "NUEVO";
        }
        String condicionAuto = modelo + " - " + estado;
        return condicionAuto;
    }
    public String getModelo() {
        return modelo;
    }

    public Boolean getUsado() {
        return usado;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }
}
