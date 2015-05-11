/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kangsoo.pharmacy.fragment;

import android.accounts.Account;
import android.app.Activity;
import android.util.Log;

import com.kangsoo.pharmacy.util.SettingsUtil;
import com.google.inject.Inject;

import com.kangsoo.pharmacy.model.User;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.typed.JSONObjectAsyncTaskCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Load of a {@link List} or {@link User} organizations
 */
public class OrganizationLoader extends AuthenticatedUserLoader<List<User>> {

    private static final String TAG = "OrganizationLoader";
    private User org;

    @Inject
    public OrganizationLoader(Activity activity) {
        super(activity);
        SettingsUtil.init(activity);
    }

    @Override
    protected List<User> getAccountFailureData() {
        return null;
    }

    @Override
    public List<User> load(final Account account) {

        try {
            Session session = SettingsUtil.getSession();

            SignIn.signIn(session, new JSONObjectAsyncTaskCallback() {

                @Override
                public void onSuccess(JSONObject userJSONObject) {
                    try {
                        org = new User(userJSONObject);
                    } catch (JSONException e) {
                        onFailure(e);
                    }
                }

                @Override
                public void onFailure(Exception e) {
                }

            });
        } catch (Exception e) {
            Log.e(TAG, "Exception loading organizations", e);
            return null;
        }

        List<User> data;
        data = new ArrayList<>();
        data.add(org);

        return data;
    }
}
