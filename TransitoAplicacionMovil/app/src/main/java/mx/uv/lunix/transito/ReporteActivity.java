package mx.uv.lunix.transito;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Aseguradora;
import mx.uv.lunix.transito.ws.pojos.Evidencia;
import mx.uv.lunix.transito.ws.pojos.Reporte;

public class ReporteActivity extends AppCompatActivity {

    private EditText longitud;
    private EditText latitud;
    private EditText lugar;
    private EditText nombreImplicado;
    private EditText modeloImplicado;
    private EditText placasImplicado;
    private EditText colorImplicado;
    private EditText marcaImplicado;
    private EditText fechaSuceso;
    private EditText aseguradoraImplicado;
    private EditText numeroPolizaImplicado;
    private Aseguradora aseguradora;
    private Reporte nuevo;
    private double longitude;
    private double latitude;
    private Evidencia evidencias;
    private Integer idConductor;
    private Response resws;
    private  Integer idEvidencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        longitud =(EditText) findViewById(R.id.longitud);
        latitud = (EditText) findViewById(R.id.latitud);
        lugar = (EditText) findViewById(R.id.lugar);
        nombreImplicado = (EditText) findViewById(R.id.nombreImplicado);
        modeloImplicado = (EditText) findViewById(R.id.modeloImplicado);
        marcaImplicado = (EditText) findViewById(R.id.marcaImplicado);
        placasImplicado = (EditText) findViewById(R.id.placaImplicado);
        colorImplicado = (EditText) findViewById(R.id.colorImplicado);
        numeroPolizaImplicado = (EditText) findViewById(R.id.polizaImplicado);
        aseguradoraImplicado = (EditText) findViewById(R.id.aseguradoraImp);
        fechaSuceso = (EditText) findViewById(R.id.fechasuc);
    }


    private synchronized void consultarUbicacion(View view) {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
         longitude = location.getLongitude();
         longitud.setText((int) longitude);
         latitude = location.getLatitude();
         latitud.setText((int) latitude);
    }

    public void SubirFotos(View v) {
        Intent intent = new Intent(ReporteActivity.this, FotosActivity.class);
        intent.putExtra("idRvidencia", evidencias.getIdevidencia().toString());
        startActivityForResult(intent, 0);
    }


    private void crearReporte(View view){
        if(validar() && isOnline()){
            nuevo = new Reporte();
            nuevo.setLongitud(longitude);
            nuevo.setLatitud(latitude);
            nuevo.setNombreImplicado(nombreImplicado.getText().toString());
            nuevo.setModeloImplicado(modeloImplicado.getText().toString());
            nuevo.setMarcaImplicado(marcaImplicado.getText().toString());
            nuevo.setColorImplicado(colorImplicado.getText().toString());
            nuevo.setFechaSuceso(colorImplicado.getText().toString());
            nuevo.setIdConductor(idConductor);
            aseguradora.setNombre(aseguradoraImplicado.getText().toString());
            nuevo.setIdAAeguradora(aseguradora.getIdAseguradora());
            WSPOSTReporteTask task = new WSPOSTReporteTask();
            task.execute(nuevo);


        }
    }

    private boolean validar() {
        boolean ok  = true;

        if(longitud.getText() == null || longitud.getText().toString().isEmpty()){
            ok = false;
        }
        if(latitud.getText() == null || latitud.getText().toString().isEmpty()){
            ok = false;
        }
        if(lugar.getText() == null || lugar.getText().toString().isEmpty()){
            ok = false;
        }
        if(nombreImplicado.getText() == null || nombreImplicado.getText().toString().isEmpty()){
            ok = false;
        }
        if(modeloImplicado.getText() == null || modeloImplicado.getText().toString().isEmpty() ){
            ok=false;
        }
        if(marcaImplicado.getText() == null || marcaImplicado.getText().toString().isEmpty()){
            ok = false;
        }
        if(colorImplicado.getText() == null || colorImplicado.getText().toString().isEmpty()){
            ok = false;
        }
        if(aseguradoraImplicado.getText() == null || aseguradoraImplicado.getText().toString().isEmpty()){
            ok = false;
        }
        return ok;
    }

    private void pasarParametrosdeUsuario(){
        Intent intent = getIntent();
        idConductor = intent.getIntExtra("idCoductor", 0);
    }

    private void parametrosIntent(){
        Intent intent = getIntent();
        String json = intent.getStringExtra("json_evidencias");
        idEvidencias = intent.getIntExtra("idEvidencias", 0);
        evidencias = new Gson().fromJson(json, Evidencia.class);

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
        AlertDialog dialog = new AlertDialog.Builder(ReporteActivity.this).create();
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

    private class WSPOSTReporteTask extends AsyncTask<Object, String, String> {

        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.crearReporte(nuevo);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            resulatadoCrearReporte();
        }
    }

    private class WSPOSTRegitroAseguradoraTask extends AsyncTask<Object, String, String> {

        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.registrarAseguradora(aseguradora);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    private void resulatadoCrearReporte(){
        if(resws != null && !resws.isError() && resws.getResult() != null) {
            mostrarAlertDialog("Registro correcto","Se ha mandado conrrectamente el reporte");
        } else {
            mostrarAlertDialog("Error", resws.getResult());
        }
    }
}
