package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextcroman@belcorp.biz
 * 
 */
public class DetallesCDRDigitadas extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String operacion;               
    private String codigoVentaCambia;           
    private Integer cantidadCambia;             
    private String motivo; 
    private String codigoVentaDesea;            
    private Integer cantidadDesea;            
   
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the cantidadCambia.
	 */
	public Integer getCantidadCambia() {
		return cantidadCambia;
	}
	/**
	 * @param cantidadCambia The cantidadCambia to set.
	 */
	public void setCantidadCambia(Integer cantidadCambia) {
		this.cantidadCambia = cantidadCambia;
	}
	/**
	 * @return Returns the cantidadDesea.
	 */
	public Integer getCantidadDesea() {
		return cantidadDesea;
	}
	/**
	 * @param cantidadDesea The cantidadDesea to set.
	 */
	public void setCantidadDesea(Integer cantidadDesea) {
		this.cantidadDesea = cantidadDesea;
	}
	/**
	 * @return Returns the codigoVentaCambia.
	 */
	public String getCodigoVentaCambia() {
		return codigoVentaCambia;
	}
	/**
	 * @param codigoVentaCambia The codigoVentaCambia to set.
	 */
	public void setCodigoVentaCambia(String codigoVentaCambia) {
		this.codigoVentaCambia = codigoVentaCambia;
	}
	/**
	 * @return Returns the codigoVentaDesea.
	 */
	public String getCodigoVentaDesea() {
		return codigoVentaDesea;
	}
	/**
	 * @param codigoVentaDesea The codigoVentaDesea to set.
	 */
	public void setCodigoVentaDesea(String codigoVentaDesea) {
		this.codigoVentaDesea = codigoVentaDesea;
	}
	/**
	 * @return Returns the motivo.
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo The motivo to set.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return Returns the operacion.
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion The operacion to set.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetallesCDRDigitadas [operacion=" + operacion
				+ ", codigoVentaCambia=" + codigoVentaCambia
				+ ", cantidadCambia=" + cantidadCambia + ", motivo=" + motivo
				+ ", codigoVentaDesea=" + codigoVentaDesea + ", cantidadDesea="
				+ cantidadDesea + "]";
	}
}
