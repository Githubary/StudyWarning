package com.example.myapplication.mvp.view.generalRevision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.loginAndregister.LoginActivity;
import com.example.myapplication.utils.Photo.OkhttpUpPhoto;
import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.dialog.LoadingDialog;

public class studentStartAnwserActivity extends BaseActivity implements View.OnClickListener{

    private TextView up_answer;
    private TextView pai_answer;
    private ImageView your_answer;
    private TextView finish_answer;
    /**Loading的dialog*/
    private LoadingDialog loadingDialog;
    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;

    /**传入照片Util*/
    OkhttpUpPhoto okhttpUpPhoto;

    /**照片路径*/
    private String imagePath;

    @Override
    protected int initLayout() {
        return R.layout.activity_student_start_answer;
    }

    @Override
    protected void initView() {
        up_answer=findViewById(R.id.up_answer);
        pai_answer=findViewById(R.id.pai_answer);
        your_answer=findViewById(R.id.your_answer);
        finish_answer=findViewById(R.id.finish_answer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        up_answer.setOnClickListener(this);
        pai_answer.setOnClickListener(this);
        finish_answer.setOnClickListener(this);

        okhttpUpPhoto=OkhttpUpPhoto.getInstance();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.up_answer:
                if (ContextCompat.checkSelfPermission(studentStartAnwserActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(studentStartAnwserActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //打开系统相册
                    okhttpUpPhoto.openAlbum(this);
                }
                break;
            case R.id.pai_answer:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,2);
                break;
            case R.id.finish_answer:
                loading(this);
                break;

        }
    }
    SharedPreferences.Editor editor;
    /**
     * 加载页面时等待过程
     * @param context 当前场景，背景，上下文
     */
    void loading(Context context){
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setMessage("loading");
        loadingDialog.show();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);//休眠3秒
                    loadingDialog.dismiss();
                    editor= getSharedPreferences("answerChangeState",MODE_PRIVATE).edit();
                    editor.putString("state","1");
                    editor.apply();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    okhttpUpPhoto.openAlbum(this);
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    /**
     * 当选择完图片后会回到onActivityResult方法中，在该方法中来处理图片。
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            if (Build.VERSION.SDK_INT >= 19) {
                imagePath=okhttpUpPhoto.handleImageOnKitkat(this,data);
            } else {
                imagePath=okhttpUpPhoto.handleImageBeforeKitkat(this,data);
            }
        }else if (requestCode == 2 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            your_answer.setImageBitmap(photo);
        }
    }



    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            your_answer.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
