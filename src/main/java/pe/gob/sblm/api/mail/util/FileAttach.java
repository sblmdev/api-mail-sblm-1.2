package pe.gob.sblm.api.mail.util;

public class FileAttach {
	
	private String filename;
	private byte[] stream;
	
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the stream
	 */
	public byte[] getStream() {
		return stream;
	}
	/**
	 * @param stream the stream to set
	 */
	public void setStream(byte[] stream) {
		this.stream = stream;
	}
}
