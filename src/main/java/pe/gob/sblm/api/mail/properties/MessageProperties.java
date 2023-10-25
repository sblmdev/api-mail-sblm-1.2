package pe.gob.sblm.api.mail.properties;

import java.io.Serializable;

/**
 * Created by CSERRANOCA on 19/05/2015.
 */
public class MessageProperties implements Serializable {

	private static final long serialVersionUID = -9008912610573905472L;

	/***
	 * Es el nombre del remitente
	 */
	private String remitente;
	/***
	 * Es el asunto del correo
	 */
	private String asunto;
	/***
	 * Es el contenido del correo
	 */
	private String contenido;
	/**
	 * Aqui van los correos separados por comas. puede ir un solo correo Ej.
	 * cserra@pj.gob.pe,mcasique@pj.gob.pe
	 */
	private String destinatarios;

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	@Override
	public String toString() {
		return "MessageProperties{" + "asunto='" + asunto + '\'' + ", contenido='" + contenido + '\'' + ", destinatarios='" + destinatarios + '\'' + '}';
	}
}