package mx.uv.lunix.transito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Conductor;

public class RegistroActivity extends AppCompatActivity {

    private Conductor nuevo;
    private EditText nombre;
    private EditText apellidopaterno;
    private EditText apellidoMaterno;
    private EditText fechanacimiento;
    private EditText numerolicencia;
    private EditText numerocelular;
    private EditText contrasena;
    private Response rews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText) findViewById(R.id.nombre);
        apellidopaterno = (EditText) findViewById(R.id.apellidoPaterno);
        apellidoMaterno = (EditText) findViewById(R.id.apellidoMaterno);
        fechanacimiento = (EditText) findViewById(R.id.fechaNacimiento);
        numerolicencia = (EditText) findViewById(R.id.licencia);
        numerocelular = (EditText) findViewById(R.id.telefono);
        contrasena = (EditText) findViewById(R.id.password);
    }

    public void cancelar(View v){finish();}

    public void registrarme(View view){
        if(validar() && isOnline){
            nuevo = new Conductor();
            nuevo.setNombre(nombre.getText().toString());
            nuevo.setApellidoMaterno(apellidopaterno.getText().toString());
            nuevo.setApellidoMaterno(apellidoMaterno.getText().toString());
            nuevo.setFechaNacimiento(fechanacimiento.getText().toString());
            

        }
    }

}
