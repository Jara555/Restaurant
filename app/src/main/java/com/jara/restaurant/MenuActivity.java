package com.jara.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {
    /** Initializes the menu activity, showing the restaurants menu items **/

    /* Creates new request for retrieving categories from API */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get intent of clicked category
        Intent intent = getIntent();
        String category = (String) intent.getStringExtra("category");

        // call new request for that category
        MenuItemsRequest menuItemsRequest = new MenuItemsRequest(this, category);
        menuItemsRequest.getMenuItems(this);

        // create click and long click listener for items in list view
        OnItemClickListener listClick = new OnItemClickListener();
        ListView listView = (ListView) findViewById(R.id.listViewMenu);
        listView.setOnItemClickListener(listClick);
    }

    /* Shows categories when request succeeded */
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        // create new array adapter and set to list view
        MenuAdapter adapter = new MenuAdapter(this, R.layout.row_menu, menuItems);
        ListView listView = (ListView) findViewById(R.id.listViewMenu);
        listView.setAdapter(adapter);
    }

    /* Creates toast error message when request not succeeded */
    @Override
    public void gotMenuItemsError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        /** Responds to clicks on items of list view **/

        /* Redirects to menu items of that category */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // get MenuItem object of clicked item
            MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);

            // create intent and pass clicked MenuItem through to detail activity
            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("clickedItem", clickedItem);
            startActivity(intent);
        }
    }
}
