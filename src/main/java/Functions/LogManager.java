package Functions;

import Utils.SqlQueryUtils;

import java.sql.*;

public class LogManager implements Function {
    private Log log = new Log();
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";
    static final String USER_NAME = "us_test_f.xiong";
    static final String USER_PASSWORD = "cZOfjVj4ycP2yTA0";
    private Connection conn;
    private Statement statement;

    /**
     * 连接JDBC
     */
    public void intializeDB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public Log getLog() {
        return log;
    }

    /**
     * 查询当前登录用户的所有日志
     * @param userName
     * @return
     */
    public String checkAllLogs(String userName) {
        String sql = SqlQueryUtils.buildLogQuery(userName);
        ResultSet result;
        try {
            result = statement.executeQuery(sql);
            return result.getString(2);
        } catch (SQLException e) {

        }
        return null;

    }

    /**
     * 根据日期查询当前用户的日志
     * @param userName
     * @param date
     * @return
     */
    public String checkLogsByDate(String userName, Date date) {
        String sql = SqlQueryUtils.buildLogQueryByDate(userName, date);
        ResultSet result;
        try {
            result = statement.executeQuery(sql);
            return result.getString(2);
        } catch (SQLException e) {

        }
        return null;

    }



}
