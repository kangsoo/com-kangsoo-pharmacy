package com.kangsoo.pharmacy.activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
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
import com.kangsoo.pharmacy.task.WikiUploadAsyncTask;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.kangsoo.pharmacy.util.ToastUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Activity displaying the taken photo and offering to share it with other apps.
 *
 * @author Sebastian Kaspari <sebastian@kangsoo.com>
 */
public class PhotoActivity extends Fragment {

    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;
    private InputStream fileInputStream;
    private boolean exitConfirmation = false;
    private AnimatorSet _topBarIconAnimation;
    private ImageView _topBarIcon;
    private WifiManager wifiManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        uri = getActivity().getIntent().getData();

        View v = inflater.inflate(R.layout.activity_photo, container, false);

        ImageView photoView = (ImageView) getActivity().findViewById(R.id.photo);
        photoView.setImageURI(uri);

        _topBarIcon = (ImageView) getActivity().findViewById(R.id.top_bar_icon);
        _topBarIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PhotoActivity.this._animateTopBarIcon();

                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }

        });

        // The MAGIC happens here!
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(photoView);

        return v;

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

            case R.id.sendPhoto:
                sendPhotoToServer(item);

            default:
                return false;
        }
    }

    private void sendPhotoToServer(MenuItem item) {

        //wifi를 사용하겠다고 하면...
        if (SettingsUtil.getUsingwifi().toString().equals("false")) {
            wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }

        WikiUploadAsyncTask task = new WikiUploadAsyncTask(getActivity());
        try {
            fileInputStream = getActivity().getContentResolver().openInputStream(uri);
            task.execute(uri.getPath(), Integer.toString(ToastUtil.getBytes(fileInputStream).length));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            item.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            item.setEnabled(true);
        } finally {
            item.setEnabled(false);
        }
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
}
