package yuque;

import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/14 20:38
 * @Description
 */
public class Nio {

    //非直接 缓冲区 基本操作 put/get/flip/rewind/clear
    @Test
    public void test1() {

        String str = "ABCDE";

        ByteBuffer buffer = ByteBuffer.allocate(1024);


        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit()); //1024
        System.out.println(buffer.capacity());  //1024

        System.out.println("-----------------------------Put--------------------------------");
        buffer.put(str.getBytes());


        System.out.println(buffer.position());  //5
        System.out.println(buffer.limit()); //1024
        System.out.println(buffer.capacity());  //1024

        System.out.println("-----------------------------flip--------------------------------");

        buffer.flip();

        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit()); //5
        System.out.println(buffer.capacity());  //1024

        System.out.println("-----------------------------Get--------------------------------");


        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);

        //System.out.println(new String(bytes,0,buffer.limit()));;

        System.out.println(buffer.position());  //5
        System.out.println(buffer.limit()); //5
        System.out.println(buffer.capacity());  //1024

        System.out.println("-----------------------------rewind------------------------------");

        buffer.rewind();


        System.out.println("-----------------------------clear--------------------------------");

        buffer.clear();
        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit()); //1024
        System.out.println(buffer.capacity());  //1024
    }

    @Test
    public void test2() {
        //******************************************************************************************************* 分配直接缓冲区

        ByteBuffer direct = ByteBuffer.allocateDirect(1024);
        System.out.println(direct.isDirect());

    }


    // 非 直接换缓冲区 模式
    @Test
    public void testChannel1() {
        FileInputStream fis = null;
        FileOutputStream fio = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;


        try {
            fis = new FileInputStream("D:\\Custom\\m1.mp3");
            fio = new FileOutputStream("D:\\Custom\\m2.mp3");

            inChannel = fis.getChannel();
            outChannel = fio.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer bytebuffer = ByteBuffer.allocate(1024);

            //将通道中的数据写入到缓冲区
            while (inChannel.read(bytebuffer) != -1) {
                bytebuffer.flip();
                outChannel.write(bytebuffer);
                bytebuffer.clear();
            }
        } catch (Exception e) {
        } finally {
            try {
                if (inChannel != null) inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outChannel != null) outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fio != null) fio.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 直接缓冲区模式
    @Test
    public void testChannel2() {

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inChannel = FileChannel.open(Paths.get("D:\\Custom\\m1.mp3"), StandardOpenOption.READ);
            //outChannel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.CREATE_NEW);
            outChannel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //映射文件
            MappedByteBuffer inMapped = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMapped = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] bytes = new byte[inMapped.limit()];
            inMapped.get(bytes);
            outMapped.put(bytes);

            outMapped.clear();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outChannel != null) outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 简化版 Open
    @Test
    public void testChannel3() {

        try {
            FileChannel inChannel = FileChannel.open(Paths.get("D:\\Custom\\m1.mp3"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("D:\\Custom\\m2.mp3"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

            outChannel.transferFrom(inChannel, 0, inChannel.size());
            inChannel.transferTo(0, inChannel.size(), outChannel);

            inChannel.close();
            outChannel.close();
        } catch (Exception e) {
        }


    }


}
