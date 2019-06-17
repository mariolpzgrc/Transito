package mx.uv.lunix.transito;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import mx.uv.lunix.transito.ws.pojos.Aseguradora;
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

    private void crearReporte(){
        if(validar() && isOnline()){
            nuevo = new Reporte();
            consultarGps();
            nuevo.setNombreImplicado(nombreImplicado.getText().toString());
            nuevo.setModeloImplicado(modeloImplicado.getText().toString());
            nuevo.setMarcaImplicado(marcaImplicado.getText().toString());
            nuevo.setColorImplicado(colorImplicado.getText().toString());
            nuevo.setFechaSuceso(colorImplicado.getText().toString());
            aseguradora.setNombre(aseguradoraImplicado.getText().toString());
            nuevo.setIdAAeguradora(aseguradora.getIdAseguradora());


        }
    }

    private boolean validar() {
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean online = (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected());
        if(!online) {
            mostrarAlertDialog("Sin conexión", "No se encontró ninguna conexión a Internet, conectase a alguna red para continuar utilizando la aplicación");
        }
        return online;
    }

    private synchronized void consultarGps() {

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                latitud.setText("" + location.getLatitude());
                longitud.setText("" + location.getLongitude());
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {   }

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

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
}
