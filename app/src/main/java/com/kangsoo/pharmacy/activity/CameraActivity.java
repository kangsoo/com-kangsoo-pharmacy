package com.kangsoo.pharmacy.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.fragment.CameraFragment;
import com.kangsoo.pharmacy.listener.CameraFragmentListener;
import com.kangsoo.pharmacy.model.User;

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
public class CameraActivity extends Fragment implements View.OnClickListener {

    //kskim to-do list
    CameraFragmentListener cameraFragmentListener;

    public final static String EXTRA_MESSAGE = "com.kangsoo.MESSAGE";

    public static final String TAG = "Mustache/CameraActivity";
    private static final int PICTURE_QUALITY = 90;
    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private User mUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

//        mUser = (User) getArguments().getSerializable('org');
        View v = inflater.inflate(R.layout.activity_camera, container, false);
        insertNestedFragment();
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cameraFragmentListener = (CameraFragmentListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton button = (ImageButton) getActivity().findViewById(R.id.ibTakePicture);
        button.setOnClickListener(this);
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment childFragment = new CameraFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.camera_fragment, childFragment).commit();
    }

    // Embeds the child fragment dynamically
    private void replaceNestedFragment(File pFile) {

        Fragment photoActivity = new PhotoActivity();

        Bundle args = new Bundle();
        args.putSerializable("file", pFile);
        photoActivity.setArguments(args);

//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.camera_fragment, photoActivity).commit();
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

/*
        Intent intent = new Intent(getActivity(), PhotoActivity.class);
        intent.setData(Uri.fromFile(mediaFile));
        intent.putExtra("USER", mUser);
        startActivity(intent);
*/

/*
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PhotoActivity photoActivity = new PhotoActivity();
        ft.replace(R.id.camera_fragment, photoActivity);
        ft.commit();
*/

        //Uri.fromFile(mediaFile)
        replaceNestedFragment(mediaFile);

//        getActivity().finish();
    }

    private void showSavingPictureErrorToast() {
        Toast.makeText(getActivity(), getText(R.string.toast_error_save_picture), Toast.LENGTH_SHORT).show();
    }

    /**
     * The user wants to take a picture.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        v.setEnabled(false);

        cameraFragmentListener.onTakePicture();

//        CameraFragment fragment = (CameraFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
//        fragment.takePicture();

    }
}
