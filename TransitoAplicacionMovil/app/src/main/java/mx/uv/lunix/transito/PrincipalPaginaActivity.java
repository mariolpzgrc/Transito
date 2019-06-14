package mx.uv.lunix.transito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import mx.uv.lunix.transito.ws.pojos.Conductor;

public class PrincipalPaginaActivity extends AppCompatActivity {

    private String conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pagina);
    }

    public void irMiPerfil(View view){
        Intent intent = new Intent(PrincipalPaginaActivity.this, MiPerfilActivity.class);
        intent.putExtra("json_conductor", conductor);
        startActivity(intent);
    }

    public void registrarVehiculo(View view){
        Intent intent = new Intent(PrincipalPaginaActivity.this, VehiculosActivity.class);
        intent.putExtra("json_conductor", conductor);
        startActivity(intent);
    }

    public void crearReporte(View view){
        Intent intent = new Intent(PrincipalPaginaActivity.this, ReporteActivity.class);
        intent.putExtra("json_conductor", conductor);
        startActivity(intent);
    }

    public void verHistorial(View view){
        Intent intent = new Intent(PrincipalPaginaActivity.this, HistorialActivity.class);
        intent.putExtra("json_conductor", conductor);
        startActivity(intent);
    }

    public void verDictamen(View view){
        Intent intent = new Intent(PrincipalPaginaActivity.this, DictamenActivity.class);
        intent.putExtra("json_conductor", conductor);
        startActivity(intent);
    }

    private void parametrosIntent() {
        Intent intent = getIntent();
        conductor = intent.getStringExtra("json_conductor");
        Conductor conductores = new Gson().fromJson(conductor, Conductor.class);
        Toast.makeText(this, "Bienvenido " + conductores.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
