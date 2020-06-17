package com.example.moviles2020_2.ui.propiedades;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.icu.lang.UProperty;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviles2020_2.R;
import com.example.moviles2020_2.model.Propiedad;
import com.example.moviles2020_2.model.Usuario;

public class PropiedadDetailFragment extends Fragment {

    private PropiedadViewModel mViewModel;
    TextView tvPropiedadId, etDireccion, etAmbientes, etTipo, etUso, etPrecio, etDisponible;
    View view;
    Button btnToggleEditar;
    private int estado = 0;

    public static PropiedadDetailFragment newInstance() {
        return new PropiedadDetailFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {
            mViewModel = ViewModelProviders.of(this).get(PropiedadViewModel.class);
            view = inflater.inflate(R.layout.propiedad_detail_fragment, container, false);
            int propiedad = (int) getArguments().getInt("propiedadId");
            mViewModel.obtenerPropiedad(propiedad);

            etDireccion = view.findViewById(R.id.etDireccion);
            etAmbientes = view.findViewById(R.id.etAmbientes);
            etTipo = view.findViewById(R.id.etTipo);
            etUso = view.findViewById(R.id.etUso);
            etPrecio = view.findViewById(R.id.etPrecio);
            etDisponible = view.findViewById(R.id.etDisponible);
            btnToggleEditar = view.findViewById(R.id.btnToggleEditar);


            final Observer<Propiedad> propiedadObserver = new Observer<Propiedad>() {
                @Override
                public void onChanged(Propiedad propiedad) {
                    try {
                        etDireccion.setText(propiedad.getDireccion()+"");
                        etAmbientes.setText(propiedad.getAmbientes()+"");
                        etTipo.setText(propiedad.getTipo()+"");
                        etUso.setText(propiedad.getUso()+"");
                        etPrecio.setText(propiedad.getPrecio()+"");

                            if (propiedad.getDisponible()){
                                etDisponible.setText("Si");
                             }else {
                                etDisponible.setText("No");
                             }

                        }catch (Exception e){
                        Log.d("verPropiedadDetail", e.getMessage());
                        Log.d("verPropiedadDetail", e.getCause().toString());
                    }
                }
            };
            mViewModel.getPropiedad().observe(getViewLifecycleOwner(), propiedadObserver);

            btnToggleEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (estado==1){
                        estadoEditable();
                        Propiedad u = new Propiedad(
                                etDireccion.getText().toString(),
                                Integer.parseInt(etAmbientes.getText().toString()),
                                etTipo.getText().toString(),
                                etUso.getText().toString(),
                                Integer.parseInt(etPrecio.getText().toString()),
                                Boolean.valueOf(etDisponible.getText().toString())                        );
                        mViewModel.setPropiedad(u);
                        //
                        Log.d("Llamar seteables", "presione editar");

                    }else{
                        estadoNoEditable();
                        Log.d("Llamar a actualizar", "presione actualizar");
                        //Propietario propAux = mViewModel.getExiste(); Por algun motivo (nose cual) me setea el id=0 cuando trato de acceder desde aca
                        Propiedad a = new Propiedad();
                        //a.setId(propAux.getId());
                        a.setDireccion(etDireccion.getText().toString());
                        a.setAmbientes(Integer.parseInt(etAmbientes.getText().toString()));
                        a.setTipo(etTipo.getText().toString());
                        a.setUso(etUso.getText().toString());
                        a.setPrecio(Integer.parseInt(etPrecio.getText().toString()));
                        a.setDisponible(Boolean.valueOf(etDisponible.getText().toString()));

                        mViewModel.actualizarPropiedad(a);
                    }
                }
            });

            estadoNoEditable();
            return view;
            //

        } catch (Exception e) {
            Log.d("verPropiedadDetail", e.getMessage());
          //  Log.d("verPropiedadDetail", e.getCause().toString());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    public void estadoNoEditable() {

        etDireccion.setEnabled(false);
        etAmbientes.setEnabled(false);
        etTipo.setEnabled(false);
        etUso.setEnabled(false);
        etPrecio.setEnabled(false);
        etDisponible.setEnabled(false);
        etDireccion.setEnabled(false);
        btnToggleEditar.setText("Editar");
        estado = 1;
    }

    public void estadoEditable(){
        etDireccion.setEnabled(true);
        etAmbientes.setEnabled(true);
        etTipo.setEnabled(true);
        etUso.setEnabled(true);
        etPrecio.setEnabled(true);
        etDisponible.setEnabled(true);
        etDireccion.setEnabled(true);
        btnToggleEditar.setText("Actualizar");
        estado = 2;

    }

}
