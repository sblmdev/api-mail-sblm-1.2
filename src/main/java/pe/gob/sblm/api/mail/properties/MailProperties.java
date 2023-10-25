package pe.gob.sblm.api.mail.properties;

import java.io.Serializable;

/**
 * Created by CSERRANOCA on 19/05/2015.
 */
public class MailProperties implements Serializable {

	private static final long serialVersionUID = -2577837066048715118L;

	private String smtpIp;
	private String smtpProtocolo;
	private String smtpPuerto;
	private String smtpCorreoEmisor;
	private String smtpLocalHost;
	private String smtpAuth;
	private String smtpTlsEnable;
	
	public String getSmtpIp() {
		return smtpIp;
	}

	public void setSmtpIp(String smtpIp) {
		this.smtpIp = smtpIp;
	}

	public String getSmtpProtocolo() {
		return smtpProtocolo;
	}

	public void setSmtpProtocolo(String smtpProtocolo) {
		this.smtpProtocolo = smtpProtocolo;
	}

	public String getSmtpPuerto() {
		return smtpPuerto;
	}

	public void setSmtpPuerto(String smtpPuerto) {
		this.smtpPuerto = smtpPuerto;
	}

	public String getSmtpCorreoEmisor() {
		return smtpCorreoEmisor;
	}

	public void setSmtpCorreoEmisor(String smtpCorreoEmisor) {
		this.smtpCorreoEmisor = smtpCorreoEmisor;
	}

	public String getSmtpLocalHost() {
		return smtpLocalHost;
	}

	public void setSmtpLocalHost(String smtpLocalHost) {
		this.smtpLocalHost = smtpLocalHost;
	}

	/**
	 * @return the smtpAuth
	 */
	public String getSmtpAuth() {
		return smtpAuth;
	}

	/**
	 * @param smtpAuth the smtpAuth to set
	 */
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	/**
	 * @return the smtpTlsEnable
	 */
	public String getSmtpTlsEnable() {
		return smtpTlsEnable;
	}

	/**
	 * @param smtpTlsEnable the smtpTlsEnable to set
	 */
	public void setSmtpTlsEnable(String smtpTlsEnable) {
		this.smtpTlsEnable = smtpTlsEnable;
	}

	
}