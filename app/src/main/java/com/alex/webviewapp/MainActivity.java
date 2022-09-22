package com.alex.webviewapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.alex.webviewapp.fragments.StartFragment;
import com.alex.webviewapp.utils.ConnectionManager;
import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookButtonBase;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    AppsFlyerLib appsFlyerLib;
    FacebookButtonBase facebookButtonBase;
    OneSignal oneSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean policyAccepted = sharedPref.getBoolean(getString(R.string.acceptPolicy), false);
        if (policyAccepted) {
            ConnectionManager connectionManager = new ConnectionManager(this);
            connectionManager.requestNetwork();
        } else {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, StartFragment.class, null)
                        .commit();
            }
        }
    }

}