package com.linalgs.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.lang.reflect.Array;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
    private String correoR, contraseñaR, edcorreo,edcont;
    private EditText eCorreo,eContraseña;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private int optLog;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        eCorreo = (EditText) findViewById(R.id.eCorreo) ;
        eContraseña =(EditText) findViewById(R.id.eContraseña);
        //facebook----------------------------------------------------
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainActivity();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Login error", Toast.LENGTH_SHORT).show();

            }
        });
        //----------------end facebook--------------------
    }

    public void iniciar (View view){
        optLog=3;
        edcorreo = eCorreo.getText().toString();
        edcont = eContraseña.getText().toString();
        if (edcorreo.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese el correo",Toast.LENGTH_SHORT).show();
        }
        else if (edcont.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese la contraseña",Toast.LENGTH_SHORT).show();
        }else if (!(correoR.equals(edcorreo))){
            Toast.makeText(getApplicationContext(),"Ingrese el correo adecuado", Toast.LENGTH_SHORT).show();
        }else if (!(contraseñaR.equals(edcont))){
            Toast.makeText(getApplicationContext(),"Ingrese contraseña correcta", Toast.LENGTH_SHORT).show();
        }else if (!(correoR.equals(edcorreo))&&!(contraseñaR.equals(edcont))){
            return;
        }
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("correo",correoR);
        intent.putExtra("contraseña",contraseñaR);
        startActivity(intent);
        //startActivityForResult(intent, 1234);
        finish();

    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1234 &&  resultCode == RESULT_OK){
            correoR = data.getExtras().getString("correo");
            contraseñaR = data.getExtras().getString("contraseña");
            Toast.makeText(this,correoR,Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void Registrese(View view){
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivityForResult(intent, 1234);
    }

    private void goMainActivity() {
        //me trae las preferencias
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        //para editar
        editor =prefs.edit();

        //almacenamos el valor de optLog
        editor.putLong("optLog",optLog);
        //este comando siempre es necesario
        editor.commit();

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        // intent.putExtra("mail",RegMail);
        // intent.putExtra("pass",RegPass);
        //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }


}
