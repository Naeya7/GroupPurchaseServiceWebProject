package common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	
	//접속 객체
    PasswordAuthentication pa;
    
    //보내는이
    public MailAuth() {
        String SenderId = "testhwanny@gmail.com";
        String SenderPasswd = "whtjdghks12" ;
        
        pa = new PasswordAuthentication(SenderId, SenderPasswd);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}
