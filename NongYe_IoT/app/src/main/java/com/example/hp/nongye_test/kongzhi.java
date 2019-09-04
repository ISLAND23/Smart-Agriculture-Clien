package com.example.hp.nongye_test;

import java.util.Timer;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

//*********************************************************************************************************************
public class kongzhi extends Activity
{
    private GestureDetector mGestureDetector;

    //**************************声明***********************************************************************************
    TabHost tabHost;
    Intent intent = new Intent();
    String[] IP_SPlit= new String[4];
    public AlertDialog Dialog;
    public AlertDialog Affirm_Dialog;
    public AlertDialog Save_Dialog;

    int tab_Index=0;

    //--------画面1------------------------

    public Button Relay4_Button,Relay5_Button,Relay6_Button,Relay7_Button,Relay8_Button
            ,Relay9_Button,Relay12_Button,Relay13_Button,Relay14_Button,Relay15_Button,Relay16_Button,Relay17_Button;

public TextView erhao_nuanfengji,penwuqi,buguangdeng,yihao_nuanfengji,yihao_dingchuang
        ,erhao_dingchuang,yihao_cechuang,yihao_celian,guangaifamen,tongfengji;
    //-------------------------------------
    TCP_UserData_Information TCP_Data=new TCP_UserData_Information();
    //****************************************************************************************************************

