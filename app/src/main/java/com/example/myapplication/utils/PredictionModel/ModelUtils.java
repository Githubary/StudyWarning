package com.example.myapplication.utils.PredictionModel;

import android.content.res.AssetManager;
import android.util.Log;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class ModelUtils {

    //模型中输入变量的名称
    private static final String inputName = "";
    //模型中输出变量的名称
    private static final String outputName="";
    //输入数据
    private static double[] doubles ;

    TensorFlowInferenceInterface tensorFlowInferenceInterface;

    TensorFlowInferenceInterface inferenceInterface;

    static {
        //加载libtensorflow_inference.so库文件
        System.loadLibrary("libtensorflow_inference");
        Log.e("tensorflow","libtensorflow_inference.so库加载成功");
    }
    public ModelUtils(AssetManager assetManager, String modePath) {
        //初始化TensorFlowInferenceInterface对象
        inferenceInterface = new TensorFlowInferenceInterface(assetManager,modePath);
        Log.e("tf","TensoFlow模型文件加载成功");
    }

    public double predict(double[] doubles){
        double predict=0;

//        inferenceInterface.feed(inputName, inputdata, 1, IMAGE_SIZE, IMAGE_SIZE, 3);
//        //运行模型,run的参数必须是String[]类型
//        String[] outputNames = new String[]{outputName,probabilityName,outlayerName};
//        inferenceInterface.run(outputNames);
//        //获取结果
//        int[] labels = new int[1];
//        inferenceInterface.fetch(outputName,labels);
//        int label = labels[0];
//        float[] prob = new float[11];
//        inferenceInterface.fetch(probabilityName, prob);
//
//        for(int i = 0; i <11; ++i)
//        {
//            Log.d("matrix",prob[i] + "");
//        }
//        DecimalFormat df = new DecimalFormat("0.000000");
//        float label_prob = prob[label];
//        //返回值
//        list.add(Integer.toString(label));
//        list.add(df.format(label_prob));



        return predict;
    }


}
