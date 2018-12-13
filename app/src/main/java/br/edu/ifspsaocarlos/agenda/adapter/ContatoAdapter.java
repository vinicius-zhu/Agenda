package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import br.edu.ifspsaocarlos.agenda.data.ContatoDAO;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;

import java.util.List;


public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private static List<Contato> contatos;
    private Context context;
    private ContatoDAO cDAO;

    private static ItemClickListener clickListener;
    private static CompoundButton.OnCheckedChangeListener changeListener;



    public ContatoAdapter(List<Contato> contatos, Context context, ContatoDAO cDAO) {
        this.contatos = contatos;
        this.context = context;
        this.cDAO = cDAO;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contato_celula, parent, false);
        return new ContatoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
       holder.nome.setText(contatos.get(position).getNome());
       holder.favorito.setChecked(contatos.get(position).getFavorito());
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
        final ToggleButton favorito;

        ContatoViewHolder(View view) {

            super(view);
            nome = (TextView)view.findViewById(R.id.nome);
            favorito = (ToggleButton)view.findViewById(R.id.favButton);

            view.setOnClickListener(this);
            favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Contato c = contatos.get(getAdapterPosition());
                    c.setFavorito(isChecked);
                    cDAO.salvaFavorito(c);
                    if (isChecked)
                        favorito.setBackgroundDrawable(ContextCompat.getDrawable(buttonView.getContext(), R.drawable.img_star_blue));
                    else
                        favorito.setBackgroundDrawable(ContextCompat.getDrawable(buttonView.getContext(), R.drawable.img_star_gray));
                }
            });
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());

        }


    }

    public interface ChangeListener {
        void onCheckedChange(CompoundButton button, Boolean checked);
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }



}


