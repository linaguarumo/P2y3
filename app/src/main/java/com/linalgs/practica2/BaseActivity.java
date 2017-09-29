package com.linalgs.practica2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        Blank2Fragment fragment = new Blank2Fragment();
        ft.add(R.id.frame, fragment).commit(); //se refiere a toda la interfaz

    }


    public void cambiar(View view){

        ft = fm.beginTransaction();
        BlankFragment fragment = new BlankFragment();
        ft.replace(R.id.frame, fragment).commit(); //se refiere a toda la interfaz
    }
}
