package pe.gob.sblm.api.mail.stmp.defaults;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.codec.binary.Base64;

import pe.gob.sblm.api.mail.exception.MailException;
import pe.gob.sblm.api.mail.properties.MessageProperties;
import pe.gob.sblm.api.mail.stmp.Mail;
import pe.gob.sblm.api.mail.util.FileAttach;


//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Created by CSERRANOCA on 19/05/2015.
 */
public class MailImpl implements Mail {

	private  Session session;
	private  MimeMessage message;
	private  String emisor;
	private  String emisorNombre;
	private  String user;
	private  String password;
	

	public MailImpl(Session session, String emisor) {
		this.emisor = emisor;
		this.session = session;
		this.emisorNombre = ".";
		this.message = new MimeMessage(this.session);
	}

	public MailImpl(Session session, String emisor, String emisorNombre) {
		this.emisor = emisor;
		this.session = session;
		this.emisorNombre = emisorNombre;
		this.message = new MimeMessage(this.session);
	}

	public MailImpl(Session session, String emisor,String user,String password) {
		this.emisor = emisor;
		this.session = session;
		this.emisorNombre = ".";
		this.user = user;
		this.password = password;
		this.message = new MimeMessage(this.session);
	}
	
	@Override
	public void send(MessageProperties messageProperties) throws Exception, MessagingException {
		 try {
				String destinatarios = messageProperties.getDestinatarios();
				//message.setSubject(messageProperties.getAsunto());
				message.setSubject(messageProperties.getAsunto(), "utf-8");
				message.setContent(messageProperties.getContenido(), "text/html; charset=utf-8");
				if (emisorNombre.trim().equals(".")) {
					message.setFrom(new InternetAddress(this.emisor));
				} else
					message.setFrom(new InternetAddress(this.emisor, this.emisorNombre));
				if (destinatarios.indexOf(',') > 0) {
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));
				} else {
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios));
				}
		
				Transport t=session.getTransport("smtp");
				t.connect(user, password);
				t.sendMessage(message,message.getAllRecipients());
				t.close();
	 	} catch (Exception e){
	 		    e.printStackTrace();
	 			throw new Exception(e.getMessage(), e.getCause());
		 }

	}
	@Override
	public void send(MessageProperties messageProperties,String img_base64) throws Exception, MessagingException {
		 try {
			 
			String destinatarios = messageProperties.getDestinatarios();
			message.setSubject(messageProperties.getAsunto());
			
			 // This mail has 2 part, the BODY and the embedded image
	        MimeMultipart multipart = new MimeMultipart("related");
			
	        // first part (the html)
	        BodyPart messageBodyPart = new MimeBodyPart();
	        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
	        messageBodyPart.setContent(htmlText, "text/html");
	        // add it
	        multipart.addBodyPart(messageBodyPart);
	        
	        // second part (the image)
	        messageBodyPart = new MimeBodyPart();
	        
	        //Lectura de imagen
	        /**
	        DataSource fds = new FileDataSource(MailImpl.class.getClassLoader().getResource("logo-sblm-nuevo.jpg").getPath());
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID", "<image>");
	        **/
	        
	        byte[] img_header = Base64.decodeBase64(img_base64.getBytes());
	        DataSource fds = new ByteArrayDataSource(img_header, "image/*");
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID", "<image>");
			
	        // add image to the multipart
	        multipart.addBodyPart(messageBodyPart);
	
	        // put everything together
	        message.setContent(multipart);
			
			if (emisorNombre.trim().equals(".")) {
				message.setFrom(new InternetAddress(this.emisor));
			} else
				message.setFrom(new InternetAddress(this.emisor, this.emisorNombre));
			if (destinatarios.indexOf(',') > 0) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));
			} else {
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios));
			}

		
			Transport t=session.getTransport("smtp");
			t.connect(user, password);
			t.sendMessage(message,message.getAllRecipients());
		 } catch (Exception e){
		 throw new Exception(e.getMessage(), e.getCause());
		 }
	}

		
	private void addAttachment(Multipart multipart, FileAttach fileAttach) throws MessagingException {
		
		BodyPart messageBodyPart = new MimeBodyPart();
		
		ByteArrayDataSource ds = new ByteArrayDataSource(fileAttach.getStream(), "application/pdf"); 
		
		messageBodyPart.setDataHandler(new DataHandler(ds));
		messageBodyPart.setFileName(fileAttach.getFilename());
		multipart.addBodyPart(messageBodyPart);
	}

	@Override
	public void send(MessageProperties messageProperties, List<FileAttach> filesAttach) throws Exception {
		 try {
			String destinatarios = messageProperties.getDestinatarios();
			message.setSubject(messageProperties.getAsunto(), "utf-8");
			MimeMultipart multipart = new MimeMultipart();
			BodyPart texto = new MimeBodyPart();
			texto.setContent(messageProperties.getContenido(), "text/html; charset=UTF-8");
			multipart.addBodyPart(texto);
			
			if (emisorNombre.trim().equals(".")) {
				message.setFrom(new InternetAddress(this.emisor));//sgi@sblm.gob.pe
			} else
				message.setFrom(new InternetAddress(this.emisor, this.emisorNombre));
			if (destinatarios.indexOf(',') > 0) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));
			} else {
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios));
			}
			for (FileAttach fileAttach : filesAttach) {
				addAttachment(multipart, fileAttach);
			}
								System.out.println(multipart.getCount());
			message.setContent(multipart);
			message.setSentDate(new Date());
			
			Transport t=session.getTransport("smtp");
			t.connect(user, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
		 } catch (MessagingException e) {
			 e.printStackTrace();
		 throw new MailException(e.getMessage(), e.getCause());
		 } catch (Exception e){
			 e.printStackTrace();
		 throw new MailException(e.getMessage(), e.getCause());
		 }
	}

}