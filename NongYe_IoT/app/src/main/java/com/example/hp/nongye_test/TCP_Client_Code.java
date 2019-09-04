package com.example.hp.nongye_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Message;

import com.example.hp.nongye_test.TCP_UserData_Information;

//***********************************************************************
/*方法名称：public void stopsocket()  */
/*方法功能：关闭SOCKET连接                                 */
/*作用区域：公共的，所有都可访问                   */
/*返回值： 无                                                                    */
/*版本：2015年1月3日                                                */
class ServerThread extends Thread
{
    Socket socket;	                        //SOCKET对象
    InputStream inputStream;               //输入流
    OutputStream outputStream;             //输出流
    boolean Frist_Create_TCP=false;
    TCP_UserData_Information TCP_Data=new TCP_UserData_Information();   //TCP发送数据的类对象
    public void run()
    {
        while(true)
        {
            Create_Socket();  //建立SOCKET连接。

            while(socket!=null)
            {
                Timer_Delay(TCP_UserData_Information.Send_Time_Interval);
                send_HexData(TCP_Data.Read_SomeThing);
                try {
                    if((inputStream.read(TCP_Data.Receive_Buffer))!=-1)
                    {
                        if(TCP_Data.Receive_Buffer[7]!=0x06)  //一旦出现06，过滤掉
                        {
                            //------------------------------------------
                            Message msg3=new Message();
                            msg3.what=0xfffd;
                            TCP_UserData_Information.TCP_Master.sendMessage(msg3);    //处理03数据
                            //------------------------------------------
                            TCP_Data.Send_Relay_State();
                            TCP_Data.Send_Base_Information();	   //发送基本03信息
                        }
                    }
                    else
                    {
                        //------------------------------------------
                        Message msg4=new Message();
                        msg4.what=0xfffc;
                        TCP_UserData_Information.TCP_Master.sendMessage(msg4);    //服务器断开！
                        //------------------------------------------
                        socket.close();
                        socket=null;    //并未真正关闭，要手动清空
                        TCP_UserData_Information.Device_State=false;
                        Create_Socket();
                    }
                }
                catch (InterruptedIOException e)
                {
                    e.printStackTrace();
                    TCP_UserData_Information.Device_State=false;
                    try {
                        socket.close();
                        socket=null;      //并未真正关闭，要手动清空
                        Create_Socket();  //超时之后马上连接socket
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    public void send_HexData(byte[] Send_Data)
    {
        try {
            outputStream.write(Send_Data);
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    public void Create_Socket()
    {
        if(socket==null)
        {
            try {
                TCP_UserData_Information.first_Init_ButtonName[0]=false;
                TCP_UserData_Information.first_Init_ButtonName[1]=false;
                TCP_UserData_Information.first_Init_ButtonName[2]=false;
                TCP_UserData_Information.first_Init_ButtonName[3]=false;
                TCP_UserData_Information.first_Init_ButtonName[4]=false;
                TCP_UserData_Information.first_Init_ButtonName[5]=false;
                TCP_UserData_Information.first_Init_ButtonName[6]=false;
                TCP_UserData_Information.first_Init_ButtonName[7]=false;

                socket=new Socket(TCP_UserData_Information.IpAddress,TCP_UserData_Information.Port_Number);	  //指定一个本机端口号，要不然有点乱
                socket.setSoTimeout(TCP_UserData_Information.TCP_Keep_Alive);
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
                TCP_UserData_Information.Device_State=true;
                //------------------------------------------
                Message msg1=new Message();
                msg1.what=0xffff;
                TCP_UserData_Information.TCP_Master.sendMessage(msg1);    //服务器连接成功！
                //------------------------------------------
                Frist_Create_TCP=true;    //第一次连接成功，则显示true   此后该变量值不变
            }
            catch (UnknownHostException e)
            {
                System.out.println("链接发生异常");
                TCP_UserData_Information.Device_State=false;
                e.printStackTrace();

            }
            catch (IOException e)
            {
                TCP_UserData_Information.Device_State=false;
                if(Frist_Create_TCP==true)
                {
                    Timer_Delay(30000);            //这时候可以原地等待的原因是不连上socket,啥都干不了
                }

                try {
                    if(socket!=null)
                    {
                        socket.close();
                        socket=null;
                    }
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                   */
    public void send_String(String TCP_String)
    {
        if(socket!=null)
        {
            try {
                PrintWriter Write_String=new PrintWriter(outputStream);
                Write_String.write(TCP_String);
                Write_String.flush();
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    public void stopsocket()
    {
        try {
            while(socket!=null)
            {
                socket.close();
                inputStream.close();
                outputStream.close();
                socket=null;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("未知错误");
        }
    }
    //***********************************************************************
    /*方法名称：public void stopsocket()  */
    /*方法功能：关闭SOCKET连接                                 */
    /*作用区域：公共的，所有都可访问                   */
    /*返回值： 无                                                                    */
    /*版本：2015年1月3日                                                */
    public void Timer_Delay(long Delay_Time)
    {
        long Now_Time;
        Now_Time=System.currentTimeMillis(); //存基准时间值
        while(Now_Time>0)
        {
            if((System.currentTimeMillis()-Now_Time)>Delay_Time)   //如果当前时间值和记录的时间基准之前的差值大于某个值
            {
                Now_Time=0;
            }
        }
    }

}
