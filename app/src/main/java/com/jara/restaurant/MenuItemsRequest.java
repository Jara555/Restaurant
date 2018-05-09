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


public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    /** Specialized request class, which downloads data from the server
     * and transforms this to a list of MenuItems **/

    /* Properties */
    private Context context;
    private Callback activity;
    private String category;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    /* Interface */
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    /* Constructor */
    public MenuItemsRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    /* Retrieves menu items from API */
    public void getMenuItems(Callback inputActivity) {
        // initialize activity
        activity = inputActivity;

        // create new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // request data from url and add to queue
        String url = "https://resto.mprog.nl/menu?category=" + category;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, this, this);
        queue.add(jsonObjectRequest);
    }

    /* Request succeeded */
    @Override
    public void onResponse(JSONObject response) {
        try {
            // iterate over all items of json array
            JSONArray menuArray = response.getJSONArray("items");
            for (int i = 0; i < menuArray.length(); i++) {
                // get i'th menu object of array
                JSONObject menuObject = menuArray.getJSONObject(i);

                // retrieve all info of item
                String category = menuObject.getString("category");;
                String description = menuObject.getString("description");;
                String price = menuObject.getString("price");;
                String imageUrl = menuObject.getString("image_url");;
                String name = menuObject.getString("name");

                // create new MenuItem object and add to list
                MenuItem menuItem = new MenuItem(category, description, price, imageUrl, name);
                menuItems.add(menuItem);
            }
            // pass menu items through to got method
            activity.gotMenuItems(menuItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* Gets error message if request failed */
    @Override
    public void onErrorResponse(VolleyError error) {
        // pass message through to got error method
        activity.gotMenuItemsError(error.getMessage());
    }
}
