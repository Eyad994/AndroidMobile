package com.jamalonexpress.fragmentwithnavigation;

import android.content.Intent;
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
import android.widget.Toast;

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout3,container,false);
        WebView browser = (WebView) view.findViewById(R.id.webview);
        browser.loadUrl("https://express.jamalon.com/checkout/cart/");
        return view;
    }
}
