package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class NuevaConstanciaProgramaPuntos extends AuditableBaseObject implements Serializable {

	private String codigoPais;
	private String codigoProgramaConstancia;
	private String periodosExigidos;
	private String pedidosExigidos;
	private String puntosAbonar;
	
	public NuevaConstanciaProgramaPuntos() {
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
	 * @return the codigoProgramaConstancia
	 */
	public String getCodigoProgramaConstancia() {
		return codigoProgramaConstancia;
	}
	/**
	 * @param codigoProgramaConstancia the codigoProgramaConstancia to set
	 */
	public void setCodigoProgramaConstancia(String codigoProgramaConstancia) {
		this.codigoProgramaConstancia = codigoProgramaConstancia;
	}
	/**
	 * @return the periodosExigidos
	 */
	public String getPeriodosExigidos() {
		return periodosExigidos;
	}
	/**
	 * @param periodosExigidos the periodosExigidos to set
	 */
	public void setPeriodosExigidos(String periodosExigidos) {
		this.periodosExigidos = periodosExigidos;
	}
	/**
	 * @return the pedidosExigidos
	 */
	public String getPedidosExigidos() {
		return pedidosExigidos;
	}
	/**
	 * @param pedidosExigidos the pedidosExigidos to set
	 */
	public void setPedidosExigidos(String pedidosExigidos) {
		this.pedidosExigidos = pedidosExigidos;
	}
	/**
	 * @return the puntosAbonar
	 */
	public String getPuntosAbonar() {
		return puntosAbonar;
	}
	/**
	 * @param puntosAbonar the puntosAbonar to set
	 */
	public void setPuntosAbonar(String puntosAbonar) {
		this.puntosAbonar = puntosAbonar;
	}
	

}
