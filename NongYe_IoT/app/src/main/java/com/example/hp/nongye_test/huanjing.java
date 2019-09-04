package com.example.hp.nongye_test;

import java.util.Timer;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Message;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;




import android.widget.LinearLayout;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Calendar;


//********************************************************************************************************************

//*********************************************************************************************************************
public class huanjing extends Activity
{
    private GestureDetector mGestureDetector;

    //**************************声明***********************************************************************************
    TabHost tabHost;
    Intent intent = new Intent();
    String[] IP_SPlit= new String[4];
    public AlertDialog Dialog;
    public AlertDialog Affirm_Dialog;
    public AlertDialog Save_Dialog;

    EditText ED_CityName;
    TextView TX_temperature,TX_city;
    Button BTN_Get;
    ImageView tianqi;
    String 	City_Name ;
    String S_Result;
    LinearLayout back;

    int tab_Index=0;



    public TextView Base_01,Base_02,Base_03,Base_04,Base_05,Base_06,Base_07,Base_08,Base_09;

    //-------------------------------------
    TCP_UserData_Information TCP_Data=new TCP_UserData_Information();
    //****************************************************************************************************************

    //****************************************************************************************************************
    private void Init_UI()
    {


        Base_01=(TextView)this.findViewById(R.id.Base_01);
        Base_02=(TextView)this.findViewById(R.id.Base_02);
        Base_03=(TextView)this.findViewById(R.id.Base_03);
        Base_04=(TextView)this.findViewById(R.id.Base_04);
        Base_05=(TextView)this.findViewById(R.id.Base_05);
        Base_06=(TextView)this.findViewById(R.id.Base_06);
        Base_07=(TextView)this.findViewById(R.id.Base_07);
        Base_08=(TextView)this.findViewById(R.id.Base_08);
        Base_09=(TextView)this.findViewById(R.id.Base_09);

        ED_CityName =findViewById(R.id.et_cityname);
        TX_temperature = findViewById(R.id.tx_temperature);
        BTN_Get =findViewById(R.id.btn_get);
        TX_city=findViewById(R.id.tx_city);
        tianqi=findViewById(R.id.IM_temperature);
        back=findViewById(R.id.Liner_back);

    }
    //****************************************************************************************************************
    private void Init_Dialog()
    {
        Dialog=new AlertDialog.Builder(this)
                .setCancelable(false)   //屏幕外部区域点击无效
                .setTitle("错误：")
                .setMessage("网络断开或服务器未开启！请检查网络！")
                .setPositiveButton("确定", null).create();
    }

//    //****************************************************************************************************************

    //****************************************************************************************************************

