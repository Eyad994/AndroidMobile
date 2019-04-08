package com.jamalonexpress.fragmentwithnavigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class SingleViewActivity extends AppCompatActivity {

    private static final String TAG = "SingleViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        final TextView title = findViewById(R.id.textView2);
        final TextView author = findViewById(R.id.textView3);
        final ImageView imageView = findViewById(R.id.imageView2);
        final TextView price = findViewById(R.id.textView4);
        final EditText desc = findViewById(R.id.editText);
        final TextView sku = findViewById(R.id.sku);
        final TextView isbn = findViewById(R.id.isbn);
        final TextView isbn13 = findViewById(R.id.isbn13);
        final TextView pages = findViewById(R.id.pages);
        final TextView year = findViewById(R.id.year);
        final TextView publisher = findViewById(R.id.publisher);
        final TextView cover = findViewById(R.id.cover);
        final TextView series = findViewById(R.id.series);
        final Button addButton = findViewById(R.id.addCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://192.168.2.13/v1/catalog/product/12345671",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        JSONArray jsonArray=null;
                        try {
                            jsonObject = new JSONObject(response.toString()).getJSONObject("response");
                            JSONObject product = new JSONObject(jsonObject.toString()).getJSONObject("product_price");
                            Log.d("sad", "onResponse: "+ jsonObject);
                            title.setText(jsonObject.getString("title"));
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
                Log.d("Error: ",error+"");
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

//        Intent intent = getIntent();
//        String title = intent.getExtras().getString("title");
//        String image = intent.getExtras().getString("image");
//        TextView textView = findViewById(R.id.textView2);
//        ImageView imageView = findViewById(R.id.imageView2);
//        textView.setText(title);
//        Picasso.get().load(image).into(imageView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleViewActivity.this, "ss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
