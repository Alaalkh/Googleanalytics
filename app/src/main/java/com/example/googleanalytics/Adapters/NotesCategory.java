package com.example.googleanalytics.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleanalytics.Models.Notes;
import com.example.googleanalytics.R;

import java.util.List;

public class NotesCategory extends RecyclerView.Adapter<NotesCategory.ViewHolder> {
    private final List<Notes> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private final ItemClickListener2 itemClickListener2;

    public NotesCategory(Context context, List<Notes> data, ItemClickListener onClick, ItemClickListener2 onClick2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = onClick;
        this.itemClickListener2 = onClick2;
    }

    @NonNull
    @Override
    public NotesCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_notes, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull NotesCategory.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener2.onItemClick2(holder.getAdapterPosition(), mData.get(position).getId());


            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView card;
        public TextView title;
        ViewHolder(View itemView) {
            super(itemView);
            this.card = itemView.findViewById(R.id.card1);
            this.title=itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }


    Notes getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, String id);
    }

    public interface ItemClickListener2{
        void onItemClick2(int position, String id);
    }

}

