package com.jara.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {
    /** Initializes the menu items activity,
     * showing all menu items retrieved from a JSONArray of objects **/

    /* Creates new request for retrieving categories from API */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // call new request for categories
        MenuItemsRequest menuItemsRequest = new MenuItemsRequest(this);
        menuItemsRequest.getMenuItems(this);
    }

    /* Shows categories in list view when request succeeded */
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        // create new adapter of string array
        MenuAdapter adapter = new MenuAdapter(this, R.layout.row_category, menuItems);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    /* Shows error message in terminal when request not succeeded */
    @Override
    public void gotMenuItemsError(String message) {
        System.err.println("Error: Request failed. Categories could not be retrieved.");
    }
}
