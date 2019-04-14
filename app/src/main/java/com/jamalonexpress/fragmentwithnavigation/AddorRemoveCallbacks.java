package com.jamalonexpress.fragmentwithnavigation;

public interface AddorRemoveCallbacks {

    public void onAddProduct(String sku,String title,String image,String price,String author);
    public void onRemoveProduct();
}
