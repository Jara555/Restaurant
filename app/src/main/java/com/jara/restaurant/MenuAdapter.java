package com.jara.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    /** MenuAdapter class loading views with data of menu items list **/

    /* Properties */
    private ArrayList<MenuItem> menuItems;

    /* Constructor */
    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);

        // set menu items list to objects input
        menuItems = objects;
    }

    /* Gets view for MenuItem objects */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get menu row layout if not already exists
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_menu, parent, false);
        }

        // get single menu item of the array list
        MenuItem menuItem = menuItems.get(position);

        // set item name
        TextView itemText = convertView.findViewById(R.id.item);
        itemText.setText(menuItem.getName());

        // set item price
        TextView priceText = convertView.findViewById(R.id.price);
        priceText.setText(String.format("€%s", menuItem.getPrice()));

        // set item image
        ImageView image = convertView.findViewById(R.id.image);
        String url = menuItem.getImageUrl();
        Picasso.get().load(url).into(image);

        return convertView;
    }
}
