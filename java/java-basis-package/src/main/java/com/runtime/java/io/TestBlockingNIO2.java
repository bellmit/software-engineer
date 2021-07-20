package com.runtime.java.io;

import org.junit.Test;

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
 * @date 2020/8/15 19:10
 * @Description  客户端发送信息 服务端响应返回
 */
public class TestBlockingNIO2 {

    private ByteBuffer buffer;

    @Test
    public void client() {
        try {

            SocketChannel ssChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 4399));
            FileChannel fileChannel = FileChannel.open(Paths.get("D:\\Custom\\m1.mp3"));


            //ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                ssChannel.write(buffer);
                buffer.clear();
            }


            ssChannel.shutdownOutput();

            int len = 0;
            while ((len = ssChannel.read(buffer)) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, len));
                buffer.clear();
            }


            fileChannel.close();
            ssChannel.close();


        } catch (Exception e) {
        }


    }


    @Test
    public void services() {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            FileChannel fileChannel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            socketChannel.bind(new InetSocketAddress(4399));

            SocketChannel accept = socketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (accept.read(buffer) != -1) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }

            //发送给客户端
            buffer.put("Hello World".getBytes());
            buffer.flip();
            accept.write(buffer);

            accept.close();
            fileChannel.close();
            socketChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
