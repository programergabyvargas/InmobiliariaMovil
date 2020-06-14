package com.example.moviles2020_2.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
import com.example.moviles2020_2.model.Propietario;
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

            mViewModel.obtenerPerfil();

        final Observer<Propietario> propietarioObserver = new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {

                etdni.setText(propietario.getDni() + "");
                etapellido.setText(propietario.getApellido() + "");
                etnombre.setText(propietario.getNombre() + "");
                etTelefono.setText(propietario.getTelefono() + "");
                etMail.setText(propietario.getMail() + "");
                etPass.setText(propietario.getClave() + "");
                Log.d("salida ", etPass.getText()+"");
                // setear valores a null
            }
        };
        mViewModel.getPropietario().observe(getViewLifecycleOwner(), propietarioObserver);

        btnToggleEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado==1){
                    estadoEditable();
                    Propietario u = new Propietario(
                            etdni.getText().toString(),
                            etapellido.getText().toString(),
                            etnombre.getText().toString(),
                            etTelefono.getText().toString(),
                            etMail.getText().toString(),
                            etPass.getText().toString()
                    );
                    mViewModel.setUsuario(u);
                    //
                    Log.d("Llamar seteables", "presione editar");

                }else{
                    estadoNoEditable();
                    Log.d("Llamar a actualizar", "presione actualizar");
                    Propietario a = new Propietario();

                  //  MutableLiveData<Propietario> aux  = (MutableLiveData<Propietario>) mViewModel.getPropietario();
                   // int id= aux.getValue().getId();
                   // a.setId(id);
                    a.setApellido(etapellido.getText().toString());
                    a.setNombre(etnombre.getText().toString());
                    a.setDni(etdni.getText().toString());
                    a.setTelefono(etTelefono.getText().toString());
                    a.setMail(etMail.getText().toString());
                    a.setClave(etPass.getText().toString());

                    mViewModel.actualizar(a);
                }
            }
        });

        estadoNoEditable();
        return view;
    }


    public void estadoNoEditable() {
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

    public void estadoEditable(){
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
