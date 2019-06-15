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

import com.google.gson.Gson;

import mx.uv.lunix.transito.ws.HttpUtils;
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
    private Response resws;

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
        if(validar() && isOnline()){
            nuevo = new Conductor();
            nuevo.setNombre(nombre.getText().toString());
            nuevo.setApellidoMaterno(apellidopaterno.getText().toString());
            nuevo.setApellidoMaterno(apellidoMaterno.getText().toString());
            nuevo.setFechaNacimiento(fechanacimiento.getText().toString());
            nuevo.setNumeroLicencia(numerolicencia.getText().toString());
            nuevo.setTelefono(numerocelular.getText().toString());
            nuevo.setPassword(contrasena.getText().toString());
            WSPOSTRegistroConductorTask task = new WSPOSTRegistroConductorTask();
            task.execute(nuevo);

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

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean online = (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected());
        if(!online) {
            mostrarAlertDialog("Sin conexión", "No se encontró ninguna conexión a Internet, conectase a alguna red para continuar utilizando la aplicación");
        }
        return online;
    }

    private void mostrarAlertDialog(String titulo, String mensaje) {
        AlertDialog dialog = new AlertDialog.Builder(RegistroActivity.this).create();
        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private class WSPOSTLoginTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            resws = HttpUtils.login(strings[0], strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            resultadoEntrar();
        }
    }
    private void resultadoEntrar() {
        if (resws != null && !resws.isError() && resws.getResult() != null) {
            if (resws.getResult().contains("idconductor")) {
                Intent intent = new Intent(RegistroActivity.this, PrincipalPaginaActivity.class);
                intent.putExtra("json_conductor", resws.getResult());
                startActivity(intent);
            }
        } else {
            mostrarAlertDialog("Error", "Número celular y/o contraseña incorrectas.");
        }
    }

    private class WSPOSTRegistroConductorTask extends AsyncTask<Object, String, String> {
        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.registroConductor((Conductor)objects[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resultadoRegistro();
        }
    }

    private void resultadoRegistro(){

    }

}
