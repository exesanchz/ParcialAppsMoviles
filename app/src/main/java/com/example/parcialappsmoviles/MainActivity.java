package com.example.parcialappsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppRepositoryAuto.OnResultCallback {

    ToggleButton toggle;
    Button guardarAuto, mostrarAutos;
    EditText modelo;
    ListView listaAutos;
    Boolean usado;
    public AppRepositoryAuto repositorio;
    Context ctx;
    ArrayAdapter autoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        guardarAuto = (Button) findViewById(R.id.grd_Auto);
        modelo = (EditText) findViewById(R.id.modeloAuto);
        listaAutos = (ListView) findViewById(R.id.autosListView);
        mostrarAutos = (Button) findViewById(R.id.mostrarAutos);
        ctx = this;

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    usado = true;
                } else {
                    usado = false;
                }
            }
        });

        repositorio = new AppRepositoryAuto(this.getApplication(),this);

    guardarAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Auto auto = new Auto(modelo.getText().toString() , usado);
                repositorio.insertar(auto);
                //Instancia de auto persistida a traves de ROOM
                Toast.makeText(ctx, "Auto Guardado con Exito!", Toast.LENGTH_SHORT).show();
                modelo.setText(null);
                toggle.setChecked(false);
            }
        });
    mostrarAutos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            List<Auto> autos = repositorio.buscarTodos();
            ArrayList<Auto> ola = new ArrayList<Auto>();
            autoArrayAdapter = new ArrayAdapter<Auto>(ctx, android.R.layout.simple_list_item_1,autos);
            listaAutos.setAdapter(autoArrayAdapter);
        }
    });

    }


    @Override
    public void onResult(List result) {

    }
}