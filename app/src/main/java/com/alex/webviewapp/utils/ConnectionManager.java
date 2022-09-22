package com.alex.webviewapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.alex.webviewapp.R;
import com.alex.webviewapp.fragments.NoInternetFragment;
import com.alex.webviewapp.fragments.WebViewFragment;

public class ConnectionManager {

    FragmentManager fragmentManager;
    ConnectivityManager connectivityManager;
    NetworkRequest networkRequest;
    ConnectivityManager.NetworkCallback networkCallback;

    public ConnectionManager(Context context) {
        connectivityManager =
                (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                replaceFragment(WebViewFragment.class);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                replaceFragment(NoInternetFragment.class);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                replaceFragment(NoInternetFragment.class);
            }
        };
    }

    public void requestNetwork() {
        if (connectivityManager.getActiveNetworkInfo() == null ||
                !connectivityManager.getActiveNetworkInfo().isConnected()) {
            replaceFragment(NoInternetFragment.class);
        }
        connectivityManager.requestNetwork(networkRequest, networkCallback);
    }

    public void replaceFragment(Class<? extends Fragment> obj) {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, obj, null)
                .commit();
    }
}
