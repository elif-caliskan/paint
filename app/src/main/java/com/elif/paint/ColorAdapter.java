package com.elif.paint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {

    // List to store all the contact details
    private ArrayList<Integer> colorList;
    private int selectedIndex = -1;
    private ColorSelected colorListener;

    // Counstructor for the Class
    public ColorAdapter(ArrayList<Integer> colorList, ColorSelected colorListener) {
        this.colorList = colorList;
        this.colorListener = colorListener;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.item_color, parent, false);
        return new ColorHolder(view);
    }

    @Override
    public int getItemCount() {
        return colorList == null? 0: colorList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ColorHolder holder, final int position) {
        final int color = colorList.get(position);
        holder.setBackgroundColor(color);
        if(position == selectedIndex){
            holder.colorBackground.setBackgroundResource(R.color.selected);
        } else{
            holder.colorBackground.setBackgroundResource(R.color.darkgray);
        }
        holder.colorBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorListener.onColorSelect(position);
            }
        });
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    // This is your ViewHolder class that helps to populate data to the view
    class ColorHolder extends RecyclerView.ViewHolder {

        View colorView;
        LinearLayout colorBackground;
        ColorHolder(View itemView) {
            super(itemView);

            colorView = itemView.findViewById(R.id.view_color);
            colorBackground = itemView.findViewById(R.id.color_background);
        }

        void setBackgroundColor(int color){
            colorView.setBackgroundResource(color);
        }
    }

    interface ColorSelected{
        void onColorSelect(int position);
    }
}