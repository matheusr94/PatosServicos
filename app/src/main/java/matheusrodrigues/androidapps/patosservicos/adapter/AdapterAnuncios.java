package matheusrodrigues.androidapps.patosservicos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import matheusrodrigues.androidapps.patosservicos.R;
import matheusrodrigues.androidapps.patosservicos.model.Anuncio;

public class AdapterAnuncios extends RecyclerView.Adapter<AdapterAnuncios.MyViewHolder> {

    private List<Anuncio> anuncios;
    private Context context;

    public AdapterAnuncios(List<Anuncio> anuncios, Context context) {
        this.anuncios = anuncios;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_anuncio, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterAnuncios.MyViewHolder holder, int position) {
        Anuncio anuncio = anuncios.get(position);
        holder.servico.setText(anuncio.getServico());
        holder.valor.setText(anuncio.getValor());
        holder.telefone.setText(anuncio.getTelefone());
    }

    @Override
    public int getItemCount() {
        return anuncios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView servico;
        TextView valor;
        TextView telefone;

        public MyViewHolder(View itemView){
            super(itemView);

            servico = itemView.findViewById(R.id.textTituloServico);
            valor = itemView.findViewById(R.id.textValorServico);
            telefone = itemView.findViewById(R.id.textTelefoneServico);
        }
    }
}
