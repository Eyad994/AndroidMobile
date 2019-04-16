package com.jamalonexpress.fragmentwithnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Book> list = new ArrayList<Book>();
    private Context context;



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
        TextView author = view.findViewById(R.id.cartAuthor);
        Book book = list.get(position);
        author.setText(book.getAuthor());
        Picasso.get().load(book.getImage()).into(imageView);
        listItemText.setText(book.getTitle());
        //Handle buttons and add onClickListeners
        Button deleteBtn = view.findViewById(R.id.delete_btn);
        Button addBtn = view.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
               // list.remove(position); //or some other task
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                qty.setText("2");
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
