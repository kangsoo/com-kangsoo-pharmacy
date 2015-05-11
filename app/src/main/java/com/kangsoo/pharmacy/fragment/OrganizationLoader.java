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

import android.app.Activity;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Load of a {@link List} or {@link User} organizations
 */
public class OrganizationLoader extends AuthenticatedUserLoader<List<User>> {

    private static final String TAG = "OrganizationLoader";
    private User org;
    private List<User> data;

    @Inject
    public OrganizationLoader(Activity activity) {
        super(activity);
    }

    @Override
    protected List<User> getAccountFailureData() {
        return null;
    }

    @Override
    protected List<User> onLoadInBackground() {

        data = new ArrayList<>();
        data.add(org);
        return data;
    }

    @Override
    public List<User> load(final User org) {

        data = new ArrayList<>();
        data.add(org);
        return data;
    }
}
