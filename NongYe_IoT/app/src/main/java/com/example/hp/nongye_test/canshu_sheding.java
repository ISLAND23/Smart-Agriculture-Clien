package com.example.hp.nongye_test;


import java.util.Timer;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
//********************************************************************************************************************
class MyGestureListener extends SimpleOnGestureListener {

    @SuppressWarnings("unused")
    private Context mContext;

    MyGestureListener(Context context) {

        mContext = context;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        if(e1.getX()-e2.getX() > 20 && Math.abs(velocityX) > 10){
            Message msg=new Message();
            msg.what=0x0101;
            TCP_UserData_Information.TCP_Master.sendMessage(msg);


        }else if(e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 10){
            Message msg=new Message();
            msg.what=0x1010;
            TCP_UserData_Information.TCP_Master.sendMessage(msg);
        }
        return false;
    }
}
//*********************************************************************************************************************
public class canshu_sheding extends Activity {
    private GestureDetector mGestureDetector;

    //**************************声明***********************************************************************************
    TabHost tabHost;
    Intent intent = new Intent();
    String[] IP_SPlit = new String[4];
    public AlertDialog Dialog;
    public AlertDialog Affirm_Dialog;
    public AlertDialog Save_Dialog;

    int tab_Index = 0;

    public EditText IPAddress1, IPAddress2, IPAddress3, IPAddress4, OutofTime, Send_Time, Port_Number;
    public Button Save_Config, Canel_Config;
    public TextView Device_State_Text;


    //-------------------------------------
    TCP_UserData_Information TCP_Data = new TCP_UserData_Information();

    //****************************************************************************************************************
    private void Init_Config() {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);   //如果不存在，系统会自己创建
        //-----------------------------------------------------------------------------------------------------------
        TCP_UserData_Information.IpAddress = sharedPreferences.getString("IP_Address", "");   //读当前IP地址
        if (TCP_UserData_Information.IpAddress.equals(""))           //如果读出来的是空，则恢复默认值
        {
            SharedPreferences mySharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("IP_Address", "192.168.10.10");
            editor.commit();
            TCP_UserData_Information.IpAddress = sharedPreferences.getString("IP_Address", "");   //读当前IP地址
        }
        //-----------------------------------------------------------------------------------------------------------
        String Port_Name = sharedPreferences.getString("Port_Number", "");   //读当前IP地址

        if (Port_Name.equals(""))                                   //如果读出来的是空，则恢复默认值
        {
            SharedPreferences mySharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("Port_Number", "60000");
            editor.commit();
        }
        TCP_UserData_Information.Port_Number = Integer.parseInt(sharedPreferences.getString("Port_Number", ""));   //读当前IP地址
        //-----------------------------------------------------------------------------------------------------------
        String TCP_Keep_Alive_Name = sharedPreferences.getString("TCP_Keep_Alive", "");   //读当前IP地址

        if (TCP_Keep_Alive_Name.equals(""))                                   //如果读出来的是空，则恢复默认值
        {
            SharedPreferences mySharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("TCP_Keep_Alive", "30000");
            editor.commit();
        }
        TCP_UserData_Information.TCP_Keep_Alive = Integer.parseInt(sharedPreferences.getString("TCP_Keep_Alive", ""));   //读当前IP地址
        //-----------------------------------------------------------------------------------------------------------
        String Send_Time_Interval_name = sharedPreferences.getString("Send_Time_Interval", "");   //读当前IP地址

