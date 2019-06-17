package mx.uv.lunix.transito;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Dictamen;

public class VerDictamenActivity extends AppCompatActivity {

    private Dictamen dictamen;
    private Integer idReporte;
    private Integer folio;
    private TextView folioDictamen;
    private TextView fechaSucdic;
    private TextView reporteid;
    private TextView descricion;
    private TextView estatus;
    private Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dictamen);
        folioDictamen = (TextView) findViewById(R.id.foliodic);
        fechaSucdic = (TextView) findViewById(R.id.fechadic);
        reporteid = (TextView) findViewById(R.id.folioreport);
        estatus = (TextView) findViewById(R.id.estadodictamen);
        descricion = (TextView) findViewById(R.id.descripcion);
        paremetrosIntent();
        cargaDictamen();
    }

    private void cargaDictamen() {
        if(folio != null){
            folioDictamen.setEnabled(false);
            if(isOnline()){
                WSGETDictamenTask task = new WSGETDictamenTask();
                task.execute();
            }
        }
    }

    private void paremetrosIntent() {
        Intent intent = getIntent();
        idReporte = intent.getIntExtra("idReporte", 0);
        folio = intent.getIntExtra("folio", 0);
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
        AlertDialog dialog = new AlertDialog.Builder(VerDictamenActivity.this).create();
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

    private class WSGETDictamenTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            response = HttpUtils.getDictamenId(folio);
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resultadoDictameGET();
        }
    }

    private void resultadoDictameGET() {
        if(response != null && !response.isError() && response.getResult() != null) {
            if(response.getResult().contains("idReporte")){
                dictamen = new Gson().fromJson(response.getResult(), Dictamen.class);
                folioDictamen.setText(dictamen.getFolio());
                reporteid.setText(dictamen.getIdreporte());
                descricion.setText(dictamen.getDescripcion());
                fechaSucdic.setText(dictamen.getFechaDictamen());
                estatus.setText(dictamen.getEstado());
            }else{
                mostrarAlertDialog("Aviso", "No hay dictamen por el momento");
            }
        }else {
            mostrarAlertDialog("Error", "No se puede cargar el dictamen");
        }
    }
}
