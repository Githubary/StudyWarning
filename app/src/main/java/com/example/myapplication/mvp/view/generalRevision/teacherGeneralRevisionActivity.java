package com.example.myapplication.mvp.view.generalRevision;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.teacherClassFunction.createClassQuestionActivity;
import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;

public class teacherGeneralRevisionActivity extends BaseActivity implements View.OnClickListener {

    private TextView publish_revision;
    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;
    /**可以输入文字的dialog*/
    private MyAlertInputDialog myAlertInputDialog;


    TextView feedback;

    @Override
    protected int initLayout() {
        return R.layout.activity_teacher_general_revision;
    }

    @Override
    protected void initView() {
        publish_revision=findViewById(R.id.publish_revision);
        feedback=findViewById(R.id.feedback);
//        feedback_linearLayout=findViewById(R.id.feedback_linearLayout);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        publish_revision.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publish_revision:
                confirmHint(this,"确定生成模拟卷吗");
                break;
            case R.id.feedback:
                changeActivity(generalRevisionFeedbackActivity.class);
                break;
        }

    }




    /**
     *  确认提示的方法
     * @param context 当前场景，背景，上下文
     */
    void confirmHint(final Context context,String msg){
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg(msg);
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {

                feedback.setAlpha(1.0f);

//                    feedback = new TextView(context);
//                    feedback.setText("反馈统计");
//                    feedback.setTextColor(Color.parseColor("#FF6A6A"));
//                    feedback.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//                    feedback.setLayoutParams(params);
//                    feedback.setGravity(Gravity.LEFT);
//                    feedback_linearLayout.addView(feedback);
//                    setContentView(feedback_linearLayout);
        }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    /**
     * 弹出框
     * @param context
     * @param msg
     */
    void myAlertInputDialog(Context context, String msg){
        myAlertInputDialog = new MyAlertInputDialog(context).builder()
                .setTitle(msg)
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok();
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    public void ok(){
        changeActivity(createClassQuestionActivity.class);
    }

    public void cancel(){

    }

}
