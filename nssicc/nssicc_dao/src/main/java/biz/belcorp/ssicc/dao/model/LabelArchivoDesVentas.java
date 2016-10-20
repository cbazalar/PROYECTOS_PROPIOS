/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

/**
 * @author Danny Amaro
 *
 */
public class LabelArchivoDesVentas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7947458762413926914L;
	
	public String codigoCliente;
	public String indicadorValido;
	public String monto;
	
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}
	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
}
