package com.example.qualitycam;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainCardsAdaptor extends RecyclerView.Adapter<MainCardsAdaptor.CardHolder> {

   Context context;
   List<SingleCard> data;

    public MainCardsAdaptor(Context context, List<SingleCard> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_card, parent, false);
        return new CardHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.imageView.setImageResource(data.get(position).getImage());
        holder.price.setText(String.format("$%d",data.get(position).getPrice()) );
        holder.title.setText(data.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class CardHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        TextView price;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_title);
            price = itemView.findViewById(R.id.card_price);

        }
    }

}
