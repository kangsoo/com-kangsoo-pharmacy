package com.kangsoo.pharmacy.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kangsoo.pharmacy.model.User;
import com.kangsoo.pharmacy.util.PreferenceUtils;

public class HomePagerFragment extends TabPagerFragment<HomePagerAdapter> {

    private static final String TAG = "HomePagerFragment";

    private static final String PREF_ORG_ID = "orgId";

    private SharedPreferences sharedPreferences;

    private boolean isDefaultUser;

    private User org;

    private HomePagerAdapter homePagerAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        setOrg((User) getArguments().getSerializable("org"));
    }

    private void setOrg(User org) {
        PreferenceUtils.save(sharedPreferences.edit().putInt(PREF_ORG_ID, Integer.parseInt(Long.toString(org.getUserId()))));
        this.org = org;
        this.isDefaultUser = true;
//        this.isDefaultUser = AccountUtils.isUser(getActivity(), org);
        configureTabPager();
    }

    @Override
    protected HomePagerAdapter createAdapter() {
        homePagerAdapter = new HomePagerAdapter(this, isDefaultUser, org);
        return homePagerAdapter;
    }

    public void setPagePosition(int position){

        homePagerAdapter.getItemPosition(2);
//        this.getCurrentItem(position);
//        homePagerAdapter.getItem(position);
//        this.onPageSelected(position);
    }

    @Override
    public void setCurrentItem(int position) {
        super.setCurrentItem(position);
    }
}
