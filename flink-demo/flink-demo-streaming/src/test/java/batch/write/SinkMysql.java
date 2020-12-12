package batch.write;

import runtime.utils.Singleton;
import runtime.utils.SplitSeparator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/11 14:58
 * @Description
 */
public class SinkMysql {
    public static void SinkMysqlSource(String upPojo) throws Exception {

        Connection connection = Singleton.SINGLETON.getInstance().getConnection();
        PreparedStatement statement = null;


        try {
            statement = connection.prepareStatement(SplitSeparator.BATCH_WRITE_MYSQL);
            statement.setString(1, upPojo);
            statement.executeUpdate();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            SinkMysql.SinkMysqlSource("123");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


