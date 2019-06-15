package mx.uv.lunix.transito;

import android.content.AsyncQueryHandler;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Vehiculo;

public class VehiculosActivity extends AppCompatActivity {

    private Conductor conductor;
    private Vehiculo vehiculo;
    private ListView listavehiculos;
    private List<Vehiculo> lista_vehiculos;
    private Response resws;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
        listavehiculos = (ListView) findViewById(R.id.list_vehiculos);
        listavehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detalleVehiculo(position);
            }
        });
        parametrosIntent();
        cargarVehiculos();
    }

    public void nuevoVehiculo(View v){
        Intent intent = new Intent(VehiculosActivity.this, RegistroActivity.class);
        intent.putExtra("idConductor", conductor.getIdConductor());
        startActivityForResult(intent, 0);
    }

    private void parametrosIntent(){
        Intent intent = getIntent();
        String json = intent.getStringExtra("json_conductor");
        conductor = new Gson().fromJson(json, Conductor.class);
        Toast.makeText(this, "Binvenido" + conductor.getNombre(), Toast.LENGTH_LONG).show();
    }

    private void cargarVehiculos(){
        if(isOnline()){
            WSGETVehiculoTask  task = new WSGETVehiculoTask();
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
        AlertDialog dialog = new AlertDialog.Builder(VehiculosActivity.this).create();
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

    private class WSGETVehiculoTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            resws = HttpUtils.getVehiculos(conductor);
            return null;
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            resultadoVehiculos();
        }
    }

    private void resultadoVehiculos(){
        if(resws != null && !resws.isError() && resws.getResult() != null){
            lista_vehiculos = new Gson().fromJson(resws.getResult(), new TypeToken<List<Vehiculo>>(){}.getType());
            List<String> vehiculos = new ArrayList<>();
            if(lista_vehiculos != null){
                for(Vehiculo vehiculo:lista_vehiculos){
                    vehiculos.add(vehiculo.getModelo() + ","+ vehiculo.getMarca() + ","  + vehiculo.getAnio());
                }
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculos);
            listavehiculos.setAdapter(itemsAdapter);
        } else{
            mostrarAlertDialog("Error", resws.getResult());
        }
    }

    private void detalleVehiculo(int index){
        Vehiculo vehiculo = VehiculosActivity.this.lista_vehiculos.get(index);
        Intent intent = new Intent(VehiculosActivity.this, RegistrarVehiculoActivity.class);
        intent.putExtra("idVehiculo", vehiculo.getPlacas().toString());
        intent.putExtra("idConductor", conductor.getIdConductor().toString());
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK) {
            cargarVehiculos();
        }
    }
}
