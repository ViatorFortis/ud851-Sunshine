package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by Антон on 06.01.2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        // TODO (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {


    // TODO (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);

        // TODO (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        for(int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference) ) {
                String preferenceValue = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, preferenceValue);
            }
        }
    }

    // TODO (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
    public void setPreferenceSummary(Preference preference, Object value) {
        String valueString = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int valueIndex = listPreference.findIndexOfValue(valueString);
            if (valueIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[valueIndex]);
            }
        } else {
            preference.setSummary(valueString);
        }
    }

    // TODO (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference != null) {
            if (!(preference instanceof CheckBoxPreference) ) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }
    }

    // TODO (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    // TODO (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
}
