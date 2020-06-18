package com.example.moviles2020_2.ui.propiedades;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.moviles2020_2.R;
import com.example.moviles2020_2.model.Propiedad;

public class NuevaPropiedadFragment extends Fragment {

    private PropiedadViewModel mViewModel;
    EditText tvPropiedadId, etDireccion, etAmbientes, etTipo, etUso, etPrecio ;
    CheckBox cBDisponible;
    View view;
    Button btnToggleGuardar;
    private int estado = 0;

    public static NuevaPropiedadFragment newInstance() {
        return new NuevaPropiedadFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {
            mViewModel = ViewModelProviders.of(this).get(PropiedadViewModel.class);
            view = inflater.inflate(R.layout.nueva_propiedad_fragment, container, false);
          //  int propiedad = (int) getArguments().getInt("propiedadId");
          //  mViewModel.obtenerPropiedad(propiedad);

            etDireccion = view.findViewById(R.id.etDireccion);
            etAmbientes = view.findViewById(R.id.etAmbientes);
            etTipo = view.findViewById(R.id.etTipo);
            etUso = view.findViewById(R.id.etUso);
            etPrecio = view.findViewById(R.id.etPrecio);
            cBDisponible = view.findViewById(R.id.cBDisponible);
            btnToggleGuardar = view.findViewById(R.id.btnToggleGuardar);

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
                                cBDisponible.setChecked(true);
                             }else {
                                cBDisponible.setChecked(false);
                             }

                        }catch (Exception e){
                        Log.d("verPropiedadDetail", e.getMessage());
                        Log.d("verPropiedadDetail", e.getCause().toString());
                    }
                }
            };
            mViewModel.getPropiedad().observe(getViewLifecycleOwner(), propiedadObserver);

            btnToggleGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        estadoEditable();
                        Propiedad a = new Propiedad();
                        //a.setId(propAux.getId());
                        a.setDireccion(etDireccion.getText().toString());
                        a.setAmbientes(Integer.parseInt(etAmbientes.getText().toString()));
                        a.setTipo(etTipo.getText().toString());
                        a.setUso(etUso.getText().toString());
                        a.setPrecio(Integer.parseInt(etPrecio.getText().toString()));
                        a.setDisponible(Boolean.valueOf(cBDisponible.isChecked()));

                        mViewModel.altaPropiedad(a);
                   //Navigation.findNavController(view).navigate(R.id.propiedadFragmentMainLayout);
                }
            });

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

    public void estadoEditable(){
        etDireccion.setEnabled(true);
        etAmbientes.setEnabled(true);
        etTipo.setEnabled(true);
        etUso.setEnabled(true);
        etPrecio.setEnabled(true);
        cBDisponible.setEnabled(true);
        etDireccion.setEnabled(true);
     }

}
