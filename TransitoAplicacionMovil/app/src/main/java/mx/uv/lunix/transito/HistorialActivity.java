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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Responses;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Reporte;

public class HistorialActivity extends AppCompatActivity {

    private Conductor conductor;
    private Reporte reporte;
    private ListView lst_reportes;
    private List<Reporte> lista_reportes;
    private Responses resws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        lst_reportes =(ListView) findViewById(R.id.list_reportes);
        lst_reportes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detalleReporte(position);
            }
        });
        parametrosIntent();
        cargarReportes();
    }

    private void parametrosIntent() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("json_conductor");
        conductor = new Gson().fromJson(json, Conductor.class);
        Toast.makeText(this, "Bienvenido " + conductor.getNombre(), Toast.LENGTH_LONG).show();
    }

    private void detalleReporte(int index) {
        Reporte  reporte  = HistorialActivity.this.lista_reportes.get(index);
        Intent intent = new Intent(HistorialActivity.this, ReporteActivity.class);
        intent.putExtra("idReporte", reporte.getIdreporte());
        intent.putExtra("idConductor", reporte.getIdConductor());
        startActivityForResult(intent, 0);

    }

    private void ResultadoResportes(){
        if(resws != null && !resws.isError() && resws.getResult() != null) {
            lista_reportes = new Gson().fromJson(resws.getResult(), new TypeToken<List<Reporte>>(){}.getType());
            List<String> reportes = new ArrayList<>();
            if(lista_reportes != null) {
                for(Reporte reporte: lista_reportes) {
                    reportes.add(reporte.getFechaSuceso() +"," + reporte.getLugar() + "," + reporte.getNumeroPlacasImplicado());
                }
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, reportes);
            lst_reportes.setAdapter(itemsAdapter);
        } else {
            mostrarAlertDialog("Error", resws.getResult());
        }    }

    private void cargarReportes(){
        if(isOnline()){
            WSGETReportesTask task  = new WSGETReportesTask();
            task.execute();
        }
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
        AlertDialog dialog = new AlertDialog.Builder(HistorialActivity.this).create();
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

    private class WSGETReportesTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            resws = HttpUtils.getReportes(conductor);
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK) {
            cargarReportes();
        }
    }
}
