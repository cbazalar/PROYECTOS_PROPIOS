package biz.belcorp.ssicc.service.sisicc.framework.exception;

/**
 * Excepcion general de las Interfaces SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class InterfazException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7971128318954856719L;

	private String numeroLote;

	private String codigoError;

	private int registrosProcesados;

	public InterfazException(String message) {
		super(message);
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public int getRegistrosProcesados() {
		return registrosProcesados;
	}

	public void setRegistrosProcesados(int registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}
}
