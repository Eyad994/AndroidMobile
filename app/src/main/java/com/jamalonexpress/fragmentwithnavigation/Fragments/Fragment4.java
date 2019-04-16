package com.jamalonexpress.fragmentwithnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        Button button_plus = view.findViewById(R.id.add_btn);
        Button button_minus = view.findViewById(R.id.delete_btn);
        ImageView imageView = view.findViewById(R.id.imageCart);
        TextView textView = view.findViewById(R.id.cartPrice);
        TextView qty = view.findViewById(R.id.quantity);
        TextView author = view.findViewById(R.id.cartAuthor);
        author.setVisibility(View.GONE);
        qty.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        button_minus.setVisibility(View.GONE);
        button_plus.setVisibility(View.GONE);

        ArrayList<Book> list = new ArrayList<Book>();
        for (Book b : ((MainActivity)getContext()).bookItems){
            list.add(new Book(b.getSku(),b.getTitle(),b.getImage(),b.getPrice(),b.getQty(),b.getAuthor()));
        }
       // list.add(new Book("192929","ExampleBook1","123","21sa","sa","dd"));

        MyCustomAdapter adapter = new MyCustomAdapter(list, getContext());

        ListView lView = (ListView)view.findViewById(R.id.listview);
        lView.setAdapter(adapter);

        for (Book b : ((MainActivity)getContext()).bookItems)
        {
            //Toast.makeText(getContext(), ""+b.getSku(), Toast.LENGTH_SHORT).show();
            count++;
        }
        return view;
    }
}