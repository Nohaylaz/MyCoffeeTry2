package com.example.mycoffeetry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedClass> featuredLocation;

    //holds data
    public FeaturedAdapter(ArrayList<FeaturedClass> featuredLocation) {
        this.featuredLocation = featuredLocation;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedClass featuredClass = featuredLocation.get(position);

        holder.image.setImageResource(featuredClass.getImage());
        holder.title.setText(featuredClass.getTitle());
        holder.description.setText(featuredClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return featuredLocation.size();
    }

    //holds views design
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, description;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.featured_img);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_desc);

        }
    }




}
