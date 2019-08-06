package com.example.aad1.activity;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.aad1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
    }

}