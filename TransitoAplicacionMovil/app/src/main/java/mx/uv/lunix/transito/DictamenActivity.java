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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Dictamen;
import mx.uv.lunix.transito.ws.pojos.Reporte;

public class DictamenActivity extends AppCompatActivity {

    private Conductor conductor;
    private Dictamen dictamen;
    private Reporte reporte;
    private ListView lst_dictamenes;
    private List<Dictamen> lista_dictamen;
    private Response resws;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictamen);
        lst_dictamenes = (ListView) findViewById(R.id.list_dictamen);
        lst_dictamenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detalleDictamen(position);
            }
        });
        parametrosIntent();
        cargarDictamenes();
    }

    private void cargarDictamenes() {
        if(isOnline()){
            WSGETDictamenTask task = new WSGETDictamenTask();
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
        AlertDialog dialog = new AlertDialog.Builder(DictamenActivity.this).create();
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

    private void parametrosIntent() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("json_conductor");
        String jsonR = intent.getStringExtra("json_Reporte");
    }

    private void detalleDictamen(int index) {
        Dictamen dictamen = DictamenActivity.this.lista_dictamen.get(index);
        Intent intent = new Intent(DictamenActivity.this, VerDictamenActivity.class);
        intent.putExtra("folio", dictamen.getFolio());
        intent.putExtra("idReporte", reporte.getIdreporte());
        startActivityForResult(intent, 0);
    }

    private class WSGETDictamenTask extends AsyncTask<String, String,String>{

        @Override
        protected String doInBackground(String... strings) {
            resws = HttpUtils.getDictamenten(reporte);
            return null;
        }
        protected  void onPostExcute(String s){
            super.onPostExecute(s);
            resultadoDictamen();
        }
    }

    private void resultadoDictamen() {
        if(resws != null && !resws.isError() && resws.getResult() != null){
            lista_dictamen = new Gson().fromJson(resws.getResult(), new TypeToken<List<Dictamen>>(){}.getType());
            List<String> dictamenes = new ArrayList<>();
            if(lista_dictamen != null){
                for(Dictamen dictamen : lista_dictamen){
                    dictamenes.add(dictamen.getFolio() + "," + dictamen.getFechaDictamen() + ", " + dictamen.getFechaDictamen());
                }
            }
            ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dictamenes);
            lst_dictamenes.setAdapter(itemAdapter);
        }else{
            mostrarAlertDialog("Error", resws.getResult());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK) {
            cargarDictamenes();
        }
    }
}
