package com.example.hp.nongye_test;

import android.os.Handler;
import android.os.Message;

/*类名称：    TCP用数据信息                                      */
/*类功能：    供TCP收发数据使用                           */
/*作用区域：公共的，所有都可访问                   */
/*版本：2015年1月3日                                                */

public class TCP_UserData_Information
{
    long Exit_time=0;             //退出的时间基准值
    boolean Exit_State=false;     //返回键的按键状态

    static boolean[] first_Init_ButtonName=new boolean[15];
    //-----------------需要被多个类引用的同一个实例------------------------------
    static boolean Device_State=false;          //设备连接服务器的状态
    static byte Deviec_State_Code=0;
    //-------------------------------------------------------------------------
    //*******TCP用时间类参数****************
    static int Send_Time_Interval;         //查询指令发送间隔
    static int TCP_Keep_Alive;            //TCP保活时间（TCP超时）
    static int AffirmFlag=0;              //默认是不按下的状态
    //-------------------------------------------------------------------------
    static int[] Write_Array=new int[20]; //发送队列 每次定时器会检索发送队列里的数据，如果非零（则取1-15，用来操作1号到15号继电器）数量暂定20
    static int Write_Array_Index=0;
    //-------------------------------------------------------------------------
    //*******防止重复建立socket对象****************
    static ServerThread TCP_Contrl= new ServerThread();  //TCP线程类  //TCP线程类
    //-------------------------------------------------------------------------
    //*******查询指令用数组****************
    final byte[] Relay1_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x09,0x00,0x01};
    final byte[] Relay1_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x09,0x00,0x00};

    final byte[] Relay2_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0A,0x00,0x01};
    final byte[] Relay2_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0A,0x00,0x00};

    final byte[] Relay3_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0B,0x00,0x01};
    final byte[] Relay3_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0B,0x00,0x00};

    final byte[] Relay4_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0C,0x00,0x01};
    final byte[] Relay4_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0C,0x00,0x00};

    final byte[] Relay5_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0D,0x00,0x01};
    final byte[] Relay5_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0D,0x00,0x00};

    final byte[] Relay6_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0E,0x00,0x01};
    final byte[] Relay6_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0E,0x00,0x00};

    final byte[] Relay7_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0F,0x00,0x01};
    final byte[] Relay7_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x0F,0x00,0x00};

    final byte[] Relay8_On=new byte[]  {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x10,0x00,0x01};
    final byte[] Relay8_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x10,0x00,0x00};

    final byte[] Relay9_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x11,0x00,0x01};
    final byte[] Relay9_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x11,0x00,0x00};

    final byte[] Relay10_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x12,0x00,0x01};
    final byte[] Relay10_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x12,0x00,0x00};

    final byte[] Relay11_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x13,0x00,0x01};
    final byte[] Relay11_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x13,0x00,0x00};

    final byte[] Relay12_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x14,0x00,0x01};
    final byte[] Relay12_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x14,0x00,0x00};

    final byte[] Relay13_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x15,0x00,0x01};
    final byte[] Relay13_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x15,0x00,0x00};

    final byte[] Relay14_On =new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x16,0x00,0x01};
    final byte[] Relay14_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x16,0x00,0x00};

    final byte[] Relay15_On=new byte[]  {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x17,0x00,0x01};
    final byte[] Relay15_Off=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x06,0x00,0x17,0x00,0x00};

    final byte[] Read_SomeThing=new byte[] {-2,-2,0x00,0x00,0x00,0x06,0x01,0x03,0x00,0x00,0x00,0x18};    //这个东西就是发送的数组了
    //0地址起，查询24个
    //-------------------------------------------------------------------------

    //*******端口号和IP地址控制变量****************
    static String IpAddress;           //IP地址储存数组,之后用文本框交互就行
    static int Port_Number;                      //端口号储存变量，之后用文本框交互就行

    //-------------------------------------------------------------------------
    //*******接收缓冲区****************

    byte[] Receive_Buffer=new byte[300];       //缓冲区最大259个，我测试过的，但是设成300个无妨

    //---------------------------------------------------------------------------

    //*******基本控制变量定义,设计成可以直接引用的全局变量型**********
    static int[] Digital=new int[8];                  //模拟量采集值（数字量）存储数组
    static double[] Analog=new double[8];             //模拟量采集值（电流值）存储数组
    static boolean[] Digital_Output=new boolean[15];  //15路继电器输出状态值
    static boolean[] Digital_Input=new boolean[8];    //8路数字量输入状态值
    static Handler TCP_Master;                       //线程传递参数用handler
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    void Send_Base_Information()
    {   //按顺序发送出9个int型的数据，对应寄存器表0-8
        int Temp=0;
        int Temp1=0;

        Message Base_Infor_1=new Message();
        Base_Infor_1.what=0x0010;

        if(Receive_Buffer[9]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[10]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_1.arg1=(Receive_Buffer[9]+Temp)*256+Receive_Buffer[10]+Temp1;       //空气环境：温度

        if(Receive_Buffer[11]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[12]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_1.arg2=(Receive_Buffer[11]+Temp)*256+Receive_Buffer[12]+Temp1;      //空气环境：湿度

        TCP_Master.sendMessage(Base_Infor_1);

        Message Base_Infor_2=new Message();
        Base_Infor_2.what=0x0011;

        if(Receive_Buffer[13]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[14]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_2.arg1=(Receive_Buffer[13]+Temp)*256+Receive_Buffer[14]+Temp1;      //空气环境：光照

        if(Receive_Buffer[15]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[16]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_2.arg2=(Receive_Buffer[15]+Temp)*256+Receive_Buffer[16]+Temp1;      //空气环境：二氧化碳浓度
        TCP_Master.sendMessage(Base_Infor_2);

        Message Base_Infor_3=new Message();
        Base_Infor_3.what=0x0012;

        if(Receive_Buffer[17]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[18]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_3.arg1=(Receive_Buffer[17]+Temp)*256+Receive_Buffer[18]+Temp1;      //土壤环境：温度

        if(Receive_Buffer[19]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[20]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_3.arg2=(Receive_Buffer[19]+Temp)*256+Receive_Buffer[20]+Temp1;      //土壤环境：湿度
        TCP_Master.sendMessage(Base_Infor_3);

        Message Base_Infor_4=new Message();
        Base_Infor_4.what=0x0013;

        if(Receive_Buffer[21]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[22]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_4.arg1=(Receive_Buffer[21]+Temp)*256+Receive_Buffer[22]+Temp1;      //土壤环境：盐溶解度

        if(Receive_Buffer[23]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[24]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_4.arg2=(Receive_Buffer[23]+Temp)*256+Receive_Buffer[24]+Temp1;      //土壤环境：PH值
        TCP_Master.sendMessage(Base_Infor_4);

        Message Base_Infor_5=new Message();
        Base_Infor_5.what=0x0014;

        if(Receive_Buffer[25]<0){Temp=256;}else{Temp=0;}
        if(Receive_Buffer[26]<0){Temp1=256;}else{Temp1=0;}
        Base_Infor_5.arg1=(Receive_Buffer[25]+Temp)*256+Receive_Buffer[26]+Temp1;      //灌溉环境：水箱水位

        Base_Infor_5.arg2=0;
        TCP_Master.sendMessage(Base_Infor_5);
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    void Send_Relay_State()
    {
        if((Receive_Buffer[27]*256+Receive_Buffer[28])>0)
        {
            Message msg=new Message();
            msg.what=0x0001;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1001;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[29]*256+Receive_Buffer[30])>0)
        {
            Message msg=new Message();
            msg.what=0x0002;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1002;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[31]*256+Receive_Buffer[32])>0)
        {
            Message msg=new Message();
            msg.what=0x0003;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1003;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[33]*256+Receive_Buffer[34])>0)
        {
            Message msg=new Message();
            msg.what=0x0004;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1004;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[35]*256+Receive_Buffer[36])>0)
        {
            Message msg=new Message();
            msg.what=0x0005;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x01005;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[37]*256+Receive_Buffer[38])>0)
        {
            Message msg=new Message();
            msg.what=0x0006;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1006;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[39]*256+Receive_Buffer[40])>0)
        {
            Message msg=new Message();
            msg.what=0x0007;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1007;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[41]*256+Receive_Buffer[42])>0)
        {
            Message msg=new Message();
            msg.what=0x0008;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1008;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[43]*256+Receive_Buffer[44])>0)
        {
            Message msg=new Message();
            msg.what=0x0009;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x1009;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[45]*256+Receive_Buffer[46])>0)
        {
            Message msg=new Message();
            msg.what=0x0000a;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100a;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[47]*256+Receive_Buffer[48])>0)
        {
            Message msg=new Message();
            msg.what=0x000b;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100b;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[49]*256+Receive_Buffer[50])>0)
        {
            Message msg=new Message();
            msg.what=0x000c;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100c;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[51]*256+Receive_Buffer[52])>0)
        {
            Message msg=new Message();
            msg.what=0x000d;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100d;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[53]*256+Receive_Buffer[54])>0)
        {
            Message msg=new Message();
            msg.what=0x000e;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100e;
            TCP_Master.sendMessage(msg);
        }
        if((Receive_Buffer[55]*256+Receive_Buffer[56])>0)
        {
            Message msg=new Message();
            msg.what=0x000f;
            TCP_Master.sendMessage(msg);
        }
        else
        {
            Message msg=new Message();
            msg.what=0x100f;
            TCP_Master.sendMessage(msg);
        }
    }
}
