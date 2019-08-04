package com.example.aad1.activity;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.aad1.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

}