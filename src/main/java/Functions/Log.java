package Functions;

import java.sql.Date;

/**
 * POJO: 日志信息
 */
public class Log {
    String log;
    Date logDate;

    public String getDiary() {
        return log;
    }

    public void setDiary(String diary) {
        this.log = diary;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
