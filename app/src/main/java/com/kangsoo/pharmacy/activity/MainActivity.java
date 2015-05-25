package com.kangsoo.pharmacy.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.CameraFragment;
import com.kangsoo.pharmacy.fragment.HomePagerFragment;
import com.kangsoo.pharmacy.fragment.UserLoader;
import com.kangsoo.pharmacy.listener.CameraFragmentListener;
import com.kangsoo.pharmacy.model.ShoppingCategory;
import com.kangsoo.pharmacy.model.User;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static com.kangsoo.pharmacy.activity.NavigationDrawerObject.TYPE_SEPERATOR;

/**
 * Created by bsnc on 2015-05-10.
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        UserLoader.UserLoaderCallbacks, CameraFragmentListener {

    private static final String TAG = "MainActivity";
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private List<User> orgs = Collections.emptyList();
    private NavigationDrawerAdapter navigationAdapter;
    public static User org;
    private boolean exitConfirmation = false;

    //Error RunTime - Java
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        UserLoader userLoader = new UserLoader(this);
        userLoader.init();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

//        onNavigationDrawerItemSelected(0);
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
    public void onNavigationDrawerItemSelected(int position) {

        if (navigationAdapter.getItem(position).getType() == TYPE_SEPERATOR) {
            return;
        }

        Fragment fragment = null;
        Bundle args = new Bundle();

        switch (position) {
            case 1:
                fragment = new HomePagerFragment();
                args.putSerializable("org", org);
                break;

//
//            case 2:
////                fragment = new GistsPagerFragment();
//                break;
//
//            case 3:
////                fragment = new IssueDashboardPagerFragment();
//                break;
//
//            case 4:
////                fragment = new FilterListFragment();
//                break;
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

        TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        String sDeviceID = mTelephonyMgr.getDeviceId();
        String sSimSerial = mTelephonyMgr.getSimSerialNumber();
        String number = mTelephonyMgr.getLine1Number();

        if (number != null) {
            user.setPhoneNumber(number);
        }

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
                    MainActivity.this.onNavigationDrawerItemSelected(1);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        if (exitConfirmation) {
            super.onBackPressed();
            return;
        }

        this.exitConfirmation = true;
        Toast.makeText(MainActivity.this, "'뒤로'버튼을 한번더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                exitConfirmation = false;
            }
        }, 2000);
    }

    /**
     * On fragment notifying about a non-recoverable problem with the camera.
     */
    @Override
    public void onCameraError() {
        //kskim to-do list
        Toast.makeText(MainActivity.this, "카메라가 정상동작하고 있지않습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPictureTaken(Bitmap bitmap) {

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        HomePagerFragment homePagerFragment = (HomePagerFragment) fragmentManager.findFragmentById(R.id.container);
//        CameraActivity cameraActivity = (CameraActivity) homePagerFragment.getChildFragmentManager().findFragmentById(R.id.vp_pages);
//        cameraActivity.onPictureTaken(bitmap);

        CameraActivity cameraActivity = getVisibleCameraActivityFragment();
        cameraActivity.onPictureTaken(bitmap);

        HomePagerFragment homePagerFragment = getVisibleHomePagerFragment();
        homePagerFragment.setCurrentItem(1);
//        homePagerFragment.setPagePosition(2);

        //kskim to-do list
        Toast.makeText(MainActivity.this, "사진촬영이 정상적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTakePicture() {

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        HomePagerFragment homePagerFragment = (HomePagerFragment) fragmentManager.findFragmentById(R.id.container);
//        CameraActivity cameraActivity = (CameraActivity) homePagerFragment.getChildFragmentManager().findFragmentById(R.id.vp_pages);
//        CameraFragment cameraFragment = (CameraFragment) cameraActivity.getChildFragmentManager().findFragmentById(R.id.camera_fragment);
//        cameraFragment.takePicture();

        CameraFragment cameraFragment = getVisibleCameraFragmentFragment();
        cameraFragment.takePicture();

        //kskim to-do list
        Toast.makeText(MainActivity.this, "사진촬영 시작", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMovetoPhotoPreview(File pFile) {

        PhotoActivity photoActivity = getVisiblePhotoActivityFragment();
        photoActivity.onPreview(pFile);

    }

    @Override
    public void onMovetoDisplayShoppingItem(ShoppingCategory shoppingCategory) {

        HomePagerFragment homePagerFragment = getVisibleHomePagerFragment();
        homePagerFragment.setCurrentItem(4);

//        ShoppingItemListFragment shoppingItemListFragment = getVisibleShoppingItemFragment();

    }

    public CameraFragment getVisibleCameraFragmentFragment() {

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {

                if (fragment instanceof HomePagerFragment) {

                    List<Fragment> child_fragments = fragment.getChildFragmentManager().getFragments();

                    for (Fragment child_fragment : child_fragments) {
                        if (child_fragment != null && child_fragment.getUserVisibleHint()) {

                            if (child_fragment instanceof CameraActivity) {

                                List<Fragment> child_Camera_fragments = child_fragment.getChildFragmentManager().getFragments();

                                for (Fragment child_Camera_fragment : child_Camera_fragments) {
                                    if (child_Camera_fragment != null && child_Camera_fragment.getUserVisibleHint()) {
                                        if (child_Camera_fragment instanceof CameraFragment) {
                                            return (CameraFragment) child_Camera_fragment;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public CameraActivity getVisibleCameraActivityFragment() {

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {

                if (fragment instanceof HomePagerFragment) {

                    List<Fragment> child_fragments = fragment.getChildFragmentManager().getFragments();

                    for (Fragment child_fragment : child_fragments) {
                        if (child_fragment != null && child_fragment.getUserVisibleHint()) {

                            if (child_fragment instanceof CameraActivity) {
                                return (CameraActivity) child_fragment;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public PhotoActivity getVisiblePhotoActivityFragment() {

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {

                if (fragment instanceof HomePagerFragment) {

                    List<Fragment> child_fragments = fragment.getChildFragmentManager().getFragments();

                    for (Fragment child_fragment : child_fragments) {
                        if (child_fragment instanceof PhotoActivity) {
                            return (PhotoActivity) child_fragment;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ShoppingItemListFragment getVisibleShoppingItemFragment() {

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {

                if (fragment instanceof HomePagerFragment) {

                    List<Fragment> child_fragments = fragment.getChildFragmentManager().getFragments();

                    for (Fragment child_fragment : child_fragments) {
                        if (child_fragment instanceof ShoppingItemListFragment) {
                            return (ShoppingItemListFragment) child_fragment;
                        }
                    }
                }
            }
        }
        return null;
    }

    public HomePagerFragment getVisibleHomePagerFragment() {

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {

                if (fragment instanceof HomePagerFragment) {
                    return (HomePagerFragment) fragment;
                }
            }
        }
        return null;
    }

}
