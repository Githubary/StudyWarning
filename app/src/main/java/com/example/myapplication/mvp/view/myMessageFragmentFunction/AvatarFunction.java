package com.example.myapplication.mvp.view.myMessageFragmentFunction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.myapplication.R;

import java.io.File;

import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarFunction  extends AppCompatActivity implements View.OnClickListener {

        private static final int ACTION_TYPE_PHOTO = 0;
        private final        int CAMERA            = 10;
        private final        int ALBUM             = 20;
        private final        int CUPREQUEST        = 50;
        private CircleImageView mCivMainHead;
        private PopupWindow mPopupWindow;
        private String          picPath;
        private File mOutImage;
        private Uri mImageUri;
        private Uri uritempFile;
       @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_header);
            mCivMainHead = (CircleImageView) findViewById(R.id.CircleImageView);
            mCivMainHead.setOnClickListener(this);
            //申请权限
           ActivityCompat.requestPermissions(AvatarFunction.this,new String[]{
                   Manifest.permission.CAMERA,
                   Manifest.permission.WRITE_EXTERNAL_STORAGE,
                   Manifest.permission.READ_EXTERNAL_STORAGE},
                    ACTION_TYPE_PHOTO);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.CircleImageView:
                    Toast.makeText(this,"sbhjasf",Toast.LENGTH_SHORT).show();
                    initPhotoTypePop();
                    break;
                default:
                    break;
            }
        }

        /**
         * 初始化选择头像的pop
         */
        private void initPhotoTypePop() {
            mPopupWindow = showBotoomSmallWidthPopup(AvatarFunction.this,
                    R.layout.popu_photo, mCivMainHead);
            View contentView = mPopupWindow.getContentView();
            final TextView mobile_phones = (TextView) contentView.findViewById(R.id.mobile_phones);
            final TextView local_photo = (TextView) contentView.findViewById(R.id.local_photo);
            final TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
            mobile_phones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMobilePhones();
                }
            });

            local_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setLocalPhoto();

                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopupWindow.dismiss();
                }
            });
        }

        /**
         * 相册
         */
        private void setLocalPhoto() {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
            Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
            albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(albumIntent, ALBUM);
        }

        /**
         * 拍照
         */
        private void setMobilePhones() {
            if (mPopupWindow !=null && mPopupWindow.isShowing()){
                mPopupWindow.dismiss();
            }
            //获得项目缓存路径
            String sdPath = getExternalCacheDir().getPath();
            //根据时间随机生成图片名
            String photoName = new DateFormat().format("yyyyMMddhhmmss",
                    Calendar.getInstance(Locale.CHINA)) + ".jpg";
            picPath = sdPath + "/" + photoName;
            mOutImage = new File(picPath);
            //如果是7.0以上 那么就把uir包装
            if (Build.VERSION.SDK_INT >= 24) {
                mImageUri = FileProvider.getUriForFile(AvatarFunction.this, "com.test.FileProvider", mOutImage);
            } else {
                //否则就用老系统的默认模式
                mImageUri = Uri.fromFile(mOutImage);
            }
            //启动相机
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(intent, CAMERA);
        }


        /**
         * 选择图片后的回调
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode != RESULT_OK) {
                return;
            }
            switch (requestCode) {

                // 裁剪相机照片
                case CAMERA:
                    setCropPhoto();
                    break;

                //裁剪本地相册
                case ALBUM:
                    Uri data1 = data.getData();
                    if (data1 != null) {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                            picPath = data1.toString();
                        } else {
                            picPath = data1.getPath();
                        }
                        // Uri --> Path
                        if (picPath.contains("content://")) {
//                            picPath = PathContentUtils.getFilePathFromContentUri(data1, getContentResolver());
                        }
                        mOutImage = new File(picPath);
                        setCropPhoto();
                    }
                    break;

                //裁剪完成
                case CUPREQUEST:
                    if (data == null) {
                        return;
                    }
                    Bundle extras = data.getExtras();
                    String s=null;
                        if (Build.MANUFACTURER.contains("Xiaomi")){
                            if (uritempFile !=null){
                                    s = uritempFile.getPath();
                             }else {
                                s = "";
                            }
                        }else {
                            if (extras != null){
                                Bitmap photo = extras.getParcelable("data");
//                                s = FileUtils.saveBitmap(photo);
                            }else {
                                s ="";
                            }
                        }

                      if(!TextUtils.isEmpty(s)){
                        Bitmap bitmap = BitmapFactory.decodeFile(s);
                        mCivMainHead.setImageBitmap(bitmap);
                        }
                 break;
                default:
                    break;
            }
        }

        private void setCropPhoto() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //如果是7.0剪裁图片 同理 需要把uri包装
                //通过FileProvider创建一个content类型的Uri
                Uri inputUri = FileProvider.getUriForFile(AvatarFunction.this,
                        "com.test.FileProvider", mOutImage);
                startPhotoZoom(inputUri);//设置输入类型
            } else {
                Uri inputUri = Uri.fromFile(mOutImage);
                startPhotoZoom(inputUri);
            }
        }

        //裁剪
        private void startPhotoZoom(Uri uri) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            //sdk>=24
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //去除默认的人脸识别，否则和剪裁匡重叠
                intent.putExtra("noFaceDetection", false);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            }
            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 宽高的比例
            //华为特殊处理 不然会显示圆
            if (android.os.Build.MODEL.contains("HUAWEI")) {
                intent.putExtra("aspectX", 9998);
                intent.putExtra("aspectY", 9999);
            } else {
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
            }
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
                //miui系统 特殊处理 return-data的方式只适用于小图。
                if (Build.MANUFACTURER.contains("Xiaomi")){
                    //裁剪后的图片Uri路径，uritempFile为Uri类变量
                     uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "tt.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
                }else {
                    intent.putExtra("return-data", true);
                }
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            startActivityForResult(intent, CUPREQUEST);
        }

        /**
         * 从底部弹出pop
         */
        public static PopupWindow showBotoomSmallWidthPopup(final Activity activity, int layoutId, View view) {
            View convertView = LayoutInflater.from(activity)
                    .inflate(layoutId, null);
            PopupWindow popupWindow = new PopupWindow(convertView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            popupWindow.setAnimationStyle(R.style.popup_bottom_style);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            ColorDrawable cd = new ColorDrawable(000000);
            popupWindow.setBackgroundDrawable(cd);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            setBackgroundAlpha(activity, 0.15f);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setBackgroundAlpha(activity, 1.0f);
                }
            });

            return popupWindow;
        }

        /**
         * 弹出popwindow 周围变暗
         *
         * @param activity
         * @param alpha
         */
        public static void setBackgroundAlpha(Activity activity, float alpha) {
            WindowManager.LayoutParams lp = activity.getWindow()
                    .getAttributes();
            lp.alpha = alpha;
            activity.getWindow()
                    .setAttributes(lp);
        }
    }
