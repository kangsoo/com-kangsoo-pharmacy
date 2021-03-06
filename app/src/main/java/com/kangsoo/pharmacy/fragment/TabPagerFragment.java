package com.kangsoo.pharmacy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kangsoo.pharmacy.R;

import wishlist.ViewUtils;

import static android.widget.TabHost.OnTabChangeListener;
import static android.widget.TabHost.TabContentFactory;

public abstract class TabPagerFragment<V extends PagerAdapter & FragmentProvider>
    extends PagerFragment implements OnTabChangeListener, TabContentFactory {


    /**
     * View pager
     */
    protected ViewPager pager;

    /**
     * Tab host
     */
    protected SlidingTabLayout slidingTabsLayout;

    /**
     * Pager adapter
     */
    protected V adapter;

    @Override
    public void onPageSelected(final int position) {
        super.onPageSelected(position);
    }

    @Override
    public void onTabChanged(String tabId) {
        Toast.makeText(getActivity(), "Tab Changed" + tabId, Toast.LENGTH_LONG).show();
    }

    @Override
    public View createTabContent(String tag) {
        return ViewUtils.setGone(new View(getActivity().getApplication()), true);
    }

    /**
     * Create pager adapter
     *
     * @return pager adapter
     */
    protected abstract V createAdapter();

    /**
     * Get title for position
     *
     * @param position
     * @return title
     */
    protected String getTitle(final int position) {
        return adapter.getPageTitle(position).toString();
    }

    /**
     * Get icon for position
     *
     * @param position
     * @return icon
     */
    protected String getIcon(final int position) {
        return null;
    }

    /**
     * Set tab and pager as gone or visible
     *
     * @param gone
     * @return this activity
     */
    protected TabPagerFragment<V> setGone(boolean gone) {
        ViewUtils.setGone(slidingTabsLayout, gone);
        ViewUtils.setGone(pager, gone);
        return this;
    }

    /**
     * Set current item to new position
     * <p/>
     * This is guaranteed to only be called when a position changes and the
     * current item of the pager has already been updated to the given position
     * <p/>
     * Sub-classes may override this method
     *
     * @param position
     */
    protected void setCurrentItem(final int position) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(position);
            }
        });
    }

    /**
     * Get content view to be used when {@link #onCreate(Bundle)} is called
     *
     * @return layout resource id
     */
    protected int getContentView() {
        return R.layout.pager_with_tabs;
    }

    private void createPager() {
        adapter = createAdapter();
        getActivity().supportInvalidateOptionsMenu();
        pager.setAdapter(adapter);
        slidingTabsLayout.setViewPager(pager);
    }

    /**
     * Configure tabs and pager
     */
    protected void configureTabPager() {
        createPager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        view.findViewById(R.id.toolbar).setVisibility(View.GONE);

        // On Lollipop, the action bar shadow is provided by default, so have to remove it explicitly
        ((ActionBarActivity) getActivity()).getSupportActionBar().setElevation(0);

        pager = (ViewPager) view.findViewById(R.id.vp_pages);
        pager.setOnPageChangeListener(this);
        slidingTabsLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_layout);
        slidingTabsLayout.setCustomTabView(R.layout.tab, R.id.tv_tab);
        slidingTabsLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));
        slidingTabsLayout.setDividerColors(0);
    }

    @Override
    protected FragmentProvider getProvider() {
        return adapter;
    }

}
