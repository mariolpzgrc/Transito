package mx.uv.lunix.transito;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ResourceCursorTreeAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import mx.uv.lunix.transito.ws.HttpUtils;
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
    private Response resws;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);
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

        nuevo = new Conductor();
        nuevo.setNombre(nombre.getText().toString());
        nuevo.setApellidoMaterno(apellidopaterno.getText().toString());
        nuevo.setApellidoMaterno(apellidoMaterno.getText().toString());
        nuevo.setFechaNacimiento(fechanacimiento.getText().toString());
        nuevo.setNumeroLicencia(numerolicencia.getText().toString());
        nuevo.setTelefono(numerocelular.getText().toString());
        nuevo.setPassword(contrasena.getText().toString());

        String url = "https://transitodesapps.azurewebsites.net/api/Conductors/";
        if(validar()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(RegistroActivity.this, "Conductor se registro correctamente", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistroActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    System.out.println(nuevo.getNombre());
                    System.out.println(nuevo.getApellidoPaterno());
                    System.out.println(nuevo.getApellidoMaterno());
                    System.out.println(nuevo.getFechaNacimiento());
                    System.out.println(nuevo.getNumeroLicencia());
                    System.out.println(nuevo.getTelefono());
                    System.out.println(nuevo.getPassword());

                    params.put("idconductor", "100");
                    params.put("nombre", nuevo.getNombre());
                    params.put("apellidoPaterno", nuevo.getApellidoPaterno());
                    params.put("apellidoMaterno", nuevo.getApellidoMaterno());
                    params.put("fechaNacimiento", nuevo.getFechaNacimiento().toString());
                    params.put("numeroLicencia", nuevo.getNumeroLicencia());
                    params.put("telefono", nuevo.getTelefono());
                    params.put("password", nuevo.getPassword());
                    params.put("conductorBitacoraAcceso", "hola");
                    params.put("reporte", null);
                    params.put("vehiculo", null);



                    return params;
                }
            };

            queue.add(stringRequest);
        }




    }

    private boolean validar(){
        boolean ok = true;
        if(nombre.getText()== null || nombre.getText().toString().isEmpty()){
            nombre.setError("Debes introducir tu(s) nombre(s)");
            ok = false;
        }

        if(apellidopaterno.getText() == null || apellidopaterno.getText().toString().isEmpty()){
            apellidopaterno.setError("Debes de introducir tu apellido");
            ok = false;
        }
        if(apellidoMaterno.getText() == null || apellidoMaterno.getText().toString().isEmpty()){
            apellidoMaterno.setError("Debes de introducir tu apellido");
            ok = false;
        }
        if(fechanacimiento.getText() == null || fechanacimiento.getText().toString().isEmpty()){
            fechanacimiento.setError("Debes de incluir tu fecha de nacimiento");
            ok = false;
        }
        if(numerolicencia.getText() == null || numerolicencia.getText().toString().isEmpty()){
            numerolicencia.setError("Debes de introducir tu número de licencia.");
            ok = false;
        }
        if(numerocelular.getText() == null || numerocelular.getText().toString().isEmpty()){
            numerocelular.setError("Debes de introducir tu número de celular");
            ok = false;
        }
        if(contrasena.getText() == null || contrasena.getText().toString().isEmpty()){
            contrasena.setError("Debes de introducir tu número de celular");
            ok = false;
        }
        return ok;
    }



}
