package com.jamalonexpress.fragmentwithnavigation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private static final String TAG = "RecycleViewAdapter";
    private Context mContext;
    public ArrayList<String> mImageNames;
    public ArrayList<String> mImages;
    private int incremant = 0;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;


    public RecycleViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<String> mImages) {
        this.mContext = mContext;
        this.mImageNames = mImageNames;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: ");
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: ");
         if(i== 0){
             viewHolder.HeaderButton.setText("إعرض الكل");
             viewHolder.HeaderButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(mContext, "Display All", Toast.LENGTH_SHORT).show();
                 }
             });
             viewHolder.textView.setVisibility(View.GONE);
         }
         else if(i==1){
            viewHolder.textView.setText("الكتب الأكثر مبيعاً");
            viewHolder.HeaderButton.setVisibility(View.GONE);

        }else if(i==4){
             viewHolder.textView.setVisibility(View.GONE);

         } else if(i==5){
             viewHolder.textView.setText("كتب جديدة       ");
             viewHolder.HeaderButton.setVisibility(View.GONE);
        }else  {
            viewHolder.textView.setVisibility(View.GONE);
            viewHolder.HeaderButton.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(i))
                .into(viewHolder.image);
        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on:"+ mImageNames.get(i));
                //Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();
                ((MainActivity)mContext).onClicl(mImageNames.get(i));
                ((MainActivity)mContext).setViewPager(1);
//                Intent intent = new Intent(mContext,SingleViewActivity.class);
//                intent.putExtra("title",mImageNames.get(i));
//                intent.putExtra("image",mImages.get(i));
//                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        TextView textView,HeaderButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HeaderButton = itemView.findViewById(R.id.buttonHeader);
            textView = itemView.findViewById(R.id.header);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout
            );
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private int position(){
        incremant++;
        return incremant;
    }

}
