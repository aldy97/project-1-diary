package Functions;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;

public class EmailSender implements Function {
    private static final String SENDER_EMAIL_ADDRESS = "fengxiong34@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "xf123321";
    private static final String EMAIL_SMTP_HOST = "smtp.gmail.com";
    private static final String d_port = "465";

    private Properties properties;
    private Session session;
    private Date sendingTime;

    public EmailSender() {

    }

    public Session getSession() {
        return session;
    }

    public static String getSenderEmailAddress() {
        return SENDER_EMAIL_ADDRESS;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setSendingTime(Calendar sendingTime) {
        this.sendingTime = (Date) sendingTime.getTime();
    }

    public Date getSendingTime() {
        return sendingTime;
    }

    public void sendEmailTo(String receiverEmailAddress) {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", EMAIL_SMTP_HOST);
        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.port", d_port);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", d_port);

        session = Session.getInstance(properties);
        session.setDebug(true);

        try {
            Transport transport;
            MimeMessage message = createMimeMessage(session, SENDER_EMAIL_ADDRESS, receiverEmailAddress);
            transport = session.getTransport();

            transport.connect(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        } catch (Exception e) {

        }
    }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "Feng Xiong", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
        message.setSubject("Test Subject", "UTF-8");
        message.setContent("Test Content", "text/html;charset=UTF-8");
        message.setSentDate(new java.util.Date());
        message.saveChanges();

        return message;
    }

}
