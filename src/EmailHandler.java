import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

public class EmailHandler {
    private String sender;
    private int interval;
    private File file;
    private Properties properties;
    private String host;
    private Session session;
    private String messageText;
	int i = 0;
    
    public EmailHandler(String email, String inverval, String message, File file) {
	sender = email;
	interval = Integer.parseInt(inverval);
	this.messageText = message;
	this.file = file;
	properties = System.getProperties();
	//local host for now until we add the system ip in;
	host = "localhost";
	properties.setProperty("mail.smtp.host", host);
	session = Session.getDefaultInstance(properties);
    }
    public void sendEmail() {
    	while(i < MainApp.emailList.size()) {
    		new Timer().scheduleAtFixedRate(new TimerTask() {
    			public void run() {
    				try {	
    					MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(sender));
						//this is the sender now we need an inverval looping the sender
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(MainApp.emailList.get(i)));
						message.setSubject("From K3LLYS mail client");
						message.setText(messageText);

						//send the message
						Transport.send(message);
    				} catch(MessagingException mex) {
    					mex.printStackTrace();
    				}
    			}
    		},0, 10000);
    	}
	i++;
    }
    public String getCurrentEmail() {
    	return MainApp.emailList.get(i);
    }

    
    
}
