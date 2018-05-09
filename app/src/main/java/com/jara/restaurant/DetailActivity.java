package com.jara.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    /** DetailActivity class to set up details of individual menu items **/

    /* Loads detailed menu item layout */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // retrieve menu item from intent
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("clickedItem");

        // set name
        TextView name = findViewById(R.id.name);
        name.setText(menuItem.getName());

        // set image
        ImageView image = findViewById(R.id.image);
        String url = menuItem.getImageUrl();
        Picasso.get().load(url).into(image);

        // set description
        TextView price = findViewById(R.id.price);
        price.setText(String.format("€%s", menuItem.getPrice()));

        // set price
        TextView description = findViewById(R.id.description);
        description.setText(menuItem.getDescription());
    }
}
