package com.example.appalimentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appalimentos.db.AlimentoDatasource;
import com.example.appalimentos.model.Alimento;

public class DatosActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etTipo;
    private EditText etOrigen;
    private EditText etNutri;
    private EditText etFunc;

    AlimentoDatasource ads;

    private String nombre;
    private Alimento alim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        etNombre = findViewById(R.id.etNombreD);
        etTipo = findViewById(R.id.etTipoD);
        etOrigen = findViewById(R.id.etOrigenD);
        etNutri = findViewById(R.id.etNutriD);
        etFunc = findViewById(R.id.etFuncD);

        ads = new AlimentoDatasource(this);

        nombre = getIntent().getStringExtra("NOMBRE");
        alim = ads.consultarAlimento(nombre);

        if(alim == null) {
            mostrarMensaje(R.string.toast_datos_no_existe);
            finish();
        } else {
            etNombre.setText(alim.getNombre());
            etTipo.setText(alim.getTipo());
            etOrigen.setText(alim.getOrigen());
            etNutri.setText(alim.getNutrientes());
            etFunc.setText(alim.getFuncion());
        }

    }

    public void borrar(View v) {
        crear_dialogo().show();

    }

    public void volver(View v) {
        finish();
    }
    private Dialog crear_dialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(R.string.datos_borrar_confirmar);
        builder.setPositiveButton("Aceptar",    new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int resultado = ads.borrarAlimento(alim.getId());
                if(resultado == 1) {
                    mostrarMensaje(R.string.toast_datos_borrado);
                } else {
                    mostrarMensaje(R.string.toast_datos_borrado_error);
                }
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }


    public void mostrarMensaje(int recurso) {
        Toast t = Toast.makeText(this, recurso, Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0,0);
        t.show();
    }
}
