package com.runtime.java.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/15 18:12
 * @Description
 */
public class TestBlockingNIO {

    //***********************************************************
    // 管道   |-->文件管道<--|     -->    [--->缓冲区<---]     写出

    @Test
    public void clien1() throws IOException {


        //channel
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2625));

        FileChannel channel = FileChannel.open(Paths.get("D:\\Custom\\m1.mp3"), StandardOpenOption.READ);

        //buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取 数据 发送到服务器
        while (channel.read(buffer) != -1) {
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }

        //关闭
        channel.close();
        sChannel.close();

    }

    // 管道   ||-> 绑定连接   ||-> 缓冲区    ||-> 写出流
    @Test
    public void service1() throws IOException {

        //获取链接 绑定 干活 写出
        ServerSocketChannel ssChannel1 = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        ssChannel1.bind(new InetSocketAddress(2625));

        SocketChannel channel1 = ssChannel1.accept();


        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel1.read(buffer) != -1) {
            buffer.flip();

            fileChannel.write(buffer);
            buffer.clear();
        }

        channel1.close();
        fileChannel.close();
        ssChannel1.close();


      /*  //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel channel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //绑定链接
        ssChannel.bind(new InetSocketAddress(2625));

        //获取客户端数据
        SocketChannel accept = ssChannel.accept();

        //接收数据 保存到本地
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (accept.read(byteBuffer) != -1){
            byteBuffer.flip();
            channel.write(byteBuffer);
            byteBuffer.clear();
        }

        accept.close();
        channel.close();
        ssChannel.close();*/

    }
    //***********************************************************
}
