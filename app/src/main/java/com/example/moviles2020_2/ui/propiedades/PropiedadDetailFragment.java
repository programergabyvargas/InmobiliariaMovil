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
    TextView tvPropiedadId, tvDireccion, tvAmbientes, tvTipo, tvUso, tvPrecio, tvDisponible;
    View view;
    Button btnToggleDisponible;


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


            tvDireccion = view.findViewById(R.id.tvDireccion);
            tvAmbientes = view.findViewById(R.id.tvAmbientes);
            tvTipo = view.findViewById(R.id.tvTipo);
            tvUso = view.findViewById(R.id.tvUso);
            tvPrecio = view.findViewById(R.id.tvPrecio);
            tvDisponible = view.findViewById(R.id.tvDisponible);
            btnToggleDisponible = view.findViewById(R.id.btnToggleDisponible);


            final Observer<Propiedad> propiedadObserver = new Observer<Propiedad>() {
                @Override
                public void onChanged(Propiedad propiedad) {
                    try {

                       tvDireccion.setText(propiedad.getDireccion()+"");
                        tvAmbientes.setText(propiedad.getAmbientes()+"");
                        tvTipo.setText(propiedad.getTipo()+"");
                        tvUso.setText(propiedad.getUso()+"");
                        tvPrecio.setText(propiedad.getPrecio()+"");

                        if (propiedad.getDisponible()){
                            tvDisponible.setText("Si");
                        }else {
                            tvDisponible.setText("No");
                        }


                    }catch (Exception e){
                        Log.d("verPropiedadDetail", e.getMessage());
                        Log.d("verPropiedadDetail", e.getCause().toString());
                    }


                }
            };

            mViewModel.getPropiedad().observe(getViewLifecycleOwner(), propiedadObserver);

            btnToggleDisponible.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.cambiarDisponible();
                }
            });
            return view;
        } catch (Exception e) {
            Log.d("verPropiedadDetail", e.getMessage());
            Log.d("verPropiedadDetail", e.getCause().toString());
        }


        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel


    }

}
