package com.jamalonexpress.fragmentwithnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.jamalonexpress.fragmentwithnavigation.Book;
import com.jamalonexpress.fragmentwithnavigation.MainActivity;
import com.jamalonexpress.fragmentwithnavigation.MyCustomAdapter;
import com.jamalonexpress.fragmentwithnavigation.R;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";
    int count =0;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout4,container,false);

        ArrayList<Book> list = new ArrayList<Book>();
        for (Book b : ((MainActivity)getContext()).bookItems){
            list.add(new Book(b.getSku(),b.getTitle(),b.getImage(),b.getPrice(),b.getQty(),b.getAuthor()));
        }
       // list.add(new Book("192929","ExampleBook1","123","21sa","sa","dd"));

        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(list, getContext());

        //handle listview and assign adapter
        ListView lView = (ListView)view.findViewById(R.id.listview);
        lView.setAdapter(adapter);

        try {
            JSONArray jsonArray = new JSONArray(((MainActivity)getContext()).jsonData);
           // Toast.makeText(getContext(), ""+jsonArray.getString(0), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (Book b : ((MainActivity)getContext()).bookItems)
        {
            //Toast.makeText(getContext(), ""+b.getSku(), Toast.LENGTH_SHORT).show();
            count++;
        }
       // Toast.makeText(getContext(), "Counter: "+ count, Toast.LENGTH_SHORT).show();
       // Toast.makeText(getContext(), ""+((MainActivity)getContext()).jsonData, Toast.LENGTH_SHORT).show();
        return view;
    }
}