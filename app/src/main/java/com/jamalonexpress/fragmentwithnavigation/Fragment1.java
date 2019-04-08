package com.jamalonexpress.fragmentwithnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private final int x = 0;
    private static final String TAG = "Fragment1";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout1,container,false);

       StringRequest stringRequest=new StringRequest(Request.Method.GET, shared.employees,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray=null;
                        try {
                            jsonArray=new JSONArray(response.toString());
                            for (int i=0;i<8;i++){
                                mNames.add(jsonArray.getJSONObject(i).getString("employee_name"));
                                mImagesUrls.add("https://cdn.jamalon.com/c/p/3070376.jpg");
                            }
                            RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                            RecycleViewAdapter adapter = new RecycleViewAdapter(getContext(),mNames,mImagesUrls);
                            recyclerView.setAdapter(adapter);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("---------------------", "onDestroyView: ");
        this.mImagesUrls.clear();
        this.mNames.clear();
    }
}
