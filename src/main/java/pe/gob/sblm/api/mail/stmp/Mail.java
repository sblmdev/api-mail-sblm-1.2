package pe.gob.sblm.api.mail.stmp;

import java.util.List;

import javax.mail.MessagingException;

import pe.gob.sblm.api.mail.properties.MessageProperties;
import pe.gob.sblm.api.mail.util.FileAttach;

/**
 * Created by CSERRANOCA on 19/05/2015.
 */

public interface Mail {
	public void send(MessageProperties messageProperties) throws Exception, MessagingException;

	public void send(MessageProperties messageProperties,List<FileAttach> filesAttach) throws Exception, MessagingException;

	public void send(MessageProperties messageProperties, String img_base64)throws Exception, MessagingException;
}