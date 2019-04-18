package com.jamalonexpress.fragmentwithnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jamalonexpress.fragmentwithnavigation.Models.Book;
import com.jamalonexpress.fragmentwithnavigation.MainActivity;
import com.jamalonexpress.fragmentwithnavigation.MyCustomAdapter;
import com.jamalonexpress.fragmentwithnavigation.R;
import java.util.ArrayList;

public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";
    int count =0;
    ArrayList<Book> list = new ArrayList<>();

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
        Button delete_book = view.findViewById(R.id.delete_cart_book);
        delete_book.setVisibility(View.GONE);
        author.setVisibility(View.GONE);
        qty.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        button_minus.setVisibility(View.GONE);
        button_plus.setVisibility(View.GONE);


        for (Book b : ((MainActivity)getContext()).bookItems){
            Log.d(TAG, "QTY: "+b.getQty());
            list.add(new Book(b.getSku(),b.getTitle(),b.getImage(),b.getPrice(),b.getQty(),b.getAuthor()));
        }

       // list.add(new Book("192929","ExampleBook1","123","21sa","sa","dd"));
        MyCustomAdapter adapter = new MyCustomAdapter(list, getContext());
        ListView lView = view.findViewById(R.id.listview);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.cart_list_footer,lView,false);
        lView.addFooterView(footer,null,false);
        lView.setAdapter(adapter);

        TextView total = view.findViewById(R.id.total_price);

        int x = 0;
        for (Book book: ((MainActivity)getContext()).bookItems){
            x += Integer.parseInt(book.getPrice());
        }
        total.setText("المجموع الفرعي ("+list.size()+"),"+" "+x+" ريال سعودي");

        Button button = view.findViewById(R.id.checkout_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PlaceOrder placeOrder = new PlaceOrder();
//                ((MainActivity)getContext()).changeFragment(placeOrder);
                Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });

//        for (Book b : ((MainActivity)getContext()).bookItems)
//        {
//           // Toast.makeText(getContext(), ""+b.getQty(), Toast.LENGTH_SHORT).show();
//            count++;
//        }
        return view;
    }
}