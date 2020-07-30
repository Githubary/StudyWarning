package com.example.myapplication.utils.Photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageLoader {

    FileCache fileCache;

    public ImageLoader(Context context){
        fileCache=new FileCache(context);
    }

    //加载图片
    public static Bitmap getBitmap(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            InputStream is = conn.getInputStream();//获得图片的数据流
            if(is!=null){
                System.out.println(is);
            }else{
                System.out.println("数据流是空的");
            }
            byte[] b = StreamUtils.readInputStream(is);
            bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }



    //解码图像用来减少内存消耗
    private Bitmap decodeFile(File f){
        try {
            //解码图像大小
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //找到正确的刻度值，它应该是2的幂。
            final int REQUIRED_SIZE=70;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }



}