package com.example.moviles2020_2.ui.propiedades;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moviles2020_2.LoginViewModel;
import com.example.moviles2020_2.R;
import com.example.moviles2020_2.model.Propiedad;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class PropiedadFragment extends Fragment {

    private PropiedadViewModel mViewModel;
    List<Propiedad> listaMutable;
    RecyclerView contenedor;
    public List<Propiedad> listaPropiedades;

    public static PropiedadFragment newInstance() {
        return new PropiedadFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PropiedadViewModel.class);
        View view = inflater.inflate(R.layout.propiedad_fragment, container, false);
        contenedor = view.findViewById(R.id.rvPropiedades);

        RecyclerView.LayoutManager layout= new LinearLayoutManager(getContext());
        contenedor.setLayoutManager(layout);

        mViewModel.obtenerPropiedades();

        listaMutable = new ArrayList<>();
        final Observer<List<Propiedad>> listaObservable = new Observer<List<Propiedad>>() {
            @Override
            public void onChanged(List<Propiedad> propiedads) {
                contenedor.setAdapter(new Adapter(propiedads));
                listaPropiedades = propiedads;

                if (!listaPropiedades.isEmpty()) {
                   // Log.d("flag", "lista de propiedades " + listaPropiedades.get(0).getId());
                  //  Toast.makeText(getContext(), "Flag Propiedad" + listaPropiedades.get(0).getId(), Toast.LENGTH_LONG).show();
                }
            }
        };
        mViewModel.getPropiedades().observe(getViewLifecycleOwner(), listaObservable);


        return view;
    }

}
