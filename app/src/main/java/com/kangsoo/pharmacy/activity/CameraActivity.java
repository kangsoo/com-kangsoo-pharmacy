package com.kangsoo.pharmacy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.CameraFragment;
import com.kangsoo.pharmacy.listener.CameraFragmentListener;
import com.kangsoo.pharmacy.model.User;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.kangsoo.pharmacy.util.ToastUtil;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.typed.JSONObjectAsyncTaskCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Activity displaying the camera and android preview.
 *
 * @author Sebastian Kaspari <sebastian@kangsoo.com>
 */
public class CameraActivity extends FragmentActivity implements CameraFragmentListener {

    public static final String TAG = "Mustache/CameraActivity";
    private static final int PICTURE_QUALITY = 90;
    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;
    private boolean exitConfirmation = false;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;


    /**
     * On activity getting created.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        SettingsUtil.init(this);

        //Tabs
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);
        //End Tabs


    }

    @Override
    protected void onResume() {
        super.onResume();

        //kskim to-do : delete in future
        //UsersAsyncTask task = new UsersAsyncTask(MainActivity.this);
        //task.execute();

        signIn();

    }

    @Override
    public void onBackPressed() {

        if (exitConfirmation) {
            super.onBackPressed();
            return;
        }

        this.exitConfirmation = true;
        Toast.makeText(CameraActivity.this, "'뒤로'버튼을 한번더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();

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
        Toast.makeText(this, getString(R.string.toast_error_camera_preview), Toast.LENGTH_SHORT).show();

        finish();
    }

    /**
     * The user wants to take a picture.
     *
     * @param view
     */
    public void takePicture(View view) {
        view.setEnabled(false);

        CameraFragment fragment = (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.camera_fragment);

        fragment.takePicture();
    }

    /**
     * A picture has been taken.
     */
    public void onPictureTaken(Bitmap bitmap) {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ),
                getString(R.string.app_name)
        );

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                showSavingPictureErrorToast();
                return;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(
                mediaStorageDir.getPath() + File.separator + "KANGSOO_" + timeStamp + ".jpg"
        );

        try {
            FileOutputStream stream = new FileOutputStream(mediaFile);
            bitmap.compress(CompressFormat.JPEG, PICTURE_QUALITY, stream);
        } catch (IOException exception) {
            showSavingPictureErrorToast();

            Log.w(TAG, "IOException during saving bitmap", exception);
            return;
        }

        MediaScannerConnection.scanFile(
                this,
                new String[]{mediaFile.toString()},
                new String[]{"image/jpeg"},
                null
        );

        Intent intent = new Intent(this, PhotoActivity.class);
        intent.setData(Uri.fromFile(mediaFile));
        startActivity(intent);

        finish();
    }

    private void showSavingPictureErrorToast() {
        Toast.makeText(this, getText(R.string.toast_error_save_picture), Toast.LENGTH_SHORT).show();
    }


    protected void signIn() {
        Session session = SettingsUtil.getSession();

        SignIn.signIn(session, new JSONObjectAsyncTaskCallback() {

            @Override
            public void onSuccess(JSONObject userJSONObject) {
                try {
                    User user = new User(userJSONObject);

                    ToastUtil.show(CameraActivity.this, "User name: " + user.getName());
                } catch (JSONException e) {
                    onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.show(CameraActivity.this, "Authentication failed! " + e.getMessage());
            }

        });
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("unchecked")
    public void updateUsers(ArrayList<User> users) {

        //ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
/*
        if (users != null) {
            Log.i("MSG", "updateUsers Called. users Count: " + users.size());
        }

        userAdapter.clear();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            userAdapter.addAll(users);
        } else {
            for (User user : users) {
                userAdapter.add(user);
            }
        }
        */
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"촬영", "전송이력", "복약지도", "처방내역"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

    }

}
