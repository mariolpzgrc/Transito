package mx.uv.lunix.transito;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import mx.uv.lunix.transito.ws.HttpUtils;
import mx.uv.lunix.transito.ws.Response;
import mx.uv.lunix.transito.ws.pojos.Evidencia;

public class FotosActivity extends AppCompatActivity {

    private static final String IMAGENESCOMPLETAS = "Se lleno el numero maximo de fotos : 8";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private boolean[] listafotos;
    private Evidencia nuevo;
    private ImageView foto1;
    private ImageView foto2;
    private ImageView foto3;
    private ImageView foto4;
    private ImageView foto5;
    private ImageView foto6;
    private ImageView foto7;
    private ImageView foto8;
    private Response resws;
    private Integer idEvidencia;
    private byte[] byteArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        foto1 = (ImageView) findViewById(R.id.imageView1);
        foto2 = (ImageView) findViewById(R.id.imageView2);
        foto3 = (ImageView) findViewById(R.id.imageView3);
        foto4 = (ImageView) findViewById(R.id.imageView4);
        foto5 = (ImageView) findViewById(R.id.imageView5);
        foto6 = (ImageView) findViewById(R.id.imageView6);
        foto7 = (ImageView) findViewById(R.id.imageView7);
        foto8 = (ImageView) findViewById(R.id.imageView8);


    }

    private void pasarparametros(){
        Intent intent = getIntent();
        idEvidencia = intent.getIntExtra("idEvidencia", 0);
    }

    public void cancelar(View v){finish();}

    private void tomarfotos(View view) {
        if (hayEspacioFotos()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Toast.makeText(this, IMAGENESCOMPLETAS, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hayEspacioFotos() {
        if(listafotos[7]){
            return false;
        }else{
            return true;
        }
    }

    private void limpiarfotos(View view){
        foto1.setImageResource(R.drawable.image_not_found);
        foto2.setImageResource(R.drawable.image_not_found);
        foto3.setImageResource(R.drawable.image_not_found);
        foto4.setImageResource(R.drawable.image_not_found);
        foto5.setImageResource(R.drawable.image_not_found);
        foto6.setImageResource(R.drawable.image_not_found);
        foto7.setImageResource(R.drawable.image_not_found);
        foto8.setImageResource(R.drawable.image_not_found);

        for (int i =0; i < 8; i++){
            listafotos[i] = false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                llenarCamposImagen(bitmap);

            }
        }
    }

    private void llenarCamposImagen(Bitmap bitmap) {
        for (int i = 0; i < 8; i++) {
            if (!listafotos[i]) {
                switch (i) {
                    case 0:
                        foto1.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 1:
                        foto2.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 2:
                        foto3.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 3:
                        foto4.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 4:
                        foto5.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 5:
                        foto6.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 6:
                        foto7.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                    case 7:
                        foto8.setImageBitmap(bitmap);
                        listafotos[i] = true;
                        break;
                }
                break;
            }
        }
    }

    private void subirFotos(View view){

        if(validar() || isOnline()){
            nuevo = new Evidencia();
            nuevo.setIdevidencia(idEvidencia);
            nuevo.setFotoDerecha1(byteArray);
            nuevo.setFotoDerecha2(byteArray);
            nuevo.setFotoIzquierda1(byteArray);
            nuevo.setFotoIzquierda2(byteArray);
            nuevo.setFotoFrontal1(byteArray);
            nuevo.setFotoFrontal2(byteArray);
            nuevo.setFotoTrasera1(byteArray);
            nuevo.setFotoTrasera2(byteArray);

            WSPOSTFotosTask task = new WSPOSTFotosTask();
            task.execute(nuevo);
        }

    }

    private boolean validar() {
         boolean ok = true;
        if(foto1.getDrawable() == null){
            ok = false;
        }
        if(foto2.getDrawable() == null) {
            ok = false;
        }
        if(foto3.getDrawable() == null) {
            ok = false;
        }
        if(foto4.getDrawable() == null) {
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
        AlertDialog dialog = new AlertDialog.Builder(FotosActivity.this).create();
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

    private class WSPOSTFotosTask extends AsyncTask<Object, String, String>{

        @Override
        protected String doInBackground(Object... objects) {
            resws = HttpUtils.enviarEvidencias(nuevo);
            return null;
        }
    }
}
