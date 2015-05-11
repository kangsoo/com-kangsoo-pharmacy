package com.kangsoo.pharmacy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.CameraFragment;
import com.kangsoo.pharmacy.listener.CameraFragmentListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Activity displaying the camera and android preview.
 *
 * @author Sebastian Kaspari <sebastian@kangsoo.com>
 */
public class CameraActivity extends Fragment implements CameraFragmentListener {

    public static final String TAG = "Mustache/CameraActivity";
    private static final int PICTURE_QUALITY = 90;
    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.activity_camera, container, false);

        return v;
    }


    /**
     * On fragment notifying about a non-recoverable problem with the camera.
     */
    @Override
    public void onCameraError() {

        Toast.makeText(getActivity(), getString(R.string.toast_error_camera_preview), Toast.LENGTH_SHORT).show();
        getActivity().finish();

    }

    /**
     * The user wants to take a picture.
     *
     * @param view
     */
    public void takePicture(View view) {

        view.setEnabled(false);

        CameraFragment fragment = (CameraFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.camera_fragment);

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
                getActivity(),
                new String[]{mediaFile.toString()},
                new String[]{"image/jpeg"},
                null
        );

        Intent intent = new Intent(getActivity(), PhotoActivity.class);
        intent.setData(Uri.fromFile(mediaFile));
        startActivity(intent);

        getActivity().finish();
    }

    private void showSavingPictureErrorToast() {
        Toast.makeText(getActivity(), getText(R.string.toast_error_save_picture), Toast.LENGTH_SHORT).show();
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
