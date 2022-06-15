package com.kbstar.h02recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter
        extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private ArrayList<Person> items = new ArrayList<Person>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // person_item.xml 파일로 inflate

        Log.d("RView", "------------------------------------ onCreateViewHolder()");
        LayoutInflater inflater
                = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(
                                R.layout.person_item ,
                                parent,
                                false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        Log.d("RView", "------------------------------------ position = " + position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Person person)
    {
        items.add(person);
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

        public void setItem(Person item)
        {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}