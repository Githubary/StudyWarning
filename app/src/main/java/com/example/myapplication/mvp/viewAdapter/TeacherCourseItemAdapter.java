package com.example.myapplication.mvp.viewAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.myapplication.R;
import com.example.myapplication.entity.Course;
import com.example.myapplication.utils.Photo.ImageLoader;
import com.example.myapplication.utils.Photo.OkhttpUpPhoto;
import com.hb.dialog.dialog.ConfirmDialog;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import lombok.SneakyThrows;

public class TeacherCourseItemAdapter extends ArrayAdapter<Course> {

    private View view;

    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;

    /**courseItem布局的地方*/
    private int itemLayout;

    /**传入的数据集合*/
    private List<Course> list;

    private DeleteCourseCallBack deleteCourseCallBack;

    public ImageLoader imageLoader;

    String url ="http://47.92.142.206:8080/";

    ViewHolder viewHolder;

    public void setDeleteCourseCallBack(DeleteCourseCallBack deleteCourseCallBack){
        this.deleteCourseCallBack = deleteCourseCallBack;
    }


    public TeacherCourseItemAdapter(Context context, int itemLayout, List<Course> list){
        super(context,itemLayout,list);
        this.itemLayout=itemLayout;
        this.list=list;
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    @SneakyThrows
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        Course course = getItem(position);
        System.out.println(course.toString());

        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.courseCover=view.findViewById(R.id.courseCover);
            viewHolder.courseName=view.findViewById(R.id.courseName);
            viewHolder.courseNumber=view.findViewById(R.id.courseNumber);
            viewHolder.deleteTeacherCourse=view.findViewById(R.id.deleteTeacherCourse);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.courseName.setText(course.getCourseName());
        viewHolder.courseNumber.setText("No."+course.getCourseNumber());
        String ImagePath=url+course.getCourseCover();
        RequestOptions roundOptions = new RequestOptions()
                .transform(new RoundedCorners(20));
        Glide.with(getContext())
                .load(ImagePath)
                .placeholder(R.drawable.defaultcoursebackground)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(roundOptions)
                .into(viewHolder.courseCover);


        /**item内部删除按钮，将整个item删除*/
        viewHolder.deleteTeacherCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(getContext(),position);
            }
        });
        return view;
    }

    private void confirmDelete(final Context context, final int position) {
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("确认解散该门课程吗？");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                System.out.println("即将被删除的course："+list.get(position).toString());
                String deleteCourseNumber=list.get(position).getCourseNumber();
                list.remove(position);
                deleteCourseCallBack.DeleteCourse(deleteCourseNumber);
                /**刷新这个适配器*/
                TeacherCourseItemAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void cancel() {

            }
        });
        confirmDialog.show();
    }

    public interface DeleteCourseCallBack{
        void DeleteCourse(String deleteCourseNumber);
    }


    /**内部类，对实例进行缓存*/
    public static final class ViewHolder{
        ImageView courseCover;
        TextView courseName;
        TextView courseNumber;
        TextView deleteTeacherCourse;

    }

}
