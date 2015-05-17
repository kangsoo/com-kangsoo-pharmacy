package com.kangsoo.pharmacy.listener;

import android.graphics.Bitmap;

import com.kangsoo.pharmacy.fragment.CameraFragment;

import java.io.File;

/**
 * Listener interface that has to be implemented by activities using
 * {@link CameraFragment} instances.
 *
 * @author Sebastian Kaspari <sebastian@kangsoo.com>
 */
public interface CameraFragmentListener {
    /**
     * A non-recoverable camera error has happened.
     */
    public void onCameraError();

    /**
     * A picture has been taken.
     *
     * @param bitmap
     */
    public void onPictureTaken(Bitmap bitmap);

    /**
     * Take a Picture
     */
    public void onTakePicture();

    /**
     * After Take a Picture, go to PreviewPage
     */
    public void onMovetoPhotoPreview(File pFile);

}
