/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

/**
 * @author PEEXTLLIZANA
 *
 */
public class Nivel implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	String codigoPais;
	String codigoNivel;
	String descripcion;
	
	
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}
	/**
	 * @param codigoNivel the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
