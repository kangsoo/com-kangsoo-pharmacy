/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p/>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.kangsoo.pharmacy.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.util.SettingsUtil;

/**
 * @author Bruno Farache
 */

public class SettingsActivity extends PreferenceActivity
        implements OnSharedPreferenceChangeListener {

    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }

    @SuppressWarnings("deprecation")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        addPreferencesFromResource(R.layout.settings);

        PreferenceScreen preferenceScreen = getPreferenceScreen();

        _loginPreference = (EditTextPreference) preferenceScreen.findPreference(SettingsUtil.LOGIN);
        _passwordPreference = (EditTextPreference) preferenceScreen.findPreference(SettingsUtil.PASSWORD);
        _serverPreference = (EditTextPreference) preferenceScreen.findPreference(SettingsUtil.SERVER);
        _usingwifiPreference = (EditTextPreference) preferenceScreen.findPreference(SettingsUtil.USINGWIFI);

        _loginPreference.setText(SettingsUtil.getLogin());
        _loginPreference.setSummary(SettingsUtil.getLogin());

        _passwordPreference.setText(SettingsUtil.getPassword());

        _serverPreference.setText(SettingsUtil.getServer());
        _serverPreference.setSummary(SettingsUtil.getServer());

        _usingwifiPreference.setText(SettingsUtil.getUsingwifi());
        _usingwifiPreference.setSummary(SettingsUtil.getUsingwifi());

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key) {

        if (key.equals(SettingsUtil.LOGIN)) {
            _loginPreference.setSummary(SettingsUtil.getLogin());
        } else if (key.equals(SettingsUtil.SERVER)) {
            _serverPreference.setSummary(SettingsUtil.getServer());
        } else if (key.equals(SettingsUtil.USINGWIFI)) {
            _usingwifiPreference.setSummary(SettingsUtil.getUsingwifi());
        }
    }

    private EditTextPreference _loginPreference;
    private EditTextPreference _passwordPreference;
    private EditTextPreference _serverPreference;
    private EditTextPreference _usingwifiPreference;

}