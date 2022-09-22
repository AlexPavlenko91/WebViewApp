package com.alex.webviewapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.alex.webviewapp.R;
import com.alex.webviewapp.utils.ConnectionManager;

public class StartFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        Button btnExit = v.findViewById(R.id.btnExit);
        Button btnAccept = v.findViewById(R.id.btnAccept);

        btnExit.setOnClickListener(view -> requireActivity().finish());
        btnAccept.setOnClickListener(view -> acceptPolicy());
        return v;
    }

    private void acceptPolicy() {
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.acceptPolicy), true);
        editor.apply();
        showWebFragment();
    }

    public void showWebFragment() {
        ConnectionManager connectionManager = new ConnectionManager(requireActivity());
        connectionManager.requestNetwork();
    }
}