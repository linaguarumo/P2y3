package com.linalgs.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    private TextView tContraseña, tNombre;
    private String correoR, contraseñaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tContraseña =(TextView) findViewById(R.id.tContraseña);
        tNombre =(TextView) findViewById(R.id.tNombre);

        Bundle extras = getIntent().getExtras();
        //correoR = extras.getString("correo");
        //contraseñaR = extras.getString("contraseña");
        tNombre.setText(String.valueOf(extras.getString("correo")));
        tContraseña.setText(String.valueOf(extras.getString("contraseña")));
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.mPrincipal:
                intent = new Intent(PerfilActivity.this, MainActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("contraseña",contraseñaR);
                startActivity(intent);
                finish();
                break;
            case R.id.mCerrar:
                intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
