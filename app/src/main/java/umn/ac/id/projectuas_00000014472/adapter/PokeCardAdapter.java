package umn.ac.id.projectuas_00000014472.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import umn.ac.id.projectuas_00000014472.CardDetailActivity;
import umn.ac.id.projectuas_00000014472.R;
import umn.ac.id.projectuas_00000014472.model.PokeCard;


public class PokeCardAdapter extends RecyclerView.Adapter<PokeCardAdapter.ViewHolder> {

    public static final String KEY_NAME="name";
    public static final String KEY_IMAGE="image";

    public static final String KEY_SUPERTYPE="supertype";
    public static final String KEY_TYPE="subtype";

    public static final String KEY_SERIES="series";
    public static final String KEY_SET="set";
    public static final String KEY_SETCODE="setcode";

    public List<PokeCard> pokeCards;
    public Context context;

    public PokeCardAdapter(List<PokeCard> pokeCards, Context context){
        this.pokeCards = pokeCards;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PokeCard pokeCard = pokeCards.get(position);
        holder.card_name.setText(pokeCard.getCardName());

        Picasso.with(context)
                .load(pokeCard.getImageUrl())
                .fit()
                .placeholder(R.drawable.loading_animation)
                .into(holder.card_image);

        holder.linear_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokeCard pokeCard1 = pokeCards.get(position);
                Intent intent = new Intent(v.getContext(), CardDetailActivity.class);
                intent.putExtra(KEY_NAME, pokeCard1.getCardName());
                intent.putExtra(KEY_IMAGE, pokeCard1.getImageUrlHiRes());
                intent.putExtra(KEY_SUPERTYPE, pokeCard1.getSupertype());
                intent.putExtra(KEY_TYPE, pokeCard1.getSubtype());
                intent.putExtra(KEY_SERIES, pokeCard1.getSeries());
                intent.putExtra(KEY_SET, pokeCard1.getSet());
                intent.putExtra(KEY_SETCODE, pokeCard1.getSetCode());
                v.getContext().startActivity(intent);
            }

        });

        holder.linear_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return pokeCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView card_name;
        ImageView card_image;
        LinearLayout linear_card;

        public ViewHolder(View cardView){
            super(cardView);

            card_name = cardView.findViewById(R.id.card_name);
            card_image = cardView.findViewById(R.id.card_image);
            linear_card = cardView.findViewById(R.id.linear_card);
        }
    }
}
