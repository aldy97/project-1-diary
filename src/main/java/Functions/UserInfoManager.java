package Functions;

import Utils.SqlQueryUtils;

import java.sql.*;

/**
 * 用户信息数据库操作访问类
 */
public class UserInfoManager implements Function {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "us_test_f.xiong";
    private static final String USER_PASSWORD = "cZOfjVj4ycP2yTA0";

    private Connection conn;
    private Statement statement;

    private UserInfo userInfo = new UserInfo();
    private boolean isLoggedIn;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * 连接到JDBC
     */
    public void initializeDB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
            statement = conn.createStatement();
            System.out.println("连接成功");
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    /**
     * 向DB插入新用户信息
     *
     * @param （用户名，密码，邮箱）
     * @return
     */
    public boolean signUp(String newUserName, String newUserPassword, String newUserPassWord2, String newUserEmailAddress) {
        //如果用户名已存在或者两次密码输入不同则注册失败
        if (isUserNameExists(newUserName) || newUserPassword != newUserPassWord2) {
            return false;
        }

        String sql = SqlQueryUtils.buildInsert(newUserName, newUserPassword, newUserEmailAddress);
        int result = 0;
        try {
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {

        }
        System.out.println("注册成功！");
        return (result == -1 ? false : true);

    }

    /**
     * 注册方法的helper
     * 判断用户名是否已经存在
     *
     * @param newUserName
     * @return
     */
    public boolean isUserNameExists(String newUserName) {
        String sql = SqlQueryUtils.buildCheckUserNameQuery(newUserName);
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {

        }
        return resultSet != null;

    }

    /**
     * 在DB查询是否存在符合的数据
     * 有，则登陆成功并获取用户信息
     * 设置为登录状态
     * @param
     * @return
     */
    public boolean signIn(String userName, String userPassword) {
        String sql = SqlQueryUtils.buildUserQuery(userName, userPassword);
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
            isLoggedIn = true;
            userInfo.setUserName(result.getString(1));
            userInfo.setUserPassword(result.getString(2));
            userInfo.setUserEmailAddress(result.getString(3));
        } catch (SQLException e) {

        }
        System.out.println("登录成功！");
        return result != null;

    }

    /**
     * 注销账号并清空用户信息
     * 设置为未登录状态
     */
    public void logOff() {
        userInfo = new UserInfo();

        isLoggedIn = false;
    }

}
