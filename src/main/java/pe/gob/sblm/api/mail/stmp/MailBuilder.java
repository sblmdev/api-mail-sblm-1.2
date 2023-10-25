package pe.gob.sblm.api.mail.stmp;

import pe.gob.sblm.api.mail.exception.MailException;
import pe.gob.sblm.api.mail.properties.MailProperties;
import pe.gob.sblm.api.mail.stmp.defaults.MailImpl;
import pe.gob.sblm.api.mail.util.Constantes;

import javax.mail.Session;

import java.util.Properties;

/**
 * Created by CSERRANOCA on 19/05/2015.
 */
public class MailBuilder {

	private Properties propiedades = null;
	private Session session = null;

	public Mail build(MailProperties mailProperties) throws MailException {
		Mail mail = null;
		propiedades = new Properties();
		try {
			propiedades.setProperty(Constantes.RECURSO_MAIL_HOST, mailProperties.getSmtpIp());
			propiedades.setProperty(Constantes.RECURSO_MAIL_PROTOCOLO, mailProperties.getSmtpProtocolo());
			propiedades.setProperty(Constantes.RECURSO_MAIL_PORT, mailProperties.getSmtpPuerto());
			propiedades.setProperty(Constantes.RECURSO_MAIL_LOCALHOST, mailProperties.getSmtpLocalHost());
			propiedades.setProperty(Constantes.RECURSO_MAIL_EMISOR, mailProperties.getSmtpCorreoEmisor());
			propiedades.setProperty(Constantes.RECURSO_MAIL_AUTH, mailProperties.getSmtpAuth());
			propiedades.setProperty(Constantes.RECURSO_MAIL_TLS_ENABLE, mailProperties.getSmtpTlsEnable());
			
			session = Session.getDefaultInstance(propiedades, null);
			mail = new MailImpl(session, mailProperties.getSmtpCorreoEmisor());
			return mail;
		} catch (Exception ex) {
			throw new MailException(ex.getMessage(), ex.getCause());
		}
	}
	
	public Mail build(MailProperties mailProperties, String emisorNombre) throws MailException {
		Mail mail = null;
		propiedades = new Properties();
		try {
			propiedades.setProperty(Constantes.RECURSO_MAIL_HOST, mailProperties.getSmtpIp());
			propiedades.setProperty(Constantes.RECURSO_MAIL_PROTOCOLO, mailProperties.getSmtpProtocolo());
			propiedades.setProperty(Constantes.RECURSO_MAIL_PORT, mailProperties.getSmtpPuerto());
			propiedades.setProperty(Constantes.RECURSO_MAIL_LOCALHOST, mailProperties.getSmtpLocalHost());
			propiedades.setProperty(Constantes.RECURSO_MAIL_EMISOR, mailProperties.getSmtpCorreoEmisor());
			propiedades.setProperty(Constantes.RECURSO_MAIL_AUTH, mailProperties.getSmtpAuth());
			propiedades.setProperty(Constantes.RECURSO_MAIL_TLS_ENABLE, mailProperties.getSmtpTlsEnable());
			session = Session.getDefaultInstance(propiedades, null);
			mail = new MailImpl(session, mailProperties.getSmtpCorreoEmisor(), emisorNombre);
			return mail;
		} catch (Exception ex) {
			throw new MailException(ex.getMessage(), ex.getCause());
		}
	}

	public Mail build(MailProperties mailProperties,String user,String password) throws MailException {
		Mail mail = null;
		propiedades = new Properties();
		try {
			propiedades.setProperty(Constantes.RECURSO_MAIL_HOST, mailProperties.getSmtpIp());
			propiedades.setProperty(Constantes.RECURSO_MAIL_TLS_ENABLE, mailProperties.getSmtpTlsEnable());
			propiedades.setProperty(Constantes.RECURSO_MAIL_PORT, mailProperties.getSmtpPuerto());
			propiedades.setProperty(Constantes.RECURSO_MAIL_AUTH, mailProperties.getSmtpAuth());
//			
			propiedades.setProperty(Constantes.RECURSO_MAIL_PROTOCOLO, mailProperties.getSmtpProtocolo());
//			propiedades.setProperty(Constantes.RECURSO_MAIL_LOCALHOST, mailProperties.getSmtpLocalHost());
			propiedades.setProperty(Constantes.RECURSO_MAIL_EMISOR, mailProperties.getSmtpCorreoEmisor());
//			

			
			session = Session.getDefaultInstance(propiedades, null);
			mail = new MailImpl(session, mailProperties.getSmtpCorreoEmisor(), user,password);
			return mail;
		} catch (Exception ex) {
			throw new MailException(ex.getMessage(), ex.getCause());
		}
	}
}