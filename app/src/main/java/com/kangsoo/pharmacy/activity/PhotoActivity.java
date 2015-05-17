package com.kangsoo.pharmacy.activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.ShareActionProvider;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.listener.CameraFragmentListener;
import com.kangsoo.pharmacy.model.User;
import com.kangsoo.pharmacy.task.WikiUploadAsyncTask;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.kangsoo.pharmacy.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Activity displaying the taken photo and offering to share it with other apps.
 *
 * @author Sebastian Kaspari <sebastian@kangsoo.com>
 */
public class PhotoActivity extends Fragment implements View.OnClickListener {

    private CameraFragmentListener cameraFragmentListener;
    private PhotoViewAttacher mAttacher;

    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;
    private InputStream fileInputStream;
    private boolean exitConfirmation = false;
    private AnimatorSet _topBarIconAnimation;
    private ImageView _topBarIcon;
    private WifiManager wifiManager;

    private User mUser;
    private final String DESCRIBABLE_KEY = "com.kangsoo.MESSAGE";
    private ImageView photoView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        cameraFragmentListener = (CameraFragmentListener) activity;
        mUser = MainActivity.org;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //kskim to-do merge tag ==> replace to FrmaeLayout
        View v = inflater.inflate(R.layout.activity_photo, container, false);

        File file;
        file = (File) getArguments().getSerializable("file");
        if (file != null) {
            uri = Uri.fromFile(file);
        }

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        photoView = (ImageView) getActivity().findViewById(R.id.photo);
        if (uri == null) {
            photoView.setImageResource(R.drawable.ic_action_camera);
        } else {
            photoView.setImageURI(uri);
        }

        _topBarIcon = (ImageView) getActivity().findViewById(R.id.top_bar_icon);
        _topBarIcon.setOnClickListener(this);

//        // The MAGIC happens here!
//        if (mAttacher == null) {
//            mAttacher = new PhotoViewAttacher(photoView);
//        }
////        PhotoViewAttacher mAttacher = new PhotoViewAttacher(photoView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getActivity().getMenuInflater().inflate(R.menu.activity_photo, menu);

//        initializeShareAction(menu.findItem(R.id.share));
//        return super.onCreateOptionsMenu(menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                return true;

            case R.id.share:
                initializeShareAction(item);
                return true;

            default:
                return false;
        }
    }

    private void sendPhotoToServer() {

        //wifi를 사용하겠다고 하면...
        if (SettingsUtil.getUsingwifi().equals("false")) {
            wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }

        WikiUploadAsyncTask task = new WikiUploadAsyncTask(getActivity());
        try {
            fileInputStream = getActivity().getContentResolver().openInputStream(uri);
            task.execute(uri.getPath(), Integer.toString(ToastUtil.getBytes(fileInputStream).length), mUser.getPhoneNumber());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        _topBarIcon.setEnabled(false);
    }

    private void initializeShareAction(MenuItem shareItem) {

        ShareActionProvider shareProvider = (ShareActionProvider) shareItem.getActionProvider();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType(MIME_TYPE);

        shareProvider.setShareIntent(shareIntent);
    }

    private void _animateTopBarIcon() {
        if (_topBarIconAnimation == null) {
            Resources resources = getResources();
            long duration = resources.getInteger(R.integer.top_bar_icon_animation_duration);

            _topBarIconAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.top_bar_icon_scale);
            _topBarIconAnimation.setTarget(_topBarIcon);
            _topBarIconAnimation.setDuration(duration);
            _topBarIconAnimation.setInterpolator(new CycleInterpolator(2));
        }

        if (!_topBarIconAnimation.isStarted()) {
            _topBarIconAnimation.start();
        }
    }

    @Override
    public void onClick(View v) {

        PhotoActivity.this._animateTopBarIcon();
        //cameraFragmentListener.onTakePicture();
        sendPhotoToServer();

    }

    public void onPreview(File pFile) {

        if (pFile != null) {
            uri = Uri.fromFile(pFile);
        }
        if (uri == null) {
            photoView.setImageResource(R.drawable.ic_action_camera);
        } else {
            photoView.setImageURI(uri);
        }

//        if(mAttacher == null){
//            mAttacher = new PhotoViewAttacher(photoView);
//        }
    }
}
