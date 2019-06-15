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

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Aseguradora;
import mx.uv.lunix.transito.ws.pojos.Vehiculo;

public class RegistrarVehiculoActivity extends AppCompatActivity {

    private Vehiculo nuevo;
    private Aseguradora nueva;
    private EditText placa;
    private EditText marca;
    private EditText modelo;
    private EditText color;
    private EditText anio;
    private EditText numeropoliza;
    private EditText aseguradora;
    private Response resws;
    private Integer idConductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);
        placa = (EditText) findViewById(R.id.numerodePlacas);
        marca = (EditText) findViewById(R.id.marca);
        modelo = (EditText) findViewById(R.id.modelos);
        color = (EditText) findViewById(R.id.color);
        anio = (EditText) findViewById(R.id.año);
        numeropoliza = (EditText) findViewById(R.id.poliza);
        aseguradora = (EditText) findViewById(R.id.aseguradora);

    }

    public void cancelar(View v){finish();}

    private void registrarVehiculo(View v){
        if(validar() && isOnline()){
            nuevo = new Vehiculo();
            nueva = new Aseguradora();
            nuevo.setMarca(marca.getText().toString());
            nuevo.setModelo(modelo.getText().toString());
            nuevo.setAnio(Integer.parseInt(anio.getText().toString()));
            nuevo.setColor(color.getText().toString());
            nuevo.setPlacas(placa.getText().toString());
            nuevo.setIdaseguradora(nueva.getIdAseguradora());
            nuevo.setPolizaSeguro(numeropoliza.getText().toString());
            nueva.setNombre(aseguradora.getText().toString());
            nuevo.setIdcoductor(idConductor);
            WSPOSTRegistroVehiculoTask task = new WSPOSTRegistroVehiculoTask();
            task.execute(nuevo);
            WSPOSTRegitroAseguradoraTask taskAs = new WSPOSTRegitroAseguradoraTask();
            taskAs.execute(nueva);
        }
    }

    private void pasarParametrosdeUsuario(){
        Intent intent = getIntent();
        idConductor = intent.getIntExtra("idCoductor", 0);
    }

    private boolean validar(){
        boolean ok = true;
        if(marca.getText() == null || marca.getText().toString().isEmpty()){
            ok = false;
        }
        if(modelo.getText() == null || modelo.getText().toString().isEmpty()){
            ok = false;
        }
        if(anio.getText() == null || anio.getText().toString().isEmpty()){
            ok = false;
        }
        if(color.getText() == null || color.getText().toString().isEmpty()){
            ok = false;
        }
        if(placa.getText() == null || placa.getText().toString().isEmpty()){
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
        AlertDialog dialog = new AlertDialog.Builder(RegistrarVehiculoActivity.this).create();
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

    private class WSPOSTRegistroVehiculoTask extends AsyncTask<Object, String, String>{

        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.registroVehiculo(nuevo);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            resultadoRegistroVehiculo();
        }
    }

    private class WSPOSTRegitroAseguradoraTask extends AsyncTask<Object, String, String>{

        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.registrarAseguradora(nueva);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    private void resultadoRegistroVehiculo(){
        if(resws != null && !resws.isError() && resws.getResult() != null) {
            mostrarAlertDialog("Registro correcto","Se ha guardado correctamente el vehiculo");
        } else {
            mostrarAlertDialog("Error", resws.getResult());
        }
    }

}