    //****************************************************************************************************************
    private void Init_UI()
    {


        Relay4_Button=(Button)this.findViewById(R.id.Relay4_Button);
        Relay5_Button=(Button)this.findViewById(R.id.Relay5_Button);
        Relay6_Button=(Button)this.findViewById(R.id.Relay6_Button);
        Relay7_Button=(Button)this.findViewById(R.id.Relay7_Button);
        Relay8_Button=(Button)this.findViewById(R.id.Relay8_Button);
        Relay9_Button=(Button)this.findViewById(R.id.Relay9_Button);
        Relay12_Button=(Button)this.findViewById(R.id.Relay12_Button);
        Relay13_Button=(Button)this.findViewById(R.id.Relay13_Button);
        Relay14_Button=(Button)this.findViewById(R.id.Relay14_Button);
        Relay15_Button=(Button)this.findViewById(R.id.Relay15_Button);
        Relay16_Button=(Button)this.findViewById(R.id.Relay16_Button);
        Relay17_Button=(Button)this.findViewById(R.id.Relay17_Button);

        erhao_nuanfengji=(TextView) findViewById(R.id.erhao_nuanfengji);
        penwuqi=(TextView) findViewById(R.id.penwuqi);
        buguangdeng=(TextView) findViewById(R.id.buguangdeng);
        yihao_nuanfengji=(TextView) findViewById(R.id.yihao_nuanfengji);
        yihao_dingchuang=(TextView) findViewById(R.id.yihao_dingchuang);
        erhao_dingchuang=(TextView) findViewById(R.id.erhao_dingchuang);
        yihao_cechuang=(TextView) findViewById(R.id.yihao_cechuang);
        yihao_celian=(TextView) findViewById(R.id.yihao_celian);
        guangaifamen=(TextView) findViewById(R.id.guangaifamen);
        tongfengji=(TextView) findViewById(R.id.tongfengji);



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

    //****************************************************************************************************************
    private void Init_AffirmDialog()
    {
        Affirm_Dialog=new AlertDialog.Builder(this)
                .setCancelable(false)   //屏幕外部区域点击无效
                .setTitle("提示：")
                .setMessage("确认要操作吗？")
                .setPositiveButton("确定",  new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        switch(TCP_UserData_Information.AffirmFlag)
                        {
                            case 1:  //第一个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[0]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=1;
                                    TCP_UserData_Information.Digital_Output[0]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=16;
                                    TCP_UserData_Information.Digital_Output[0]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 2:  //第二个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[1]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=2;
                                    TCP_UserData_Information.Digital_Output[1]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=17;
                                    TCP_UserData_Information.Digital_Output[1]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 3:  //第三个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[2]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=3;
                                    TCP_UserData_Information.Digital_Output[2]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=18;
                                    TCP_UserData_Information.Digital_Output[2]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 4:  //第四个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[3]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=4;
                                    TCP_UserData_Information.Digital_Output[3]=true;
                                }
                                else
                                {  	TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=19;
                                    TCP_UserData_Information.Digital_Output[3]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 5:  //第五个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[4]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=5;
                                    TCP_UserData_Information.Digital_Output[4]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=20;
                                    TCP_UserData_Information.Digital_Output[4]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 6: //第六个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[5]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=6;
                                    TCP_UserData_Information.Digital_Output[5]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=21;
                                    TCP_UserData_Information.Digital_Output[5]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 7:  //第七个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[6]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=7;
                                    TCP_UserData_Information.Digital_Output[6]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=22;
                                    TCP_UserData_Information.Digital_Output[6]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 8:  //第八个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[7]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=8;
                                    TCP_UserData_Information.Digital_Output[7]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=23;
                                    TCP_UserData_Information.Digital_Output[7]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 9:  //第九个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[8]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=9;
                                    TCP_UserData_Information.Digital_Output[8]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=24;
                                    TCP_UserData_Information.Digital_Output[8]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 10:  //第十个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[9]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=10;
                                    TCP_UserData_Information.Digital_Output[9]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=25;
                                    TCP_UserData_Information.Digital_Output[9]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 11:  //第十一个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[10]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=11;
                                    TCP_UserData_Information.Digital_Output[10]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=26;
                                    TCP_UserData_Information.Digital_Output[10]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 12:  //第十二个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[11]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=12;
                                    TCP_UserData_Information.Digital_Output[11]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=27;
                                    TCP_UserData_Information.Digital_Output[11]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 13:  //第十三个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[12]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=13;
                                    TCP_UserData_Information.Digital_Output[12]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=28;
                                    TCP_UserData_Information.Digital_Output[12]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 14:  //第十四个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[13]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=14;
                                    TCP_UserData_Information.Digital_Output[13]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=29;
                                    TCP_UserData_Information.Digital_Output[13]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 15:  //第十五个继电器
                                //*************************************************************************//
                                if(TCP_UserData_Information.Digital_Output[14]==false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=15;
                                    TCP_UserData_Information.Digital_Output[14]=true;
                                }
                                else
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=30;
                                    TCP_UserData_Information.Digital_Output[14]=false;
                                }
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            default:
                                TCP_UserData_Information.AffirmFlag=0;          //执行完毕，状态信息归零
                                break;
                        }
                        TCP_UserData_Information.Write_Array_Index++;
                        if(TCP_UserData_Information.Write_Array_Index>19){TCP_UserData_Information.Write_Array_Index=0;}  //消息队列送够19，则送满，这时候返回第一个 也就是说5秒钟之内最多能接收50条指令
                    }})
                .setNegativeButton("取消", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TCP_UserData_Information.AffirmFlag=0;

                    }}).create();	  //取消不需要任何代码
    }
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
        setContentView(R.layout.telecontrol_linearlayout);
        Intent intent=getIntent();
        Init_UI();
       TCP_UserData_Information.TCP_Contrl.start();          //启动处理TCP的线程
        Init_WriteCommand_Timer();                            //初始化06指令发送消息队列
        Init_Dialog();
        Init_AffirmDialog();
        Init_UI();
        mGestureDetector = new GestureDetector(this, new MyGestureListener(this));
        if(TCP_UserData_Information.Device_State==false)      //判断设备网络连接状态
        {
//            Device_State_Text.setText("网络未开或服务器关");
        }
        else
        {
//            Device_State_Text.setText("软件初始化完毕");
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }
//****************************************************************************************************************

    @SuppressLint("HandlerLeak")
    public void onResume()
    { 	    super.onResume();  //不加这个，则程序错误退出

        //*************************************************************************//
        Relay4_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=4;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }

        });
        //*************************************************************************//
        Relay5_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=5;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }

        });
        //*************************************************************************//
        Relay6_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=6;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }

        });
        //*************************************************************************//
        Relay7_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=7;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }

        });
        //*************************************************************************//
        Relay8_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=8;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//
        Relay9_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=9;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//
        Relay12_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=12;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//
        Relay13_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=13;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//
        Relay14_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=14;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//
        Relay15_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=15;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//

        //*************************************************************************//
        Relay16_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=15;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//

        //*************************************************************************//
        Relay17_Button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if(TCP_UserData_Information.Device_State==true)
                {
                    TCP_UserData_Information.AffirmFlag=15;   //指示：现在点击的是第一个按钮
                    Affirm_Dialog.show();
                }
                else
                {
                    TCP_UserData_Information.AffirmFlag=0;   //无效的情况下，不做任何操作
                    Dialog.show();
                }
            }
        });
        //*************************************************************************//

        TCP_UserData_Information.TCP_Master=new Handler()
        {
            public void handleMessage(Message msg)
            {
                //如果该消息是本程序所发送的
                switch(msg.what)
                {

                    case 0x0004:  //继电器2关闭的消息句柄
                     //   Relay4_State.setImageResource(R.drawable.open);
                        Resources resources_1 = getApplicationContext().getResources();
                        Drawable btnDrawable_1 = resources_1.getDrawable(R.drawable.uu);
                        Relay4_Button.setBackgroundDrawable(btnDrawable_1);

                        if(TCP_UserData_Information.first_Init_ButtonName[3]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[3]=true;
                            TCP_UserData_Information.Digital_Output[3]=true;
                        }
                        break;
                    case 0x1004:  //继电器2关闭的消息句柄
                      //  Relay4_State.setImageResource(R.drawable.close);
                        Resources resources_2 = getApplicationContext().getResources();
                        Drawable btnDrawable_2 = resources_2.getDrawable(R.drawable.off);
                        Relay4_Button.setBackgroundDrawable(btnDrawable_2);
                        if(TCP_UserData_Information.first_Init_ButtonName[3]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[3]=true;
                            TCP_UserData_Information.Digital_Output[3]=false;
                        }
                        break;
                    case 0x0005:  //继电器2关闭的消息句柄
                      //  Relay5_State.setImageResource(R.drawable.open);
                        Resources resources_3 = getApplicationContext().getResources();
                        Drawable btnDrawable_3 = resources_3.getDrawable(R.drawable.uu);
                        Relay5_Button.setBackgroundDrawable(btnDrawable_3);
                        if(TCP_UserData_Information.first_Init_ButtonName[4]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[4]=true;
                            TCP_UserData_Information.Digital_Output[4]=true;
                        }
                        break;
                    case 0x1005:  //继电器2关闭的消息句柄
                      //  Relay5_State.setImageResource(R.drawable.close);
                        Resources resources_4 = getApplicationContext().getResources();
                        Drawable btnDrawable_4 = resources_4.getDrawable(R.drawable.off);
                        Relay5_Button.setBackgroundDrawable(btnDrawable_4);
                        if(TCP_UserData_Information.first_Init_ButtonName[4]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[4]=true;
                            TCP_UserData_Information.Digital_Output[4]=false;
                        }
                        break;
                    case 0x0006:  //继电器2关闭的消息句柄
                       // Relay6_State.setImageResource(R.drawable.open);
                        Resources resources_5 = getApplicationContext().getResources();
                        Drawable btnDrawable_5 = resources_5.getDrawable(R.drawable.uu);
                        Relay6_Button.setBackgroundDrawable(btnDrawable_5);
                        if(TCP_UserData_Information.first_Init_ButtonName[5]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[5]=true;
                            TCP_UserData_Information.Digital_Output[5]=true;
                        }
                        break;
                    case 0x1006:  //继电器2关闭的消息句柄
                      //  Relay6_State.setImageResource(R.drawable.close);
                        Resources resources_6 = getApplicationContext().getResources();
                        Drawable btnDrawable_6= resources_6.getDrawable(R.drawable.off);
                        Relay6_Button.setBackgroundDrawable(btnDrawable_6);
                        if(TCP_UserData_Information.first_Init_ButtonName[5]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[5]=true;
                            TCP_UserData_Information.Digital_Output[5]=false;
                        }
                        break;
                    case 0x0007:  //继电器2关闭的消息句柄
                      //  Relay7_State.setImageResource(R.drawable.open);
                        Resources resources_7 = getApplicationContext().getResources();
                        Drawable btnDrawable_7 = resources_7.getDrawable(R.drawable.uu);
                        Relay7_Button.setBackgroundDrawable(btnDrawable_7);
                        if(TCP_UserData_Information.first_Init_ButtonName[6]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[6]=true;
                            TCP_UserData_Information.Digital_Output[6]=true;
                        }
                        break;
                    case 0x1007:  //继电器2关闭的消息句柄
                      //  Relay7_State.setImageResource(R.drawable.close);
                        Resources resources_8 = getApplicationContext().getResources();
                        Drawable btnDrawable_8 = resources_8.getDrawable(R.drawable.off);
                        Relay7_Button.setBackgroundDrawable(btnDrawable_8);
                        if(TCP_UserData_Information.first_Init_ButtonName[6]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[6]=true;
                            TCP_UserData_Information.Digital_Output[6]=false;
                        }
                        break;
                    case 0x0008:  //继电器2关闭的消息句柄
                      //  Relay8_State.setImageResource(R.drawable.open);
                        Resources resources_9 = getApplicationContext().getResources();
                        Drawable btnDrawable_9 = resources_9.getDrawable(R.drawable.uu);
                        Relay8_Button.setBackgroundDrawable(btnDrawable_9);
                        if(TCP_UserData_Information.first_Init_ButtonName[7]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[7]=true;
                            TCP_UserData_Information.Digital_Output[7]=true;
                        }
                        break;
                    case 0x1008:  //继电器2关闭的消息句柄
                      //  Relay8_State.setImageResource(R.drawable.close);
                        Resources resources_10 = getApplicationContext().getResources();
                        Drawable btnDrawable_10 = resources_10.getDrawable(R.drawable.off);
                        Relay8_Button.setBackgroundDrawable(btnDrawable_10);
                        if(TCP_UserData_Information.first_Init_ButtonName[7]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[7]=true;
                            TCP_UserData_Information.Digital_Output[7]=false;
                        }
                        break;
                    case 0x0009:  //继电器2关闭的消息句柄
                      //  Relay9_State.setImageResource(R.drawable.open);
                        Resources resources_11 = getApplicationContext().getResources();
                        Drawable btnDrawable_11 = resources_11.getDrawable(R.drawable.uu);
                        Relay9_Button.setBackgroundDrawable(btnDrawable_11);
                        if(TCP_UserData_Information.first_Init_ButtonName[8]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[8]=true;
                            TCP_UserData_Information.Digital_Output[8]=true;
                        }
                        break;
                    case 0x1009:  //继电器2关闭的消息句柄
                     //   Relay9_State.setImageResource(R.drawable.close);
                        Resources resources_12 = getApplicationContext().getResources();
                        Drawable btnDrawable_12 = resources_12.getDrawable(R.drawable.off);
                        Relay9_Button.setBackgroundDrawable(btnDrawable_12);
                        if(TCP_UserData_Information.first_Init_ButtonName[8]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[8]=true;
                            TCP_UserData_Information.Digital_Output[8]=false;
                        }
                        break;
                    case 0x000a:  //继电器2关闭的消息句柄
                     //   Relay10_State.setImageResource(R.drawable.open);
//                        Resources resources_13 = getApplicationContext().getResources();
//                        Drawable btnDrawable_13 = resources_13.getDrawable(R.drawable.uu);
//                        Relay4_Button.setBackgroundDrawable(btnDrawable_13);
                        if(TCP_UserData_Information.first_Init_ButtonName[9]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[9]=true;
                            TCP_UserData_Information.Digital_Output[9]=true;
                        }
                        break;
                    case 0x100a:  //继电器2关闭的消息句柄
                      //  Relay10_State.setImageResource(R.drawable.close);
//                        Resources resources_14 = getApplicationContext().getResources();
//                        Drawable btnDrawable_14 = resources_14.getDrawable(R.drawable.off);
//                        Relay10_Button.setBackgroundDrawable(btnDrawable_14);
                        if(TCP_UserData_Information.first_Init_ButtonName[9]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[9]=true;
                            TCP_UserData_Information.Digital_Output[9]=false;
                        }
                        break;
                    case 0x000c:  //继电器2关闭的消息句柄
                       // Relay12_State.setImageResource(R.drawable.open);
                        Resources resources_15 = getApplicationContext().getResources();
                        Drawable btnDrawable_15 = resources_15.getDrawable(R.drawable.uu);
                        Relay12_Button.setBackgroundDrawable(btnDrawable_15);
                        if(TCP_UserData_Information.first_Init_ButtonName[11]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[11]=true;
                            TCP_UserData_Information.Digital_Output[11]=true;
                        }
                        break;
                    case 0x100c:  //继电器2关闭的消息句柄
                      //  Relay12_State.setImageResource(R.drawable.close);
                        Resources resources_16 = getApplicationContext().getResources();
                        Drawable btnDrawable_16 = resources_16.getDrawable(R.drawable.off);
                        Relay12_Button.setBackgroundDrawable(btnDrawable_16);
                        if(TCP_UserData_Information.first_Init_ButtonName[11]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[11]=true;
                            TCP_UserData_Information.Digital_Output[11]=false;
                        }
                        break;
                    case 0x000d:  //继电器2关闭的消息句柄
                      //  Relay13_State.setImageResource(R.drawable.open);
                        Resources resources_17 = getApplicationContext().getResources();
                        Drawable btnDrawable_17 = resources_17.getDrawable(R.drawable.uu);
                        Relay13_Button.setBackgroundDrawable(btnDrawable_17);
                        if(TCP_UserData_Information.first_Init_ButtonName[12]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[12]=true;
                            TCP_UserData_Information.Digital_Output[12]=true;
                        }
                        break;
                    case 0x100d:  //继电器2关闭的消息句柄
                     //   Relay13_State.setImageResource(R.drawable.close);
                        Resources resources_18 = getApplicationContext().getResources();
                        Drawable btnDrawable_18 = resources_18.getDrawable(R.drawable.off);
                        Relay13_Button.setBackgroundDrawable(btnDrawable_18);
                        if(TCP_UserData_Information.first_Init_ButtonName[12]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[12]=true;
                            TCP_UserData_Information.Digital_Output[12]=false;
                        }
                        break;
                    case 0x000e:  //继电器2关闭的消息句柄
                      //  Relay14_State.setImageResource(R.drawable.open);
                        Resources resources_19 = getApplicationContext().getResources();
                        Drawable btnDrawable_19 = resources_19.getDrawable(R.drawable.uu);
                        Relay14_Button.setBackgroundDrawable(btnDrawable_19);
                        if(TCP_UserData_Information.first_Init_ButtonName[13]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[13]=true;
                            TCP_UserData_Information.Digital_Output[13]=true;
                        }
                        break;
                    case 0x100e:  //继电器2关闭的消息句柄
                      //  Relay14_State.setImageResource(R.drawable.close);
                        Resources resources_20 = getApplicationContext().getResources();
                        Drawable btnDrawable_20 = resources_20.getDrawable(R.drawable.off);
                        Relay14_Button.setBackgroundDrawable(btnDrawable_20);
                        if(TCP_UserData_Information.first_Init_ButtonName[13]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[13]=true;
                            TCP_UserData_Information.Digital_Output[13]=false;
                        }
                        break;
                    case 0x000f:  //继电器2关闭的消息句柄
                      //  Relay15_State.setImageResource(R.drawable.open);
                        Resources resources_21 = getApplicationContext().getResources();
                        Drawable btnDrawable_21 = resources_21.getDrawable(R.drawable.uu);
                        Relay15_Button.setBackgroundDrawable(btnDrawable_21);
                        if(TCP_UserData_Information.first_Init_ButtonName[14]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[14]=true;
                            TCP_UserData_Information.Digital_Output[14]=true;
                        }
                        break;
                    case 0x100f:  //继电器2关闭的消息句柄
                     //   Relay15_State.setImageResource(R.drawable.close);
                        Resources resources_22 = getApplicationContext().getResources();
                        Drawable btnDrawable_22 = resources_22.getDrawable(R.drawable.off);
                        Relay15_Button.setBackgroundDrawable(btnDrawable_22);
                        if(TCP_UserData_Information.first_Init_ButtonName[14]==false)
                        {
                            TCP_UserData_Information.first_Init_ButtonName[14]=true;
                            TCP_UserData_Information.Digital_Output[14]=false;
                        }
                        break;

                    case 0x0010:
                        if(msg.arg1>100){msg.arg1=100;}
//自动关闭

                        erhao_nuanfengji.setText(Integer.toString(msg.arg1)+"℃");
                        yihao_nuanfengji.setText(Integer.toString(msg.arg1)+"℃");

                        int wendu=msg.arg1;

                        if (wendu>25 && TCP_UserData_Information.Digital_Output[12]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=28;
                            TCP_UserData_Information.Digital_Output[12]=false;
                        }

                        if (wendu>26 && TCP_UserData_Information.Digital_Output[11]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=27;
                            TCP_UserData_Information.Digital_Output[11]=false;
                        }

                        if(msg.arg2>100){msg.arg2=100;}
                        penwuqi.setText(Integer.toString(msg.arg2)+"%");

                        int shidu=msg.arg2;

                        if(shidu>100 && TCP_UserData_Information.Digital_Output[13]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=29;
                            TCP_UserData_Information.Digital_Output[13]=false;
                        }
                        break;
                    case 0x0011:
                        buguangdeng.setText(Integer.toString(msg.arg1)+"Lux");
                        //自动关闭
                        int  guangzhao=msg.arg1;
                        if (guangzhao>100 && TCP_UserData_Information.Digital_Output[8]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=24;
                            TCP_UserData_Information.Digital_Output[8]=false;
                        }
                        yihao_celian.setText(Integer.toString(msg.arg1)+"Lux");
                        if (guangzhao>151 && TCP_UserData_Information.Digital_Output[3]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=19;
                            TCP_UserData_Information.Digital_Output[3]=false;
                        }

                        yihao_dingchuang.setText(Integer.toString(msg.arg2)+"ppm");
                        int co2=msg.arg2;
                        if (co2>800 &&  TCP_UserData_Information.Digital_Output[4]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=20;
                            TCP_UserData_Information.Digital_Output[4]=false;
                        }

                        erhao_dingchuang.setText(Integer.toString(msg.arg2)+"ppm");
                        if (co2>801 &&  TCP_UserData_Information.Digital_Output[5]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=21;
                            TCP_UserData_Information.Digital_Output[5]=false;
                        }
                        yihao_cechuang.setText(Integer.toString(msg.arg2)+"ppm");
                        if (co2>802 &&   TCP_UserData_Information.Digital_Output[6]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=22;
                            TCP_UserData_Information.Digital_Output[6]=false;
                        }
                        tongfengji.setText(Integer.toString(msg.arg2)+"ppm");
                        if (co2>803 && TCP_UserData_Information.Digital_Output[7]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=23;
                            TCP_UserData_Information.Digital_Output[7]=false;
                        }
                        break;
                    case 0x0012:
                        guangaifamen.setText(Integer.toString(msg.arg2)+"%");
                        int   turang_shidu=msg.arg2;
                        if (turang_shidu>40 && TCP_UserData_Information.Digital_Output[14]==true){
                            TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index]=30;
                            TCP_UserData_Information.Digital_Output[14]=false;
                        }
                        break;

                    case 0x0013:
                        break;
                    case 0x0014:
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

