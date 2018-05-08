package com.jara.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    /** Initializes the catagories activity, showing all categories retrieved from a JSONobject **/

    /* Creates new request for retrieving categories from API */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // call new request for categories
        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);
    }

    /* Shows categories in list view when request succeeded */
    @Override
    public void gotCategories(ArrayList<String> categories) {
        // create new adapter of string array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_category, R.id.category, categories);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    /* Shows error message in terminal when request not succeeded */
    @Override
    public void gotCategoriesError(String message) {
        System.err.println("Error: Request failed. Categories could not be retrieved.");
    }
}
