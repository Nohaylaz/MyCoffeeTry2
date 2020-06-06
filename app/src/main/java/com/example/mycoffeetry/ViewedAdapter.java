package com.example.mycoffeetry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewedAdapter extends RecyclerView.Adapter<ViewedAdapter.ViewedViewHolder> {


    ArrayList<ViewedClass> viewedLocation;

    //holds data
    public ViewedAdapter(ArrayList<ViewedClass> viewedLocation) {
        this.viewedLocation = viewedLocation;
    }


    @NonNull
    @Override
    public ViewedAdapter.ViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mostviewed_card, parent, false);
        ViewedAdapter.ViewedViewHolder viewedViewHolder = new ViewedAdapter.ViewedViewHolder(view);

        return viewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewedAdapter.ViewedViewHolder holder, int position) {

        ViewedClass viewedClass = viewedLocation.get(position);

        holder.image.setImageResource(viewedClass.getImage());
        holder.title.setText(viewedClass.getTitle());
        holder.description.setText(viewedClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return viewedLocation.size();
    }

    public static class ViewedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, description;

        public ViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.viewed_img);
            title = itemView.findViewById(R.id.viewed_title);
            description = itemView.findViewById(R.id.viewed_desc);

        }

    }
}