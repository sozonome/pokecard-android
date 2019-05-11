package id.ac.umn.AgustinusNathaniel.adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import id.ac.umn.AgustinusNathaniel.CardDetailActivity;
import id.ac.umn.AgustinusNathaniel.R;
import id.ac.umn.AgustinusNathaniel.model.PokeCard;

import static id.ac.umn.AgustinusNathaniel.app.App.CHANNEL_1_ID;

public class PokeCardAdapter extends RecyclerView.Adapter<PokeCardAdapter.ViewHolder> {
    private NotificationManagerCompat notificationManagerCompat;

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
        Log.d("Adapter Track", "PokeCardAdapter start working");
        this.pokeCards = pokeCards;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
                Intent intent = new Intent(v.getContext(), CardDetailActivity.class);
                intent.putExtra(KEY_NAME, pokeCard.getCardName());
                intent.putExtra(KEY_IMAGE, pokeCard.getImageUrlHiRes());
                intent.putExtra(KEY_SUPERTYPE, pokeCard.getSupertype());
                intent.putExtra(KEY_TYPE, pokeCard.getSubtype());
                intent.putExtra(KEY_SERIES, pokeCard.getSeries());
                intent.putExtra(KEY_SET, pokeCard.getSet());
                intent.putExtra(KEY_SETCODE, pokeCard.getSetCode());
                v.getContext().startActivity(intent);
            }

        });

        holder.linear_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int wait_duration = 5000;
                final Toast toast = Toast.makeText(context, pokeCard.getCardName() + " registered. Please Wait...", Toast.LENGTH_LONG);

                CountDownTimer timer;
                timer = new CountDownTimer(wait_duration, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        toast.cancel();

                        String title = "Register Successful";
                        String message = pokeCard.getCardName() + " is registered";

                        notificationManagerCompat = NotificationManagerCompat.from(context);
                        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                                .setSmallIcon(R.drawable.ic_add)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();

                        notificationManagerCompat.notify(1, notification);
                    }
                };
                toast.show();
                timer.start();
                return true;
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
