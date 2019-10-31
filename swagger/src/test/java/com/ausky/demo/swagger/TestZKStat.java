/**
 * File Name:    TestZKStat.java
 * <p>
 * File Desc:    TODO
 * <p>
 * Product AB:   TODO
 * <p>
 * Product Name: TODO
 * <p>
 * Module Name:  TODO
 * <p>
 * Module AB:    TODO
 * <p>
 * Author:       敖海样
 * <p>
 * History:      2019-10-30 created by hy.ao
 */
package com.ausky.demo.swagger;


import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: hy.ao
 * Date: 2019-10-30
 * Time: 10:44
 * 文件说明：TODO
 */
public class TestZKStat
{
    public static void main( String[] args ) throws Exception
    {
        Socket socket = new Socket( "192.168.222.123", 2181 );

        System.out.println( socket.isConnected() );

        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write( "stat".getBytes() );
        outputStream.write( "wchc".getBytes() );


        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[ 1024 * 100 ];
        int len = inputStream.read( bytes );

        System.out.println( len );

        System.out.println( new String( bytes ) );

    }
}