package com.kangsoo.pharmacy.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.HomePagerFragment;
import com.kangsoo.pharmacy.fragment.UserLoader;
import com.kangsoo.pharmacy.model.User;

import java.util.Collections;
import java.util.List;

import static com.kangsoo.pharmacy.activity.NavigationDrawerObject.TYPE_SEPERATOR;

/**
 * Created by bsnc on 2015-05-10.
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        UserLoader.UserLoaderCallbacks {
//        LoaderManager.LoaderCallbacks<List<User>> {

    private static final String TAG = "MainActivity";
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private List<User> orgs = Collections.emptyList();
    private NavigationDrawerAdapter navigationAdapter;
    private User org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        UserLoader userLoader = new UserLoader(this);
        userLoader.init();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getMenuInflater().inflate(R.menu.home, optionMenu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = optionMenu.findItem(R.id.m_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(optionMenu);
    }


    @Override
    protected void onResume() {
        super.onResume();

/*
        UsersAsyncTask task = new UsersAsyncTask(MainActivity.this);
        task.execute();
*/

        // Restart loader if default account doesn't match currently loaded
        // account
        List<User> currentOrgs = orgs;

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (navigationAdapter.getItem(position).getType() == TYPE_SEPERATOR) {
            return;
        }

        Fragment fragment;
        fragment = new HomePagerFragment();
        Bundle args = new Bundle();

        switch (position) {
            case 1:
//                fragment = new HomePagerFragment();
//                args.putSerializable("org", org);
                break;

            case 2:
//                fragment = new GistsPagerFragment();
                break;

            case 3:
//                fragment = new IssueDashboardPagerFragment();
                break;

            case 4:
//                fragment = new FilterListFragment();
                break;

            default:
                fragment = new HomePagerFragment();
                args.putSerializable("org", org);
                break;
        }

        fragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onUserInformationSelected(User user) {

        if (user == null)
            return;

        this.org = user;

        if (navigationAdapter != null)
            navigationAdapter.setOrgs(user);
        else {
            navigationAdapter = new NavigationDrawerAdapter(MainActivity.this, user);
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout), navigationAdapter, user);

            Window window = getWindow();
            if (window == null)
                return;
            View view = window.getDecorView();
            if (view == null)
                return;

            view.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.this.onNavigationDrawerItemSelected(0);
                }
            });
        }
    }
}
