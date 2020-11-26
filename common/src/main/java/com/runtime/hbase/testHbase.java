package com.runtime.hbase;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/18 20:40
 * @Description
 */
public class testHbase {

    private static Configuration conf = null;
    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "master");// 使用eclipse时必须添加这个，否则无法定位master需要配置hosts
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "node01,node02,node03");
    }

    public static void main(String[] args) throws IOException {
        String[] cols = new String[1];
        String[] colsValue = new String[1];
        cols[0] = "title";
        colsValue[0] = "AboutYunArticle";

        // 创建表
        createTable();
        // 添加值
        addData("www.aboutyun.com", "blog", cols, colsValue);
    }

    private static void createTable() throws MasterNotRunningException,
            ZooKeeperConnectionException, IOException {

        HBaseAdmin admin = new HBaseAdmin(conf);// 新建一个数据库管理员//新api
        if (admin.tableExists(TableName.valueOf("LogTable"))) {
            System.out.println("table is not exist!");
            System.exit(0);
        } else {

            HTableDescriptor desc = new HTableDescriptor(
                    TableName.valueOf("blog"));
            desc.addFamily(new HColumnDescriptor("article"));

            admin.createTable(desc);
            admin.close();
            System.out.println("create table Success!");
        }
    }

    /*
     * 为表添加数据（适合知道有多少列族的固定表）
     *
     * @rowKey rowKey
     *
     * @tableName 表名
     *
     * @column1 第一个列族列表
     *
     * @value1 第一个列的值的列表
     *
     */
    private static void addData(String rowKey, String tableName,String[] column1, String[] value1) throws IOException {
        Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
        HTable table = new HTable(conf, Bytes.toBytes(tableName));// HTabel负责跟记录相关的操作如增删改查等//
        // 获取表
        HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
                .getColumnFamilies();

        for (int i = 0; i < columnFamilies.length; i++) {
            String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
            if (familyName.equals("article")) { // article列族put数据
                for (int j = 0; j < column1.length; j++) {
                    put.add(Bytes.toBytes(familyName),
                            Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
                }
            }

        }
        table.put(put);
        System.out.println("add data Success!");
    }


}
