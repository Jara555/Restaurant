package com.jara.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class test extends android.widget.ArrayAdapter {

    private ArrayList<String> categories;

    public test(Context context, int layout, ArrayList<String> objects) {
        super(context, layout, objects);
        categories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // get grid item layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_category, parent, false);
        }

        String category = categories.get(position);

        TextView categoryText = (TextView) convertView.findViewById(R.id.category);
        categoryText.setText(category);

        return convertView;
    }
}
