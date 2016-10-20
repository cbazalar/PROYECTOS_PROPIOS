/*
 * Created on 23/12/2015 11:15:10 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentoRMSWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="DescuentoRMSWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 * 
 * 
 */
public class DescuentoRMSWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoGrupoDescuento;
	private String descripcionGrupoDescuento;
	private String codigoRangoDescuento;
	private Double valorImporteMaximo;
	private Double porcentajeDescuento;
	
	/**
	 * @return the valorImporteMaximo
	 */
	public Double getValorImporteMaximo() {
		return valorImporteMaximo;
	}
	/**
	 * @param valorImporteMaximo the valorImporteMaximo to set
	 */
	public void setValorImporteMaximo(Double valorImporteMaximo) {
		this.valorImporteMaximo = valorImporteMaximo;
	}
	/**
	 * @return the porcentajeDescuento
	 */
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	/**
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	/**
	 * @return the codigoGrupoDescuento
	 */
	public String getCodigoGrupoDescuento() {
		return codigoGrupoDescuento;
	}
	/**
	 * @param codigoGrupoDescuento the codigoGrupoDescuento to set
	 */
	public void setCodigoGrupoDescuento(String codigoGrupoDescuento) {
		this.codigoGrupoDescuento = codigoGrupoDescuento;
	}
	/**
	 * @return the descripcionGrupoDescuento
	 */
	public String getDescripcionGrupoDescuento() {
		return descripcionGrupoDescuento;
	}
	/**
	 * @param descripcionGrupoDescuento the descripcionGrupoDescuento to set
	 */
	public void setDescripcionGrupoDescuento(String descripcionGrupoDescuento) {
		this.descripcionGrupoDescuento = descripcionGrupoDescuento;
	}
	/**
	 * @return the codigoRangoDescuento
	 */
	public String getCodigoRangoDescuento() {
		return codigoRangoDescuento;
	}
	/**
	 * @param codigoRangoDescuento the codigoRangoDescuento to set
	 */
	public void setCodigoRangoDescuento(String codigoRangoDescuento) {
		this.codigoRangoDescuento = codigoRangoDescuento;
	}	
	
	
}