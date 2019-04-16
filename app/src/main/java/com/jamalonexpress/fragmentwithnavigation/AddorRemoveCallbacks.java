package com.jamalonexpress.fragmentwithnavigation;

public interface AddorRemoveCallbacks {

    public void onAddProduct(String sku,String title,String image,String price,int qty,String author);
    public void onRemoveProduct(int position);
}
