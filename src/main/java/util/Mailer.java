package util;

import java.util.Properties;

public class Mailer {
    private String emailTo;
    private String emailFrom;
    private String host;
    private Properties props;


    public void setProps() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "");
    }
}
