package com.runtime.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Before;

import java.io.IOException;
//  conf.set("hbase.zookeeper.quorum", "node01,node02,node03");
//  conf.set("hbase.client.scanner.timeout.period", "600000");
//  conf.set("hbase.master", "node02:16000");
//  conf.set("hbase.rpc.timeout", "600000");
/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/18 15:54
 * @Description
 */
public class de {
    static Connection conn = null;

    static {

        try {
            Configuration conf = HBaseConfiguration.create();
            conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "master");// 使用eclipse时必须添加这个，否则无法定位master需要配置hosts
            conf.set("hbase.zookeeper.property.clientPort", "2181");

            conf.set("hbase.zookeeper.quorum", "node01,node02,node03");
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(conn);

        try {
            Admin admin = conn.getAdmin();
            if (admin.tableExists(TableName.valueOf("ll"))){
                System.out.println("in");
            }
            System.out.println("of");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //TableName tableName = TableName.valueOf("ll");

    }

}
