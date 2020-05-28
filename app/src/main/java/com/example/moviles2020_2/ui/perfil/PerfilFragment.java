package com.example.moviles2020_2.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviles2020_2.LoginViewModel;
import com.example.moviles2020_2.R;
import com.example.moviles2020_2.model.Usuario;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.ExecutionException;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    EditText etdni, etapellido, etnombre, etTelefono, etMail, etPass;
    Button btnToggleEditar;
    private int estado = 0;


    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        etdni = view.findViewById(R.id.etPFDNI);
        etapellido = view.findViewById(R.id.etPFApellido);
        etnombre = view.findViewById(R.id.etPFNombre);
        etTelefono = view.findViewById(R.id.etPFTelefono);
        etMail = view.findViewById(R.id.etPFMail);
        etPass = view.findViewById(R.id.etPFPass);
        btnToggleEditar = view.findViewById(R.id.btnToggleEditar);

            mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

            mViewModel.obtenerPerfil("Aca voy a pasar el parametro de la session");

        final Observer<Usuario> usuarioObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                etdni.setText(usuario.getDni() + "");
                etapellido.setText(usuario.getApellido() + "");
                etnombre.setText(usuario.getNombre() + "");
                etTelefono.setText(usuario.getTelefono() + "");
                etMail.setText(usuario.getMail() + "");
                etPass.setText(usuario.getPass() + "");
            }
        };
        mViewModel.getUsuario().observe(getViewLifecycleOwner(), usuarioObserver);

        btnToggleEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado==1){
                    estadoDos();
                    Usuario u = new Usuario(
                            1,
                            etdni.getText().toString(),
                            etapellido.getText().toString(),
                            etnombre.getText().toString(),
                            etTelefono.getText().toString(),
                            etMail.getText().toString(),
                            etPass.getText().toString()
                    );
                    mViewModel.setUsuario(u);

                }else{
                    estadoUno();
                }
            }
        });

        estadoUno();
        return view;
    }


    public void estadoUno() {
        etdni.setEnabled(false);
        etapellido.setEnabled(false);
        etnombre.setEnabled(false);
        etTelefono.setEnabled(false);
        etMail.setEnabled(false);
        etPass.setEnabled(false);
        etapellido.setEnabled(false);
        btnToggleEditar.setText("Editar");
        estado = 1;
    }

    public void estadoDos(){
        etdni.setEnabled(true);
        etapellido.setEnabled(true);
        etnombre.setEnabled(true);
        etTelefono.setEnabled(true);
        etMail.setEnabled(true);
        etPass.setEnabled(true);
        etapellido.setEnabled(true);
        btnToggleEditar.setText("Actualizar");
        estado = 2;

    }
}
