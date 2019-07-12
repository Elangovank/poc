package com.poc.app.helper;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

public class ImageLoader {


    /**
     * load Image View using Universal Image Loader
     *
     * @param aContext      FragmentActivity
     * @param aImageView    ImageView
     * @param aImageUrlStr  String
     * @param aDefaultImage int
     * @param aErrorImage   int
     */
    public static void loadImage(FragmentActivity aContext, final ImageView aImageView, String aImageUrlStr, int aDefaultImage, int aErrorImage) {
        try {
            com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(aContext));
            DisplayImageOptions options = new DisplayImageOptions.Builder()

                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(ContextCompat.getDrawable(aContext, aDefaultImage))
                    .showImageOnFail(ContextCompat.getDrawable(aContext, aErrorImage))
                    .showImageOnLoading(ContextCompat.getDrawable(aContext, aDefaultImage))
                    .build();


            //download and display image from url
            imageLoader.displayImage(aImageUrlStr, aImageView, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    //   Log.d(TAG, "onLoadingComplete: " + s);

                }


                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private Bitmap rotate(Bitmap bitmap, Uri uri) {
        Bitmap myBitmap = bitmap;

        try {
            ExifInterface exif = new ExifInterface(new File(uri.getPath()).getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true); // rotating bitmap

            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
