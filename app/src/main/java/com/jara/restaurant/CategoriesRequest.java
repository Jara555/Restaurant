package com.jara.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    /** Specialized request class, which downloads data from the server
     * and transforms this to a list of strings **/

    /* Properties */
    private Context context;
    private static String url = "https://resto.mprog.nl/categories";
    private ArrayList<String> categories = new ArrayList<>();
    private Callback activity;

    /* Interface */
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    /* Constructor */
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    /* Retrieves categories from API */
    public void getCategories(Callback inputActivity) {
        // initialize activity
        activity = inputActivity;

        // create new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // request data from API and add to queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, this, this);
        queue.add(jsonObjectRequest);
    }

    /* Request succeeded */
    @Override
    public void onResponse(JSONObject response) {
        // transform json to array list of type string
        try {
            JSONArray categoriesArray = response.getJSONArray("categories");
            // extract categories present in response
            for (int i = 0; i < categoriesArray.length(); i++) {
                categories.add(categoriesArray.getString(i));
            }
            activity.gotCategories(categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* Gets error message if request not succeeded */
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }
}
