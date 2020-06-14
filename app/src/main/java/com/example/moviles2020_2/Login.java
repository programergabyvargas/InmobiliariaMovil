package com.example.moviles2020_2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviles2020_2.model.GeneralData;
import com.example.moviles2020_2.model.Usuario;

public class Login extends AppCompatActivity {
    EditText etMail, etPass;
    Button btnIngresar;
    GeneralData uData;
    TextView tvError;
    LoginViewModel loginVM;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configView();
    }

    public void configView(){

        loginVM= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        tvError =findViewById(R.id.tvError);
        uData = new GeneralData();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                //loginVM.validar(etMail.getText().toString());
                ingresar(view);
            }
        });

        final Observer<String> errorObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                    tvError.setText(s);
            }
        };
        loginVM.getError().observe(this, errorObserver);

        final Observer<Usuario> usuarioObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                tvError.setText("Iniciando sesion...");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mail", usuario.getUsuario());
                //intent.putExtra("clave", usuario.getClave());
                startActivity(intent);
            }
        };
        loginVM.getUsuario().observe(this, usuarioObserver);

        final Observer<String> tokenObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mail", etMail.getText().toString());
                startActivity(intent);
            }
        };
        loginVM.getToken().observe(this, tokenObserver);
    }

    public void ingresar (android.view.View view){
        loginVM.ingresar(etMail.getText().toString() ,etPass.getText().toString());
    }
}


