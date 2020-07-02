package com.runtime.algorithm.structure.tree.huffman;


import java.io.*;
import java.util.*;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/2 9:55
 * @Description
 */
public class HuffmanTreeCode {

    //生成赫夫曼树对应的赫夫曼编码 生成的赫夫曼编码表{32=01, 97=100, 100=11000, }...
    static Map<Byte, String> HUFFMANCODES = new HashMap<Byte, String>();
    //在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder STRINGBUILDER = new StringBuilder();


    static String INPUT = "";
    static String OUTPUT = "";


    public static void main(String[] args) {

        //todo 传入字符串 转换成 byte 数组

        // String content = "i like like like java do you like a java";
        // System.out.println("元数据 ==> " + content);
        // byte[] contentBytes = content.getBytes();
        // System.out.println("元数据转换成Byte 数组 ==> " + Arrays.toString(contentBytes));

        // //todo 将Byte数组转换成 赫夫曼压缩成功
        // byte[] huffmanZip = huffmanZip(contentBytes);
        // System.out.println("压缩后 ==> " + Arrays.toString(huffmanZip));  //数据压缩成功 //--[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        // System.out.println("赫夫曼码 ==>" + HUFFMANCODES);
        // //todo 压缩成功 那就解压
        // System.out.println("解压后 ==> " + Arrays.toString(decode(HUFFMANCODES, huffmanZip))); //解码成功 [105, 32, 108, 105, 107, 101, 32, 108, 105, 107


        // String psth1 = "";
        zipFile(INPUT, OUTPUT);
        unZipFile(OUTPUT, INPUT);

    }


    //todo  编写一个方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {

            try {

                if (is != null) {
                    is.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("e2 " + e2.getMessage());
            }

        }
    }


    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(HUFFMANCODES);


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }

    }


    // todo 将 原始字符串对应的字节数组 转换成 和府办编码处理后的字节数组
    private static byte[] huffmanZip(byte[] contentBytes) {
        //todo 返回数组二叉树 需要构建
        List<NodeCode> nodes = getNodes(contentBytes);
        //todo 创建赫夫曼树
        NodeCode huffmanTreeRoot = createHuffmanTree(nodes);
        //todo  对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //todo 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    //todo 编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }


    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //todo 方法重载
    private static Map<Byte, String> getCodes(NodeCode root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", STRINGBUILDER);
        getCodes(root.right, "1", STRINGBUILDER);
        return HUFFMANCODES;
    }

    //todo 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
    private static void getCodes(NodeCode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) { //如果node == null不处理
            //判断是叶子节点 还是非叶子节点 左走为1 右走为2
            if (node.data == null) { //子节点  非叶子节点
                //向左递归
                getCodes(node.left, "0", stringBuilder1);
                //向右递归
                getCodes(node.right, "1", stringBuilder1);
            } else {
                //System.out.println(stringBuilder1.toString()); // 000 0010 0011 01 100
                HUFFMANCODES.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //todo 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     * @param bytes        这是原始的字符串对应的 byte[] | byte[] contentBytes
     * @param huffmanCodes 生成的赫夫曼编码的count集合 | HashMap<字节,次数>...
     * @return 返回赫夫曼编码处理后的 byte[]   | 1010100010111111110010001011111111001000101111 ...
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //System.out.println("huffmanCodes => " + huffmanCodes); //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        //System.out.println("bytes =>　" + Arrays.toString(bytes)); //[105, 32, 108, 105, 107, 101, 32,
        //2. 遍历bytes数组
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位对应一个byte,所以步长 +8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //todo 可以通过List 创建对应的赫夫曼树
    private static NodeCode createHuffmanTree(List<NodeCode> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出 0 1
            NodeCode leftNodeCode = nodes.get(0);
            //取出第二颗最小的二叉树
            NodeCode rightNodeCode = nodes.get(1);
            //累加权值 -> 子节点 没有data 只有权值
            NodeCode newNodeCode = new NodeCode(null, leftNodeCode.weight + rightNodeCode.weight);

            // todo 做关联
            newNodeCode.left = leftNodeCode;
            newNodeCode.right = rightNodeCode;

            //移除 拼进
            nodes.remove(leftNodeCode);
            nodes.remove(rightNodeCode);
            nodes.add(newNodeCode);

        }
        //nodes 最后的结点，就是赫夫曼树的根结点
        return nodes.get(0);
    }

    //todo 接收字节数组 返回一个List 里面装的是 NodeCode 格式 
    private static List<NodeCode> getNodes(byte[] bytes) {
        //1. 创建一个list 用来装 Nodes
        List nodes = new ArrayList<NodeCode>();
        //2. 创建一个HashMap 统计每一个byte出现的次数
        HashMap<Byte, Integer> hashMap = new HashMap<>();
        //3. 遍历字节数组 
        for (byte aByte : bytes) {
            Integer count = hashMap.get(aByte);
            if (count != null) {
                hashMap.put(aByte, count + 1);
            } else {
                hashMap.put(aByte, 1);
            }
        }
        //4. 把每一个键值 转成一个 Node 对象 并加入到集合中
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            nodes.add(new NodeCode(entry.getKey(), entry.getValue()));
        }
        //5. 返回NodeCode 格式
        return nodes;
    }

    //todo 前序遍历
    public static void perOrder(NodeCode root) {
        if (root != null) {
            root.preOrder();
        }
    }
}

//创建Node ,待数据和权值
class NodeCode implements Comparable<NodeCode> {
    Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    int weight; //权值, 表示字符出现的次数
    NodeCode left;
    NodeCode right;


    //编写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public NodeCode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 升序排列
    // 如果 降序 可以  -(this.value - o.value)
    @Override
    public int compareTo(NodeCode o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

}