package rui.todd;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyActivity extends AppCompatActivity {

    @BindView(R.id.view)
    ImageView view;
    @BindView(R.id.video)
    VideoView video;
    String mCurrentPhotoPath;
    File photoFile = null;
    private final int VIDEO_PERMISSION = 2;
    private final int PIC_PERMISSION = 1;
    private final int PIC_REQUEST_CODE = 1001;
    private final int VIDEO_REQUEST_CODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ButterKnife.bind(this);
//        studyPic();
//        studyVideo();
        BlankFragment fragment = new BlankFragment();
        BlankFragment2 fragment2 = new BlankFragment2();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction1 = manager.beginTransaction();
        FragmentTransaction transaction2 = manager.beginTransaction();
        transaction1.add(R.id.container, fragment);
        transaction2.add(R.id.container, fragment2);
        transaction1.commitAllowingStateLoss();
        transaction2.addToBackStack("2");
        transaction2.commitAllowingStateLoss();
    }

    private void studyVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, VIDEO_REQUEST_CODE);
        }
    }

    private void studyPic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "需要此权限", Toast.LENGTH_SHORT).show();
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PIC_PERMISSION);
                    }
                } else {
                    photoFile = createImageFile();
                    if (photoFile != null) {
                        Uri photoUri = FileProvider.getUriForFile(this, "rui.todd.fileprovider", photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 1001);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photoFile);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIC_REQUEST_CODE && resultCode == RESULT_OK) {
            galleryAddPic();
        }
        if (requestCode == VIDEO_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            video.setVideoURI(uri);
            video.start();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PIC_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        photoFile = createImageFile();
                        if (photoFile != null) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            startActivityForResult(intent, 1001);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            break;
        }
    }
}
