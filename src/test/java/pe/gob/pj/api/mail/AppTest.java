package pe.gob.pj.api.mail;

//import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pe.gob.sblm.api.commons.utility.CorreoUtil;
import pe.gob.sblm.api.mail.exception.MailException;
import pe.gob.sblm.api.mail.properties.MailProperties;
import pe.gob.sblm.api.mail.properties.MessageProperties;
import pe.gob.sblm.api.mail.stmp.Mail;
import pe.gob.sblm.api.mail.stmp.MailBuilder;
import pe.gob.sblm.api.mail.util.FileAttach;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void sendMail() {
		System.out.println("Enviando Mensaje...");
		MailProperties mailProperties = new MailProperties();

		//mailProperties.setSmtpIp("mail.sblm.gob.pe");
		mailProperties.setSmtpIp("mail.beneficenciadelima.org");
		mailProperties.setSmtpAuth("false");
		mailProperties.setSmtpTlsEnable("false");
		mailProperties.setSmtpProtocolo("smtp");
		mailProperties.setSmtpPuerto("26");
//		mailProperties.setSmtpLocalHost("sinoe.pj.gob.pe");
		mailProperties.setSmtpCorreoEmisor("sgi@sblm.gob.pe");
		
		try {
			MailBuilder mailBuilder = new MailBuilder();
			Mail mail;
			//mail = mailBuilder.build(mailProperties,"cvallejos@sblm.gob.pe","98765c");
			mail = mailBuilder.build(mailProperties,"sistemasgi@sblm.gob.pe","beneficencia2019");

			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setAsunto("Consulta raul ventocilla");
			messageProperties.setContenido(CorreoUtil.obtenerPlantillaContenidoCorreoNotificacion("Panzon","mesaje"));
			messageProperties.setDestinatarios("franco.emp@gmail.com");
			
			List<FileAttach> l= new ArrayList<FileAttach>();
			FileAttach fileAttach= new FileAttach();
			fileAttach.setFilename("11.pdf");
			fileAttach.setStream(obtenerByteArray());
			
			l.add(fileAttach);
			
			FileAttach fileAttach2= new FileAttach();
			fileAttach2.setFilename("22.pdf");
			fileAttach2.setStream(obtenerByteArray());
			l.add(fileAttach2);
			
			mail.send(messageProperties,l);
//			mail.send(messageProperties, Constantes.IMG_HEADER_MAIL_BASE64);

			
			//Enviar 
//			 mail.send(messageProperties);
			 
			 System.out.println("Mensaje enviado con exito");
			 
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public byte[] obtenerByteArray(){
		InputStream stream = null;

		try {
		stream = new FileInputStream("C:/111.pdf");

		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] content = new byte[ 2048 ];
		int bytesRead = -1;
		try {
		while( ( bytesRead = stream.read( content ) ) != -1 ) {
		baos.write( content, 0, bytesRead );
		}
		} catch (IOException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		}

		try {
		stream.close();
		} catch (IOException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		}
		return baos.toByteArray();
		
	}
}