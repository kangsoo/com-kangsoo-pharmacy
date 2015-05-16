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

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.activity.CameraActivity;
import com.kangsoo.pharmacy.activity.PhotoActivity;
import com.kangsoo.pharmacy.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Pager adapter for a user's different views
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private final User org;
    private boolean defaultUser;
    private final FragmentManager fragmentManager;
    private final Resources resources;
    private final Set<String> tags = new HashSet<>();

    /**
     * @param fragment
     * @param defaultUser
     */
    public HomePagerAdapter(final Fragment fragment, final boolean defaultUser, final User org) {
        super(fragment);
        this.org = org;
        fragmentManager = fragment.getChildFragmentManager();
        resources = fragment.getResources();
        this.defaultUser = defaultUser;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = new CameraActivity();
                break;
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(DESCRIBABLE_KEY, this.org);
//                fragment.setArguments(bundle);
            case 1:
                fragment = new PhotoActivity();
                break;
//            case 2:
//                fragment = defaultUser ? new MyFollowersFragment() : new MembersFragment();
//                break;
//            case 3:
//                fragment = new MyFollowingFragment();
//                break;
//            default:
//                fragment = new CameraFragment();
//                fragment = new CameraActivity();
//                break;
        }

        if (fragment != null) {
            Bundle args = new Bundle();
            args.putSerializable("org", org);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        if (fragment instanceof Fragment)
            tags.add(((Fragment) fragment).getTag());
        return fragment;
    }

    @Override
    public int getCount() {
        return defaultUser ? 4 : 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.tab_takepicture);
            case 1:
                return resources.getString(R.string.tab_passHistory);
            case 2:
                return resources.getString(R.string.tab_drugInfo);
            case 3:
                return resources.getString(R.string.tab_orderHistory);
            default:
                return null;
        }
    }
}
