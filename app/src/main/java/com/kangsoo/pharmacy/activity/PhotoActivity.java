package com.kangsoo.pharmacy.activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

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
public class PhotoActivity extends FragmentActivity {

    private static final String MIME_TYPE = "image/jpeg";
    private Uri uri;
    private InputStream fileInputStream;
    private boolean exitConfirmation = false;
    private AnimatorSet _topBarIconAnimation;
    private ImageView _topBarIcon;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsUtil.init(this);

        uri = getIntent().getData();

        setContentView(R.layout.activity_photo);

        ImageView photoView = (ImageView) findViewById(R.id.photo);
        photoView.setImageURI(uri);

        _topBarIcon = (ImageView) findViewById(R.id.top_bar_icon);
        _topBarIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PhotoActivity.this._animateTopBarIcon();

                Intent intent = new Intent(PhotoActivity.this, CameraActivity.class);
                startActivity(intent);
            }

        });

        // The MAGIC happens here!
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(photoView);
    }

    @Override
    public void onBackPressed() {

        if (exitConfirmation) {
            super.onBackPressed();
            return;
        }

        this.exitConfirmation = true;
        Toast.makeText(PhotoActivity.this, "'뒤로'버튼을 한번더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                exitConfirmation = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_photo, menu);

//        initializeShareAction(menu.findItem(R.id.share));
//        return super.onCreateOptionsMenu(menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
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
            wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }

        WikiUploadAsyncTask task = new WikiUploadAsyncTask(PhotoActivity.this);
        try {
            fileInputStream = PhotoActivity.this.getContentResolver().openInputStream(uri);
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

            _topBarIconAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.top_bar_icon_scale);
            _topBarIconAnimation.setTarget(_topBarIcon);
            _topBarIconAnimation.setDuration(duration);
            _topBarIconAnimation.setInterpolator(new CycleInterpolator(2));
        }

        if (!_topBarIconAnimation.isStarted()) {
            _topBarIconAnimation.start();
        }
    }
}