    //****************************************************************************************************************
    private void Init_WriteCommand_Timer()
    {
        Timer time= new Timer();
        time.schedule(new WriteCommand_Task(), 1000,2000);  //开机1秒钟之后，每隔2秒钟检索一次发送队列，并操作发送队列发送写指令
    }
    //****************************************************************************************************************
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // 如果是返回键,直接返回到桌面
        if(keyCode == KeyEvent.KEYCODE_BACK )
        {
            if(TCP_Data.Exit_State==false)
            {
//                Toast.makeText(MainActivity.this.getApplicationContext(), "再按一次退出程序", 0).show();
                TCP_Data.Exit_State=true;
                TCP_Data.Exit_time=System.currentTimeMillis();
                return false;
            }
            else
            {
                if(System.currentTimeMillis()-TCP_Data.Exit_time>3000)
                {
                    TCP_Data.Exit_State=false;
                    TCP_Data.Exit_time=0;
                    return false;
                }
                else
                {
                    System.exit(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    //****************************************************************************************************************
    @Override
    public void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_show);
        Intent intent=getIntent();
        Init_UI();
        Init_WriteCommand_Timer();                            //初始化06指令发送消息队列
        Init_Dialog();
        Init_UI();
        mGestureDetector = new GestureDetector(this, new MyGestureListener(this));

        BTN_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ED_CityName.length()!=0){
                    City_Name=ED_CityName.getText().toString();
                    Thread_HttpGet thread_httpget = new Thread_HttpGet();
                    thread_httpget.start();}else{
                    Toast.makeText(huanjing.this,"城市不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public class Thread_HttpGet extends Thread{
        public void run(){
            String Web_URL="http://apicloud.mob.com/v1/weather/query";
            String Appkey="294297de549b6";
            String Http_URL=Web_URL+"?"+"key="+Appkey+"&"+"city="+City_Name;

            HttpGet httpRequest=new HttpGet(Http_URL);
            try{
                HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
                if(httpResponse.getStatusLine().getStatusCode()==200){
                    HttpEntity httpEntity=httpResponse.getEntity();
                    String result=EntityUtils.toString(httpEntity);
                    result.replaceAll("\r","");
                    Message msg=new Message();
                    msg.what=0;
                    Bundle bundle=new Bundle();
                    bundle.putString("web_get",result);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }else {
                    Message msg =new Message();
                    msg.what=1;
                    httpRequest.abort();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    Handler handler = new Handler(){

        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    S_Result = msg.getData().getString("web_get");

                    Gson gson=new Gson();
                    Weather_get weather_get=gson.fromJson(S_Result,Weather_get.class);
                    String city_get=weather_get.getResult().get(0).getCity();
                    String temperature_get=weather_get.getResult().get(0).getTemperature();
                    // String date_get=weather_get.getResult().get(0).getDate();
                    String weather=weather_get.getResult().get(0).getWeather();
                    //  String week_get=weather_get.getResult().get(0).getWeek();
                    String air_get=weather_get.getResult().get(0).getAirCondition();
                    String wind_get=weather_get.getResult().get(0).getWind();
                    Calendar c=Calendar.getInstance();
                    String time_get=Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c.get(Calendar.MINUTE));

                    TX_temperature.setText(city_get+"           "+time_get+"更新"+  "                  气温："+temperature_get);
                    TX_city.setText( weather+"               空气质量："+air_get+"                "+wind_get);

                    if(weather.equals("晴")){
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.qing));
                      // back.setBackground(getResources().getDrawable(R.mipmap.back_gaowen));
                        Resources resources_01 = getApplicationContext().getResources();
                         Drawable btnDrawable_01= resources_01.getDrawable(R.mipmap.sun_2);
                        back.setBackgroundDrawable(btnDrawable_01);

                    }else if(weather.equals("阴")){tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.yin));
                       // back.setBackground(getResources().getDrawable(R.mipmap.back_yin));
                        Resources resources_02 = getApplicationContext().getResources();
                        Drawable btnDrawable_02= resources_02.getDrawable(R.mipmap.yin);
                        back.setBackgroundDrawable(btnDrawable_02);
                    }
                    else if(weather.equals("多云")){
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.cloud));
                    }
                    else if(weather.equals("雷阵雨")){
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.leiyu));
                    }
                    else if(weather.equals("小雨")|weather.equals("阵雨")|weather.equals("中雨")|weather.equals("大雨"))
                    {
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.yu));
                    }
                    else if(weather.equals("大风")){
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.sun));
                    }
                    else{
                        tianqi.setImageDrawable(getResources().getDrawable(R.mipmap.tempture));
                    }
                    break;
                case 1:
                    Toast.makeText(huanjing.this,"请输入正确的城市名",Toast.LENGTH_SHORT).show();

                default:
                    break;
            }
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }
//****************************************************************************************************************

    @SuppressLint("HandlerLeak")
    public void onResume()
    { 	    super.onResume();  //不加这个，则程序错误退出
        //---------------------------------------------------------------------------------------

        //*************************************************************************//
        TCP_UserData_Information.TCP_Master=new Handler()
        {
            public void handleMessage(Message msg)
            {
                //如果该消息是本程序所发送的
                switch(msg.what)
                {


                    case 0x0010:
                        if(msg.arg1>100){msg.arg1=100;}
                        Base_01.setText(Integer.toString(msg.arg1)+"℃");     //空气环境：温度
                        if(msg.arg2>100){msg.arg2=100;}
                        Base_02.setText(Integer.toString(msg.arg2)+"%");      //空气环境：湿度
                        break;
                    case 0x0011:
                        Base_03.setText(Integer.toString(msg.arg1)+"Lux");    //空气环境：光照
                        Base_04.setText(Integer.toString(msg.arg2)+"ppm");    //空气环境：二氧化碳
                        break;
                    case 0x0012:
                        Base_05.setText(Integer.toString(msg.arg1)+"℃"); //土壤环境：温度
                        Base_06.setText(Integer.toString(msg.arg2)+"%");      //土壤环境：湿度
                        break;
                    case 0x0013:
                        Base_07.setText(Double.toString((double)msg.arg1/10)+"mS/cm");  //土壤环境：盐溶解度
                        Base_08.setText(Double.toString((double)msg.arg2/10));          //土壤环境：PH值
                        break;
                    case 0x0014:
                        Base_09.setText(Integer.toString(msg.arg1)+"cm");     //灌溉环境：水箱水位
                        break;

                    case 0x0101:
                        tab_Index++;     if(tab_Index>2){tab_Index=2;}
                        tabHost.setCurrentTab(tab_Index);
                        break;
                    case 0x1010:
                        tab_Index--;     if(tab_Index==-1){tab_Index=0;}
                        tabHost.setCurrentTab(tab_Index);
                        break;
                }
            }
        };
        //*************************************************************************//
    }
    //****************************************************************************************************************

}

