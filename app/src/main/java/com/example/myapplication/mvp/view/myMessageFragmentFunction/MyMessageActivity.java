package com.example.myapplication.mvp.view.myMessageFragmentFunction;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.contract.MyMessageContract;
import com.example.myapplication.mvp.presenter.MyMessagePresenter;
import com.example.myapplication.mvp.view.loginAndregister.LoginActivity;
import com.example.myapplication.utils.Camera.CameraUtil;
import com.example.myapplication.utils.Photo.OkhttpUpPhoto;
import com.example.myapplication.utils.alertUtils.BottomDialog;
import com.example.myapplication.utils.erweima.CreateQRcodeBitmap;
import com.hb.dialog.dialog.ConfirmDialog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myapplication.utils.Photo.OkhttpUpPhoto.okhttpUpPhoto;


public class MyMessageActivity extends BaseActivity implements View.OnClickListener ,MyMessageContract.activity {


    private TextView quit;
    Toolbar toolbar;
    private TextView save;
    private EditText name_edit;
    private TextView sex_text;
    private EditText telephone_edit;
    private EditText personal_edit;
    private ImageView QRcode_img;
    private LinearLayout linearLayout;
    private CircleImageView circleImageView;
    private String circleImageViewPath;
    private String username;
    private int width=200;
    private int height=200;
    private String url;
    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;
    private CreateQRcodeBitmap createQRcode;
    private SharedPreferences pref;
    private MyMessageContract.presenter myMessagePresenter;

    @Override
    public void setPresenter(MyMessageContract.presenter presenter) {
        this.myMessagePresenter=presenter;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_header;
    }


    @Override
    protected void initView() {
        pref = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
        username=pref.getString("currentUserName","");
        circleImageViewPath=pref.getString("currentUserImageHead","");
        circleImageView = findViewById(R.id.CircleImageView);
        try {
            username= URLDecoder.decode(username, "UTF-8");
            url = "http://47.92.142.206:8080/images/"+username+".jpg";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("============="+url);
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.defaultcoursebackground)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                    .into(circleImageView);
        linearLayout = findViewById(R.id.touxiang_linearlayout);
        linearLayout.setOnClickListener(this);
        save=findViewById(R.id.savetitle);
        quit=findViewById(R.id.quit);
        name_edit=findViewById(R.id.name_edit);
        name_edit.setText(pref.getString("currentUserNickName",""));
        sex_text=findViewById(R.id.sex_text);
        sex_text.setText(pref.getString("currentUserGender",""));
        telephone_edit=findViewById(R.id.telephone_edit);
        telephone_edit.setText(pref.getString("currentUserTelephone",""));
        personal_edit=findViewById(R.id.personal_edit);
        personal_edit.setText(pref.getString("currentUserSignature",""));
        QRcode_img=findViewById(R.id.QRcode);
        myMessagePresenter = new MyMessagePresenter(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.my_header_toolbar);

        /**添加监听*/
        quit.setOnClickListener(this);
        save.setOnClickListener(this);
        name_edit.setOnClickListener(this);
        sex_text.setOnClickListener(this);
        telephone_edit.setOnClickListener(this);
        personal_edit.setOnClickListener(this);


        /**创建二维码*/
        createQRcode=CreateQRcodeBitmap.getInstance();
        Bitmap bitmap2 = createQRcode.createQRCodeBitmap(username,width,height);
        QRcode_img.setImageBitmap(bitmap2);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touxiang_linearlayout:
                new BottomDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                        CameraUtil.openCamera(MyMessageActivity.this);
                                        Toast.makeText(getApplicationContext(), "选择了拍照", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("从相册选择", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                        Toast.makeText(getApplicationContext(), "选择了相册", Toast.LENGTH_SHORT).show();
                                        if (ContextCompat.checkSelfPermission(MyMessageActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(MyMessageActivity.this, new
                                                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                        } else {
                                            //打开系统相册
                                            okhttpUpPhoto= OkhttpUpPhoto.getInstance();
                                            okhttpUpPhoto.openAlbum(MyMessageActivity.this);
                                        }
                                    }
                                })
                        .addSheetItem("查看原图", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                        Toast.makeText(getApplicationContext(), "选择了查看", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                break;
            case R.id.quit:
                pref = getSharedPreferences("currentUserMessage",MODE_PRIVATE);
                SharedPreferences.Editor editor =pref.edit();
                editor.clear();
                editor.commit();
                System.out.println(pref.getString("identity",""));
                System.out.println(pref.getString("currentUserName",""));
                changeActivity(LoginActivity.class);
                break;
            case R.id.savetitle:
                confirmSave(this);
                break;
            case R.id.name_edit:

            case R.id.sex_text:
                new BottomDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("男", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sex_text.setText("男");
                                    }
                                })
                        .addSheetItem("女", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sex_text.setText("女");
                                    }
                                }).show();
                break;
            case R.id.telephone_edit:

            case R.id.personal_edit:

        }
    }



    /**
     *  确认提示的方法
     * @param context 当前场景，背景，上下文
     */
    void confirmSave(Context context){
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("确认保存");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                String nickName = name_edit.getText().toString();
                String gender = sex_text.getText().toString();
//                String telephone = telephone_edit.getText().toString();
                String signature = personal_edit.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences("currentUserMessage",MODE_PRIVATE).edit();
                if(nickName.equals(pref.getString("currentUserNickName",""))
                        && gender.equals(pref.getString("currentUserGender",""))
                        && signature.equals(pref.getString("currentUserSignature",""))
                        && circleImageViewPath.equals(pref.getString("currentUserImageHead",""))
                ){
                    toastCenter(getApplicationContext(),"信息并无变化");
                }else{
                    File  file = new File(circleImageViewPath);
                    myMessagePresenter.AddPersonalInformationRequest(nickName,gender,signature,username,file);
                }
                editor.putString("currentUserNickName",nickName);
                editor.putString("currentUserGender", gender);
                editor.putString("currentUserSignature",signature);
                editor.apply();
                MyMessageActivity.this.finish();
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
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
                circleImageViewPath=okhttpUpPhoto.handleImageOnKitkat(this,data);
                displayImage(circleImageViewPath);
            } else {
                circleImageViewPath=okhttpUpPhoto.handleImageBeforeKitkat(this,data);
                displayImage(circleImageViewPath);
            }
        }else if (requestCode == 2 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(photo);
        }else if(requestCode== 3 && requestCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(photo);
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            circleImageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void AddPersonalInformationSuccessful(String result) {
        System.out.println("信息保存成功后："+result);
    }

}
