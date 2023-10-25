package pe.gob.sblm.api.mail.type;

/**
 * Created by CSERRANOCA on 08/09/2015.
 */
public enum ConfiguracionSMTPType {
																		TBL_CONF_SMTP("TBL_CONF_SMTP"),
																		SMTP_IP("SMTP_IP"),
																		SMTP_PROTOCOLO("SMTP_PROTOCOLO"),
																		SMTP_PUERTO("SMTP_PUERTO"),
																		SMTP_CORREO_EMISOR("SMTP_CORREO_EMISOR"),
																		SMTP_LOCALHOST("SMTP_LOCALHOST");

	private String value;

	private ConfiguracionSMTPType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}