package com.kbstar.i04movie;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter
        extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private ArrayList<Movie> items = new ArrayList<Movie>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // person_item.xml 파일로 inflate
        LayoutInflater inflater
                = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(
                R.layout.movie_item ,
                parent,
                false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item = items.get(position);
        Log.d("RView", "------------------------------------ position = " + position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Main같이 외부에서 API
    public void addItem(Movie movie)
    {
        items.add(movie);
    }

    public void setItem(int idx, Movie movie)
    {
        items.set(idx, movie);
    }

    public void removeItem(int idx)
    {
        items.remove(idx);
    }

    public void removeAllItems() {
        for(int i=items.size()-1; i>=0; i--)
        {
            items.remove(i);
        }
    }

    public Movie getItem(int idx)
    {
        return items.get(idx);
    }

    // 한번에 리스트 형태를 만들어서 넣기
    public void setItems(ArrayList<Movie> items)
    {
        this.items =  items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView)
        {
            super(itemView);

            Log.d("RView", "------------------------------------ ViewHolder()");
            // 뷰객체내에 있는 이름, 전화객체를 참조
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(Movie item)
        {
            textView.setText(item.getTitle());
            textView2.setText(item.getAudiCount());
        }
    }
}