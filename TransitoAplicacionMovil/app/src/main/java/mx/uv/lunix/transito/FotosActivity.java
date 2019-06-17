package mx.uv.lunix.transito;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import mx.uv.lunix.transito.ws.Responses;

public class FotosActivity extends AppCompatActivity {

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
}
