package biz.belcorp.ssicc.service.util;

/**
 * @deprecated Usar InterfazException
 */
public class ExcepcionSistema extends Exception {
	private String mensajeError;
	private String codigoError;
	private Exception excepcion;
	
	public ExcepcionSistema() {
		super();
		this.mensajeError = "";
		this.codigoError = "";
	}	
		
	public ExcepcionSistema(String mensaje) {
		super(mensaje);
		this.mensajeError = mensaje;
			
	}
	
	/**
	 * @return Returns the codigoError.
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError The codigoError to set.
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return Returns the mensajeError.
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError The mensajeError to set.
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return Returns the excepcion.
	 */
	public Exception getExcepcion() {
		return excepcion;
	}

	/**
	 * @param excepcion The excepcion to set.
	 */
	public void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}
	
	

}
