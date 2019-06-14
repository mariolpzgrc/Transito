package mx.uv.lunix.transito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import mx.uv.lunix.transito.ws.pojos.Conductor;

public class MiPerfilActivity extends AppCompatActivity {

    private Conductor conductor;
    private TextView nombre;
    private TextView apellidopaterno;
    private TextView apellidomaterno;
    private TextView fechanacimiento;
    private TextView numerolicencia;
    private TextView numerocelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        nombre = findViewById(R.id.nombreLabel);
        apellidopaterno = findViewById(R.id.apellidoPaternoLabel);
        apellidomaterno = findViewById(R.id.apellidoMaternoLabel);
        fechanacimiento = findViewById(R.id.fechaNacimientoLabel);
        numerolicencia = findViewById(R.id.licenciaLabel);
        numerocelular = findViewById(R.id.telefonoLabel);
        parametrosIntent();
        llenarPerfil();
    }

    private void parametrosIntent() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("json_conductor");
        conductor = new Gson().fromJson(json, Conductor.class);
    }

    private void llenarPerfil(){
        nombre.setText(conductor.getNombre());
        apellidomaterno.setText(conductor.getApellidoPaterno());
        apellidomaterno.setText(conductor.getApellidoMaterno());
        fechanacimiento.setText(conductor.getFechaNacimiento());
        numerolicencia.setText(conductor.getNumeroLicencia());
        numerocelular.setText(conductor.getTelefono());
    }
}
