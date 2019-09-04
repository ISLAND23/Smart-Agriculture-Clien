package com.example.hp.nongye_test;

import android.os.Handler;


public class WriteCommand_Task extends java.util.TimerTask
{
    Handler TCP_Master=new Handler();
    int i=0;
    int Index_Value=0;
    boolean Valid_Value=false;
    TCP_UserData_Information TCP_Data=new TCP_UserData_Information();
    @Override
    public void run()
    {
        //************************************************************************************************
		/*if(TCP_UserData_Information.Device_State==false)   //判断设备网络连接状态
		{

			Message Decevice_State=new Message();
			Decevice_State.what=0x0015;
			TCP_Master.sendMessage(Decevice_State);
		}*/
        //************************************************************************************************
        for(i=0;i<20;i++)   //检索目标目录中，第一个有效数据
        {
            if(TCP_UserData_Information.Write_Array[i]!=0)
            {
                Index_Value=i;          //存入有效数据的索引值
                Valid_Value=true;
                break;
            }

        }
        if(Valid_Value==true)     //表示出现了有效值
        {
            Valid_Value=false;
            System.out.println("序号:"+Index_Value);
            System.out.println("操作:"+TCP_UserData_Information.Write_Array[Index_Value]);
            switch(TCP_UserData_Information.Write_Array[Index_Value])     //判断当前索引值要进行什么操作
            {
                case 1:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay1_On);
                    break;
                case 16:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay1_Off);
                    break;
                case 2:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay2_On);
                    break;
                case 17:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay2_Off);
                    break;
                case 3:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay3_On);
                    break;
                case 18:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay3_Off);
                    break;
                case 4:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay4_On);
                    break;
                case 19:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay4_Off);
                    break;
                case 5:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay5_On);
                    break;
                case 20:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay5_Off);
                    break;
                case 6:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay6_On);
                    break;
                case 21:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay6_Off);
                    break;
                case 7:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay7_On);
                    break;
                case 22:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay7_Off);
                    break;
                case 8:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay8_On);
                    break;
                case 23:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay8_Off);
                    break;
                case 9:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay9_On);
                    break;
                case 24:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay9_Off);
                    break;
                case 10:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay10_On);
                    break;
                case 25:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay10_Off);
                    break;
                case 11:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay11_On);
                    break;
                case 26:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay11_Off);
                    break;
                case 12:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay12_On);
                    break;
                case 27:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay12_Off);
                    break;
                case 13:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay13_On);
                    break;
                case 28:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay13_Off);
                    break;
                case 14:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay14_On);
                    break;
                case 29:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay14_Off);
                    break;
                case 15:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay15_On);
                    break;
                case 30:
                    TCP_UserData_Information.TCP_Contrl.send_HexData(TCP_Data.Relay15_Off);
                    break;

            }
            TCP_UserData_Information.Write_Array[Index_Value]=0;                      //触发完毕，当前的处理值清零
        }
        else
        {
            //扫描完所有发送队列，发现一个有效值也没有，这时候什么都不做，直接退出
        }

    }
}
