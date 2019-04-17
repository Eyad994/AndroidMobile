package com.jamalonexpress.fragmentwithnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.jamalonexpress.fragmentwithnavigation.Models.ListBook;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ListAdapterFragment extends BaseAdapter implements ListAdapter {

    private ArrayList<ListBook> list;
    private Context context;
    private static final String TAG = "ListAdapterFragment";

    public ListAdapterFragment(ArrayList<ListBook> list, Context context) {
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
            view = inflater.inflate(R.layout.list_fragment_layout, null);
        }

        final ListBook listBook = list.get(position);

        TextView title = view.findViewById(R.id.list_title);
        ImageView image = view.findViewById(R.id.list_image);
        TextView price = view.findViewById(R.id.list_price);

        price.setText(Integer.toString(listBook.getPrice()));
        title.setText(listBook.getTitle());
        Picasso.get().load("https://cdn.jamalon.com/c/p/3070376.jpg").into(image);

        return view;
    }

}
