package com.jamalonexpress.fragmentwithnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.jamalonexpress.fragmentwithnavigation.R;

public class PlaceOrder extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.place_order_layout, container, false);
        WebView browser = view.findViewById(R.id.place_order_webview);
        browser.loadUrl("https://express.jamalon.com/onestepcheckout/");
        return view;
    }
}
