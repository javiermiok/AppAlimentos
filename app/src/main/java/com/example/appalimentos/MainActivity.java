package com.example.appalimentos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appalimentos.db.AlimentoDatasource;
import com.example.appalimentos.model.Alimento;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etTipo;
    private EditText etOrigen;
    private EditText etNutri;
    private EditText etFunc;

    AlimentoDatasource ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        etOrigen = findViewById(R.id.etOrigen);
        etNutri = findViewById(R.id.etNutri);
        etFunc = findViewById(R.id.etFunc);

        ads = new AlimentoDatasource(this);
    }

    public void registrar(View v) {
        String nombre = etNombre.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        String origen = etOrigen.getText().toString().trim();
        String nutri = etNutri.getText().toString().trim();
        String func = etFunc.getText().toString().trim();

        if(nombre.isEmpty() || tipo.isEmpty() || origen.isEmpty() || nutri.isEmpty() || func.isEmpty()) {
            mostrarMensaje(R.string.toast_main_campos_vacios);
        } else {
            Alimento alim = new Alimento(nombre, tipo, origen, nutri, func);
            long resultado = ads.insertarAlimento(alim);

            if(resultado != -1) {
                mostrarMensaje(R.string.toast_main_insertado);
                limpiar(v);
            } else {
                mostrarMensaje(R.string.toast_main_insertado_error);
            }

        }

    }

    public void consultar(View v) {
        String nombre = etNombre.getText().toString().trim();

        if(nombre.isEmpty()) {
            mostrarMensaje(R.string.toast_main_nombre_req);
        } else {
            Intent i = new Intent(this, DatosActivity.class);
            i.putExtra("NOMBRE", nombre);
            startActivity(i);
            limpiar(v);
        }
    }

    public void limpiar(View v) {
        etNombre.setText("");
        etTipo.setText("");
        etOrigen.setText("");
        etNutri.setText("");
        etFunc.setText("");
    }

    public void mostrarMensaje(int recurso) {
        Toast t = Toast.makeText(this, recurso, Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0,0);
        t.show();
    }
}
