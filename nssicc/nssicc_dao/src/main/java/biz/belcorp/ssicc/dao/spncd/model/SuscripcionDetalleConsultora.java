/**
 * 
 */
package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 *
 */
public class SuscripcionDetalleConsultora extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPrograma;
	
	private String codigoConsultora;
	
	private String codigoNivel;
	
	private String codigoCupon;
	
	private String codigoVenta;
	
	private String descripcionProducto;
	
	private String valorDemandada;
	
	private String indicadorUtilidad;
	
	private String campanhaUtilidad;
	
	private String codigoUsuario;
		
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return the codigoCupon
	 */
	public String getCodigoCupon() {
		return codigoCupon;
	}

	/**
	 * @param codigoCupon the codigoCupon to set
	 */
	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the valorDemandada
	 */
	public String getValorDemandada() {
		return valorDemandada;
	}

	/**
	 * @param valorDemandada the valorDemandada to set
	 */
	public void setValorDemandada(String valorDemandada) {
		this.valorDemandada = valorDemandada;
	}

	/**
	 * @return the indicadorUtilidad
	 */
	public String getIndicadorUtilidad() {
		return indicadorUtilidad;
	}

	/**
	 * @param indicadorUtilidad the indicadorUtilidad to set
	 */
	public void setIndicadorUtilidad(String indicadorUtilidad) {
		this.indicadorUtilidad = indicadorUtilidad;
	}

	/**
	 * @return the campanhaUtilidad
	 */
	public String getCampanhaUtilidad() {
		return campanhaUtilidad;
	}

	/**
	 * @param campanhaUtilidad the campanhaUtilidad to set
	 */
	public void setCampanhaUtilidad(String campanhaUtilidad) {
		this.campanhaUtilidad = campanhaUtilidad;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
