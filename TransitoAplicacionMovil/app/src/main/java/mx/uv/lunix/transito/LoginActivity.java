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
import android.widget.Button;
import android.widget.EditText;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Responses;


public class LoginActivity extends AppCompatActivity {
    private Button iniciarSesion;
    private Button registrase;
    private String telefono;
    private String password;
    private Responses resws;

    private EditText txt_num;
    private EditText txt_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarSesion = findViewById(R.id.iniciarSesion);
        txt_num = (EditText) findViewById(R.id.telefono);
        txt_con = (EditText) findViewById(R.id.password);
        registrase = findViewById(R.id.crearCuenta);


    }

    public void registrarse(View view){
        startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
    }



    public void entrar(View v){
        if(validar() && isOnline()){
            telefono = txt_num.getText().toString();
            password = txt_con.getText().toString();
            WSPOSTLoginTask task = new WSPOSTLoginTask();
            task.execute(telefono, password);
        }
    }

    private boolean validar() {
        boolean ok = true;
        if(txt_num.getText() == null || txt_num.getText().toString().isEmpty()){
            txt_num.setError("Debes indtroducir el número celular para accesar");
            ok = false;
        }
        if(txt_con.getText() == null || txt_con.getText().toString().isEmpty()){
            txt_con.setError("Debes incluir la contraseña para accesar");
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
        AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                Intent intent = new Intent(LoginActivity.this, PrincipalPaginaActivity.class);
                intent.putExtra("json_conductor", resws.getResult());
                startActivity(intent);
            }
            else{
                mostrarAlertDialog("Error", "al pasar a la otra pantalla");
            }
        } else {
            mostrarAlertDialog("Error", "Usuario  y/o contraseña estan incorrectas");
        }
    }
}


