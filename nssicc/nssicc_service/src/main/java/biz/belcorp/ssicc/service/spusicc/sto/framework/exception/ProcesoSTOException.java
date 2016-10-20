package biz.belcorp.ssicc.service.spusicc.sto.framework.exception;

/**
 * Excepcion general de las Interfaces SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class ProcesoSTOException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoError;

	/**
	 * @param message
	 */
	public ProcesoSTOException(String message) {
		super(message);
	}

	/**
	 * @return
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

}
