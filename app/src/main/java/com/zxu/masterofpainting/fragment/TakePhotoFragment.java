package com.zxu.masterofpainting.fragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.PhysiqueActivity;
import com.zxu.masterofpainting.activity.ShowIngredientsActivity;
import com.zxu.masterofpainting.activity.TakePhotoActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

public class TakePhotoFragment extends Fragment {
    private Handler handler = null;
    private Bitmap bitmap;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOSE_PHOTO = 2;

    private ImageView picture_Distinguish;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_takephoto,container,false);
        iniview(view);
        return view;
    }

    public void iniview(View view){

    }

}
