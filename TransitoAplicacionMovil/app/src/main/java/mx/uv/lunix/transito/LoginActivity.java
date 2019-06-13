package mx.uv.lunix.transito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private Button iniciarSesion;
    private Button registrase;
    private EditText telefono;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarSesion = findViewById(R.id.iniciarSesion);
        telefono = findViewById(R.id.telefono);
        password = findViewById(R.id.password);
        registrase = findViewById(R.id.crearCuenta);


    }

    public void registrarse(View view){
        Intent intent = new  Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}
