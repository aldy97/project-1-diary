package Utils;

import java.sql.Date;

/**
 * SQL工具类
 */
public class SqlQueryUtils {

    private SqlQueryUtils() {

    }

    /**
     * 生成插入语句
     *
     * @param newUserName
     * @param newUserPassword
     * @param newUserEmailAddress
     * @return
     */
    public static String buildInsert(String newUserName, String newUserPassword, String newUserEmailAddress) {
        String query = "Insert into user values('" + newUserName + "','" + newUserPassword + "','" + newUserEmailAddress + "',')";
        return query;
    }

    /**
     * 生成日志查询语句
     *
     * @param userName
     * @return
     */
    public static String buildLogQuery(String userName) {
        String query = "Select * from log from where ( userName = " + userName + " )";
        return query;
    }

    public static String buildLogQueryByDate(String userName, Date date){
        String query = "Select * from log where ( userName = " + userName + " and logDate =" + date + ")";
        return query;
    }

    /**
     * 生成查询用户是否存在
     */
    public static String buildUserQuery(String userName, String userPassword) {
        String query = "Select * from user where" + "name = " + userName + "password = " + userPassword;
        return query;
    }

    /**
     * 生成查询用户名是否已经被注册
     *
     * @param newUserName
     * @return
     */
    public static String buildCheckUserNameQuery(String newUserName) {
        String query = "Select name from user where" + "name = " + newUserName;
        return query;
    }

}
