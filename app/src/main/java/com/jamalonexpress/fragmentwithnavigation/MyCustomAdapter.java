package com.jamalonexpress.fragmentwithnavigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.jamalonexpress.fragmentwithnavigation.Models.Book;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Book> list;
    private Context context;
    private static final String TAG = "MyCustomAdapter";


    public MyCustomAdapter(ArrayList<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_layout4, null);
        }
        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string);
        ImageView imageView = view.findViewById(R.id.imageCart);
        final TextView qty = view.findViewById(R.id.quantity);
        Button deleteBook = view.findViewById(R.id.delete_cart_book);
        TextView author = view.findViewById(R.id.cartAuthor);
        final TextView price = view.findViewById(R.id.cartPrice);

        if(list.size()>0) {

            final Book book = list.get(position);
            author.setText(book.getAuthor());
            Picasso.get().load(book.getImage()).into(imageView);
            listItemText.setText(book.getTitle());
            qty.setText(Integer.toString(book.getQty()));
            price.setText(Integer.toString(Integer.parseInt(book.getPrice()))+" ريال سعودي");

            //Handle buttons and add onClickListeners
            Button deleteBtn = view.findViewById(R.id.delete_btn);
            Button addBtn = view.findViewById(R.id.add_btn);

            deleteBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).onRemoveProduct(position);
                    list.remove(position);
                    ((MainActivity)context).saveData();
                    notifyDataSetChanged();
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = book.getQty();
                    if(x > 1 && x <= 5){
                        x--;
                        book.setQty(x);
                        ((MainActivity)context).bookItems.get(position).setQty(x);
                        ((MainActivity)context).saveData();
                        qty.setText(Integer.toString(book.getQty()));
                    }
                }
            });
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(book.getQty() > 1){
//                        int x = book.getQty();
//                        int pri = Integer.parseInt(book.getPrice());
//                        int w = x * pri;
//                        price.setText(Integer.toString(w));
//                        notifyDataSetChanged();
//                    }else{
//                        price.setText(Integer.toString(Integer.parseInt(book.getPrice())));
//                    }

                    int x = book.getQty();
                    if(x >= 1 && x < 5) {
                        x++;
                       book.setQty(x);
                        ((MainActivity)context).bookItems.get(position).setQty(x);
                        ((MainActivity)context).saveData();
                        qty.setText(Integer.toString(book.getQty()));
                        //notifyDataSetChanged();
                    }
                }
            });
        }
        return view;
    }
}
