package com.kangsoo.pharmacy.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.HomePagerFragment;
import com.kangsoo.pharmacy.model.User;
import com.kangsoo.pharmacy.task.UsersAsyncTask;
import com.kangsoo.pharmacy.util.AvatarLoader;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.kangsoo.pharmacy.util.ToastUtil;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.typed.JSONObjectAsyncTaskCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import static com.kangsoo.pharmacy.activity.NavigationDrawerObject.TYPE_SEPERATOR;

/**
 * Created by bsnc on 2015-05-10.
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        LoaderManager.LoaderCallbacks<List<User>> {

    private static final String TAG = "MainActivity";

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private List<User> orgs = Collections.emptyList();

    private NavigationDrawerAdapter navigationAdapter;

    private User org;

    @Inject
    private AvatarLoader avatars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        SettingsUtil.init(this);

        getSupportLoaderManager().initLoader(0, null, this);

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


    private void reloadOrgs() {
        getSupportLoaderManager().restartLoader(0, null, this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        UsersAsyncTask task = new UsersAsyncTask(MainActivity.this);
        task.execute();
        signIn();

        // Restart loader if default account doesn't match currently loaded
        // account
        List<User> currentOrgs = orgs;

//        if (currentOrgs != null && !currentOrgs.isEmpty() && !AccountUtils.isUser(this, currentOrgs.get(0))){
        if (currentOrgs != null && !currentOrgs.isEmpty()) {
            reloadOrgs();
        }

//        reloadOrgs();

    }

    private void signIn() {

        Session session = SettingsUtil.getSession();

        SignIn.signIn(session, new JSONObjectAsyncTaskCallback() {

            @Override
            public void onSuccess(JSONObject userJSONObject) {
                try {
                    org = new User(userJSONObject);
                    ToastUtil.show(MainActivity.this, "User name: " + org.getName());
                } catch (JSONException e) {
                    onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.show(MainActivity.this, "Authentication failed! " + e.getMessage());
            }

        });
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
                args.putSerializable("org", orgs.get(position - 6));
                break;
        }

        fragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public Loader<List<User>> onCreateLoader(int i, Bundle bundle) {
//        return new OrganizationLoader(this, accountDataManager,userComparatorProvider);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {

        if (orgs.isEmpty())
            return;

        org = orgs.get(0);
        this.orgs = orgs;

        if (navigationAdapter != null)
            navigationAdapter.setOrgs(orgs);
        else {
            navigationAdapter = new NavigationDrawerAdapter(MainActivity.this, orgs, avatars);
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout), navigationAdapter, avatars, org);

            Window window = getWindow();
            if (window == null)
                return;
            View view = window.getDecorView();
            if (view == null)
                return;

            view.post(new Runnable() {

                @Override
                public void run() {
                    MainActivity.this.onNavigationDrawerItemSelected(1);
                }
            });
        }

    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {

    }

}