        if (Send_Time_Interval_name.equals(""))                                   //如果读出来的是空，则恢复默认值
        {
            SharedPreferences mySharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("Send_Time_Interval", "3");
            editor.commit();
        }
        TCP_UserData_Information.Send_Time_Interval = Integer.parseInt(sharedPreferences.getString("Send_Time_Interval", ""));   //读当前IP地址
        //-----------------------------------------------------------------------------------------------------------
        IP_SPlit = TCP_UserData_Information.IpAddress.split("\\.");
        IPAddress1.setText(IP_SPlit[0]);
        IPAddress2.setText(IP_SPlit[1]);
        IPAddress3.setText(IP_SPlit[2]);
        IPAddress4.setText(IP_SPlit[3]);
        Port_Number.setText(Integer.toString(TCP_UserData_Information.Port_Number));
        OutofTime.setText(Integer.toString(TCP_UserData_Information.TCP_Keep_Alive / 1000));
        Send_Time.setText(Integer.toString(TCP_UserData_Information.Send_Time_Interval / 1000));
    }

    //****************************************************************************************************************
    private void Init_UI() {
        IPAddress1 = (EditText) this.findViewById(R.id.IP_Address1);
        IPAddress2 = (EditText) this.findViewById(R.id.IP_Address2);
        IPAddress3 = (EditText) this.findViewById(R.id.IP_Address3);
        IPAddress4 = (EditText) this.findViewById(R.id.IP_Address4);
        OutofTime = (EditText) this.findViewById(R.id.Outoftime);
        Send_Time = (EditText) this.findViewById(R.id.Send_Time);
        Port_Number = (EditText) this.findViewById(R.id.Port_Name);
        Save_Config = (Button) this.findViewById(R.id.Save_Button);
        Canel_Config = (Button) this.findViewById(R.id.Canel_Button);
        Device_State_Text = (TextView) this.findViewById(R.id.Device_State);

    }

    //****************************************************************************************************************
    private void Init_Dialog() {
        Dialog = new AlertDialog.Builder(this)
                .setCancelable(false)   //屏幕外部区域点击无效
                .setTitle("错误：")
                .setMessage("网络断开或服务器未开启！请检查网络！")
                .setPositiveButton("确定", null).create();
    }

    private void Init_Save_Dialog() {
        Save_Dialog = new AlertDialog.Builder(this)
                .setCancelable(false)   //屏幕外部区域点击无效
                .setTitle("提示：")
                .setMessage("确认要保存吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (
                                IPAddress1.getText().toString().equals("") ||
                                        IPAddress2.getText().toString().equals("") ||
                                        IPAddress3.getText().toString().equals("") ||
                                        IPAddress4.getText().toString().equals("") ||
                                        Port_Number.getText().toString().equals("") ||
                                        OutofTime.getText().toString().equals("") ||
                                        Send_Time.getText().toString().equals("")
                                ) {
                            //Toast.makeText(MainActivity.this.getApplicationContext(), "参数不能空着不填！", 0).show();
                        } else if (
                                (Integer.parseInt(OutofTime.getText().toString()) < 10) ||
                                        (Integer.parseInt(Send_Time.getText().toString()) < 1)
                                ) {

                            // Toast.makeText(MainActivity.this.getApplicationContext(), "发送间隔最少设置1秒钟，超时时间最少设置10秒钟，请修改", 0).show();
                        } else {
                            TCP_UserData_Information.IpAddress = IPAddress1.getText().toString() + "." +
                                    IPAddress2.getText().toString() + "." +
                                    IPAddress3.getText().toString() + "." +
                                    IPAddress4.getText().toString();                             //写入IP地址

                            TCP_UserData_Information.Port_Number = Integer.parseInt(Port_Number.getText().toString());

                            TCP_UserData_Information.TCP_Keep_Alive = Integer.parseInt(OutofTime.getText().toString()) * 1000;       //写入TCP超时时间

                            TCP_UserData_Information.Send_Time_Interval = Integer.parseInt(Send_Time.getText().toString()) * 1000;    //写入TCP发送间隔

                            SharedPreferences mySharedPreferences = getSharedPreferences("TCP_Data", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mySharedPreferences.edit();

                            editor.putString("IP_Address", TCP_UserData_Information.IpAddress);
                            editor.putString("Port_Number", Integer.toString(TCP_UserData_Information.Port_Number));
                            editor.putString("TCP_Keep_Alive", Integer.toString(TCP_UserData_Information.TCP_Keep_Alive));
                            editor.putString("Send_Time_Interval", Integer.toString(TCP_UserData_Information.Send_Time_Interval));

                            editor.commit();


                            // Toast.makeText(MainActivity.this.getApplicationContext(), "设置成功！重新连接", 0).show();
                            if (TCP_UserData_Information.Device_State == false) {
                                Device_State_Text.setText("网络未开或服务器关");
                            } else {
                                TCP_UserData_Information.TCP_Contrl.stopsocket();
                                Device_State_Text.setText("立即断线重连");
                            }
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                    }
                }).create();      //取消不需要任何代码

    }

    //****************************************************************************************************************
    private void Init_AffirmDialog() {
        Affirm_Dialog = new AlertDialog.Builder(this)
                .setCancelable(false)   //屏幕外部区域点击无效
                .setTitle("提示：")
                .setMessage("确认要操作吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        switch (TCP_UserData_Information.AffirmFlag) {
                            case 1:  //第一个继电器
                                //*************************************************************************//
                                if (TCP_UserData_Information.Digital_Output[0] == false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 1;
                                    TCP_UserData_Information.Digital_Output[0] = true;
                                } else {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 16;
                                    TCP_UserData_Information.Digital_Output[0] = false;
                                }
                                TCP_UserData_Information.AffirmFlag = 0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 2:  //第二个继电器
                                //*************************************************************************//
                                if (TCP_UserData_Information.Digital_Output[1] == false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 2;
                                    TCP_UserData_Information.Digital_Output[1] = true;
                                } else {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 17;
                                    TCP_UserData_Information.Digital_Output[1] = false;
                                }
                                TCP_UserData_Information.AffirmFlag = 0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;
                            case 3:  //第三个继电器
                                //*************************************************************************//
                                if (TCP_UserData_Information.Digital_Output[2] == false)  //现在是关的状态
                                {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 3;
                                    TCP_UserData_Information.Digital_Output[2] = true;
                                } else {
                                    TCP_UserData_Information.Write_Array[TCP_UserData_Information.Write_Array_Index] = 18;
                                    TCP_UserData_Information.Digital_Output[2] = false;
                                }
                                TCP_UserData_Information.AffirmFlag = 0;          //执行完毕，状态信息归零
                                //*************************************************************************//
                                break;

                            default:
                                TCP_UserData_Information.AffirmFlag = 0;          //执行完毕，状态信息归零
                                break;
                        }
                        TCP_UserData_Information.Write_Array_Index++;
                        if (TCP_UserData_Information.Write_Array_Index > 19) {
                            TCP_UserData_Information.Write_Array_Index = 0;
                        }  //消息队列送够19，则送满，这时候返回第一个 也就是说5秒钟之内最多能接收50条指令
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TCP_UserData_Information.AffirmFlag = 0;

                    }
                }).create();      //取消不需要任何代码
    }

    //****************************************************************************************************************

    //****************************************************************************************************************
    private void Init_WriteCommand_Timer() {
        Timer time = new Timer();
        time.schedule(new WriteCommand_Task(), 1000, 2000);  //开机1秒钟之后，每隔2秒钟检索一次发送队列，并操作发送队列发送写指令
    }

    //****************************************************************************************************************
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键,直接返回到桌面
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (TCP_Data.Exit_State == false) {
                // Toast.makeText(MainActivity.this.getApplicationContext(), "再按一次退出程序", 0).show();
                TCP_Data.Exit_State = true;
                TCP_Data.Exit_time = System.currentTimeMillis();
                return false;
            } else {
                if (System.currentTimeMillis() - TCP_Data.Exit_time > 3000) {
                    TCP_Data.Exit_State = false;
                    TCP_Data.Exit_time = 0;
                    return false;
                } else {
                    System.exit(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //****************************************************************************************************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Init_UI();
        Init_WriteCommand_Timer();                            //初始化06指令发送消息队列
        Init_Dialog();
        Init_Save_Dialog();                                   //初始化警告对话框
        Init_AffirmDialog();
        Init_UI();
        Init_Config();                                        //初始化设置信息、
        mGestureDetector = new GestureDetector(this, new MyGestureListener(this));
        if (TCP_UserData_Information.Device_State == false)      //判断设备网络连接状态
        {
            Device_State_Text.setText("网络未开或服务器关");
        } else {
            Device_State_Text.setText("软件初始化完毕");
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }
//****************************************************************************************************************

    @SuppressLint("HandlerLeak")
    public void onResume() {
        super.onResume();  //不加这个，则程序错误退出
        //---------------------------------------------------------------------------------------
        Save_Config.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Save_Dialog.show();
            }
        });
        //---------------------------------------------------------------------------------------
        Canel_Config.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                IP_SPlit = TCP_UserData_Information.IpAddress.split("\\.");
                IPAddress1.setText(IP_SPlit[0]);
                IPAddress2.setText(IP_SPlit[1]);
                IPAddress3.setText(IP_SPlit[2]);
                IPAddress4.setText(IP_SPlit[3]);
                Port_Number.setText(Integer.toString(TCP_UserData_Information.Port_Number));
                OutofTime.setText(Integer.toString(TCP_UserData_Information.TCP_Keep_Alive / 1000));
                Send_Time.setText(Integer.toString(TCP_UserData_Information.Send_Time_Interval / 1000));
            }
        });
        //---------------------------------------------------------------------------------------
        IPAddress1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 255) {
                        IPAddress1.setText("255");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        IPAddress2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 255) {
                        IPAddress2.setText("255");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        IPAddress3.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 255) {
                        IPAddress3.setText("255");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        IPAddress4.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 255) {
                        IPAddress4.setText("255");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        Port_Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 65536) {
                        Port_Number.setText("65536");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        OutofTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 120) {
                        OutofTime.setText("120");  //超时时间最大2分钟
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //---------------------------------------------------------------------------------------
        Send_Time.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && !s.equals("")) {
                    int a = 0;
                    try {
                        a = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        a = 0;
                    }
                    if (a > 3600)   //发送间隔最大1小时
                    {
                        Send_Time.setText("3600");
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        //*************************************************************************//

        //*************************************************************************//
        TCP_UserData_Information.TCP_Master = new Handler() {
            public void handleMessage(Message msg) {
                //如果该消息是本程序所发送的
                switch (msg.what) {

                    case 0xffff:
                        Device_State_Text.setText("服务器连接成功");
                        break;
                    case 0xfffe:
                        Device_State_Text.setText("服务器未开启/断网");
                        break;
                    case 0xfffd:
                        Device_State_Text.setText("正常收发数据");
                        break;
                    case 0xfffc:
                        Device_State_Text.setText("服务器断开稍后重试");
                        break;
                    case 0x0101:
                        tab_Index++;
                        if (tab_Index > 2) {
                            tab_Index = 2;
                        }
                        tabHost.setCurrentTab(tab_Index);
                        break;
                    case 0x1010:
                        tab_Index--;
                        if (tab_Index == -1) {
                            tab_Index = 0;
                        }
                        tabHost.setCurrentTab(tab_Index);
                        break;
                }
            }
        };
        //*************************************************************************//
    }
}
    //***************************************************************************************************************