package com.runtime.es.CRUD;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/3 11:18
 * @Description
 */
public class MysqlConnect {
    private static ComboPooledDataSource dataSource;
    private int i = 0;

    public MysqlConnect() {
        dataSource = new ComboPooledDataSource();

        System.out.println("SingletonClass被初始化 " + ++i + " 次" + "ComboPooledDataSource 初始化一次");
    }

    private DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @return 获取Connect连接
     */
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
