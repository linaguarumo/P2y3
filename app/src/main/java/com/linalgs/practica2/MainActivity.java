package com.linalgs.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private String correoR, contraseñaR;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            correoR = extras.getString("correo");
            contraseñaR = extras.getString("contraseña");
        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.mPerfil:
                intent = new Intent(MainActivity.this, PerfilActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("contraseña",contraseñaR);
                startActivity(intent);
                finish();
                break;
            case R.id.mCerrar:
                //me trae las preferencias
                prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                //para editar
                editor =prefs.edit();

                editor.putLong("optLog",0);
                editor.commit();

                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
