package com.runtime.java.io;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/17 9:09
 * @Description
 */
public class select {
    public static void main(String[] args) throws IOException {

        //创建 Selector ：通过调用 Selector.open() 方法创建一个 Selector。
        Selector open = Selector.open();

        //向选择器注册通道：SelectableChannel.register(Selector sel, int ops)
        SelectionKey key = SocketChannel.open(new InetSocketAddress("127.0.0.1", 4399)).register(open, SelectionKey.OP_READ | SelectionKey.OP_ACCEPT);

        // 管道注册 / 监听时间类型
        //  读 : SelectionKey.OP_READ （1）
        //  写 : SelectionKey.OP_WRITE (4)
        //  连接 : SelectionKey.OP_CONNECT (8)
        //  接收 : SelectionKey.OP_ACCEPT （16）

        //注册成功后 轮询式的获取选择器上已经“准备就绪”的事件
        while (open.select() > 0) {
            Iterator<SelectionKey> keyIterator = open.selectedKeys().iterator();

            //获取 选择器上的事件
            while (keyIterator.hasNext()) {
                SelectionKey next = keyIterator.next();

                //判断事件类型
                next.isReadable(); //检测 channel 中读事件是否就绪
                next.isAcceptable(); //检测 channel 中接收是否就绪
                next.isConnectable(); //检测 channel 中连接是否就绪
//                next.isValid(); // ?
                next.isWritable(); //检测 channel 中写事件是否就绪


            }
        }
    }
    /**
     * 方 法 描 述
     * int interestOps() 获取感兴趣事件集合
     * int readyOps() 获取通道已经准备就绪的操作的集合
     * SelectableChannel channel() 获取注册通道
     * Selector selector() 返回选择器
     * boolean isReadable() 检测 Channal 中读事件是否就绪
     * boolean isWritable() 检测 Channal 中写事件是否就绪
     * boolean isConnectable() 检测 Channel 中连接是否就绪
     * boolean isAcceptable() 检测 Channel 中接收是否就绪
     */


}



