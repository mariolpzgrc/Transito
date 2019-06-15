package mx.uv.lunix.transito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        WSPOSTRegistroVehiculoTask task = new WSPOSTRegistroVehiculoTask();
        task.execute(nuevo);
        WSPOSTRegitroAseguradoraTask task = new WSPOSTRegistroAseguradoraTask();
        task.execute(nueva);
    }

    public void cancelar(View v){finish();}

    private void registrarVehiculo(View v){
        if(validar() && isOnline){
            nuevo = new Vehiculo();
            nueva = new Aseguradora();
            nuevo.setMarca(marca.getText().toString());
            nuevo.setModelo(modelo.getText().toString());
            nuevo.setAnio(Integer.parseInt(anio.getText().toString()));
            nuevo.setColor(color.getText().toString());
            nuevo.setPlacas(placa.getText().toString());


        }
    }
}
