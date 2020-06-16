package com.example.moviles2020_2.ui.pagos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviles2020_2.R;
import com.example.moviles2020_2.model.Propiedad;

import java.util.List;

public class AdapterPagosPropiedades extends RecyclerView.Adapter<AdapterPagosPropiedades.ViewHolder> {

    List<Propiedad> listaPropiedad;

    public AdapterPagosPropiedades(List<Propiedad> listaPropiedad) {
        this.listaPropiedad = listaPropiedad;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_propiedad, parent, false);
        return new ViewHolder(vista, listaPropiedad);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargaPropiedad(listaPropiedad.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPropiedad.size();
    }



    //clase Interna


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EditText etDireccion, etAmbientes, etTipo, etUso, tvPrecio, tvDisponible;
        Button btnBuscaPropiedad;
        List<Propiedad> lista;


        public ViewHolder(@NonNull View itemView, List<Propiedad> lista) {
            super(itemView);
            etDireccion = itemView.findViewById(R.id.etDireccion);
            etAmbientes = itemView.findViewById(R.id.etAmbientes);
            etTipo = itemView.findViewById(R.id.etTipo);
            etUso = itemView.findViewById(R.id.etUso);
            tvPrecio = itemView.findViewById(R.id.etPrecio);
            tvDisponible = itemView.findViewById(R.id.etDisponible);
            btnBuscaPropiedad = itemView.findViewById(R.id.btnBuscaPropiedad);
            btnBuscaPropiedad.setOnClickListener(this);
            this.lista = lista;
        }

        public void cargaPropiedad(Propiedad p){
            etDireccion.setText(p.getDireccion());
            etAmbientes.setText(p.getAmbientes()+"");
            //tvTipo.setText(p.getTipo());
            etUso.setText(p.getUso());
            tvPrecio.setText(p.getPrecio()+"");
            //tvDisponible.setText(p.getDisponible()+"");
            btnBuscaPropiedad.setText("Ver Pagos");
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Propiedad propiedad = listaPropiedad.get(position);

            //Toast.makeText(btnBuscaPropiedad.getContext(), propiedad.getDireccion(), Toast.LENGTH_SHORT).show();


            Bundle bundle = new Bundle();
            bundle.putInt("id", propiedad.getId());
            Navigation.findNavController(v).navigate(R.id.paymentDetailFragment, bundle);



        }
    }

}
