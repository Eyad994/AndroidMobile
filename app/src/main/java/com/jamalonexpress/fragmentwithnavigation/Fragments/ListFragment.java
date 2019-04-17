package com.jamalonexpress.fragmentwithnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jamalonexpress.fragmentwithnavigation.ListAdapterFragment;
import com.jamalonexpress.fragmentwithnavigation.Models.ListBook;
import com.jamalonexpress.fragmentwithnavigation.R;
import com.jamalonexpress.fragmentwithnavigation.shared;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private ArrayList<ListBook> listBooks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.list_fragment_layout,container,false);
        final ListView listView = view.findViewById(R.id.list_fragment);
        TextView main_title = view.findViewById(R.id.list_title);
        ImageView imageView = view.findViewById(R.id.list_image);
        TextView price = view.findViewById(R.id.list_price);
        price.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        main_title.setText("الكتب الأكثر مبيعاً");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, shared.employees,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray=null;
                        try {
                            jsonArray=new JSONArray(response.toString());
                            for (int i=0;i<8;i++){
                                listBooks.add(new ListBook(jsonArray.getJSONObject(i).getString("employee_name"),jsonArray.getJSONObject(i).getString("employee_age"),15));
                            }
                            ListAdapterFragment listAdapter = new ListAdapterFragment(listBooks,getContext());
                            listView.setAdapter(listAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ",error+"");
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return view;

    }
}
