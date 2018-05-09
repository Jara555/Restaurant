package com.jara.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    /** Initializes the categories activity, showing the restaurants menu categories **/

    /* Creates new request for retrieving categories from API */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // call new request for categories
        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);

        // create click and long click listener for items in list view
        OnItemClickListener listClick = new OnItemClickListener();
        ListView listView = (ListView) findViewById(R.id.listViewCategories);
        listView.setOnItemClickListener(listClick);
    }

    /* Shows categories when request succeeded */
    @Override
    public void gotCategories(ArrayList<String> categories) {
        // create new array adapter and set to list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row_category, R.id.category, categories);
        ListView listView = (ListView) findViewById(R.id.listViewCategories);
        listView.setAdapter(adapter);
    }

    /* Creates toast error message when request failed */
    @Override
    public void gotCategoriesError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        /** Responds to clicks on items of list view **/

        /* Redirects to menu items of that category */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // get category of clicked item
            String category = (String) parent.getItemAtPosition(position);

            // create intent and pass through to menu activity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
