package com.jara.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    /** MenuAdapter class loading views with data of menu items list **/

    // properties
    private ArrayList<MenuItem> menuItems;

    // constructor
    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);

        // set friends list
        menuItems = objects;
    }

    /* Gets view for Friend objects */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get menu row layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_menu, parent, false);
        }

        // get single menu item of the array list
        MenuItem menuItem = menuItems.get(position);

        // get name of menu item and set to text view
        TextView itemText = convertView.findViewById(R.id.item);
        itemText.setText(menuItem.getName());

        // get price of menu item and set to text view
        TextView priceText = convertView.findViewById(R.id.price);
        priceText.setText(menuItem.getPrice());

        return convertView;
    }
}
