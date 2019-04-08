package com.jamalonexpress.fragmentwithnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class Fragment2 extends Fragment {
    ArrayList<String> cartList = new ArrayList<>();
    private static final String TAG = "Fragment2";
    JSONObject jsonObject = null;
    Book book = new Book();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout2,container,false);

        final MainActivity x = (MainActivity)getActivity();
        final TextView title = view.findViewById(R.id.textView2);
        final TextView author = view.findViewById(R.id.textView3);
        final ImageView imageView = view.findViewById(R.id.imageView2);
        final TextView price = view.findViewById(R.id.textView4);
        final EditText desc = view.findViewById(R.id.editText);
        final TextView sku = view.findViewById(R.id.sku);
        final TextView isbn = view.findViewById(R.id.isbn);
        final TextView isbn13 = view.findViewById(R.id.isbn13);
        final TextView pages = view.findViewById(R.id.pages);
        final TextView year = view.findViewById(R.id.year);
        final TextView publisher = view.findViewById(R.id.publisher);
        final TextView cover = view.findViewById(R.id.cover);
        final TextView series = view.findViewById(R.id.series);
        final Button button = view.findViewById(R.id.cartButton);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://192.168.2.13/v1/catalog/product/12345671",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray=null;
                        try {
                            jsonObject = new JSONObject(response.toString()).getJSONObject("response");
                            JSONObject product = new JSONObject(jsonObject.toString()).getJSONObject("product_price");
                            Log.d("sad", "onResponse: "+ jsonObject);
                            title.setText(x.sku);
                           // title.setText(jsonObject.getString("title"));
                            author.setText("المؤلف: "+jsonObject.getString("author_name"));
                            Picasso.get().load(jsonObject.getString("main_image")).into(imageView);
                            price.setText("السعر: "+ product.getString("price")+ " ريال سعودي");
                            desc.setText(jsonObject.getString("description"));
                            sku.setText(jsonObject.getString("sku")+" :SKU");
                            isbn.setText("ردمك: "+jsonObject.getString("ean"));
                            isbn13.setText("ردمك-13: "+jsonObject.getString("isbn"));
                            pages.setText("عدد الصفحات: "+jsonObject.getString("pages"));
                            year.setText("سنة النشر: "+jsonObject.getString("publish_date"));
                            publisher.setText("دار النشر: "+jsonObject.getString("publisher_name"));
                            cover.setText("غﻻف الكتاب: "+jsonObject.getString("cover_type"));
                            series.setText("ترتيب الكتاب في السلسة: "+"غير متوفر");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", String.valueOf(error));
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    book.setSku(jsonObject.getString("sku"));
                    book.setAuthor(jsonObject.getString("author_name"));
                    book.setImage(jsonObject.getString("main_image"));
                    ((AddorRemoveCallbacks)getContext()).onAddProduct(jsonObject.getString("sku"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

}

