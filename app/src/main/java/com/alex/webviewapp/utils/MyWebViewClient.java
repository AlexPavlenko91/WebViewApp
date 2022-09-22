package com.alex.webviewapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alex.webviewapp.MainActivity;
import com.alex.webviewapp.R;

public class MyWebViewClient extends WebViewClient {

    private final Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        final Uri uri = Uri.parse(url);
        return false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        final Uri uri = request.getUrl();
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        saveUrl(url);
    }

    private void saveUrl(String url) {
        SharedPreferences sharedPref = ((MainActivity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.lastUrl), url);
        editor.apply();
    }


}
