package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;

import java.util.List;


public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private static List<Contato> contatos;
    private Context context;

    private static ItemClickListener clickListener;

    public ContatoAdapter(List<Contato> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contato_celula, parent, false);
        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
       holder.nome.setText(contatos.get(position).getNome());

       Boolean favorito = contatos.get(position).getFavorito();

       holder.favorite.setSelected(favorito);
       if (favorito) {
           holder.favorite.setBackgroundResource(R.drawable.favoritado);
       } else {
           holder.favorite.setBackgroundResource(R.drawable.desfavoritado);
       }
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public  class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nome;
        final Button favorite;

        ContatoViewHolder(View view) {
            super(view);
            nome = (TextView)view.findViewById(R.id.nome);
            favorite = (Button)view.findViewById(R.id.btFavorito);

            favorite.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.btFavorito) {
                if (view.isSelected()) {
                    view.setBackgroundResource(R.drawable.desfavoritado);
                    view.setSelected(false);
                } else {
                    view.setBackgroundResource(R.drawable.favoritado);
                    view.setSelected(true);
                }

                if (clickListener != null) {
                    clickListener.favoriteSelection(getAdapterPosition(), view.isSelected());
                }
            } else {
                if (clickListener != null)
                    clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
        void favoriteSelection(int position, Boolean isFavorite);
    }
}


