package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas </a>
 * 
 */
public class ReporteAPEDistribucionCdrsFacturacionRealForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	
	private String codigoPais;

	private String codigoPeriodo;
	
	private Date fechaFacturacionInicio;
	
	private Date fechaFacturacionFin;
	
	private String[] codigoRegion;
	
	private String[] codigoZona;
	
	private String centroAcopio;
	
	private String companhiaTransporte;
	
	private boolean mostrarBotonExcel;


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
	

	/**
	 * @return the fechaFacturacionInicio
	 */
	public Date getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}

	/**
	 * @param fechaFacturacionInicio the fechaFacturacionInicio to set
	 */
	public void setFechaFacturacionInicio(Date fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}

	/**
	 * @return the fechaFacturacionFin
	 */
	public Date getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}

	/**
	 * @param fechaFacturacionFin the fechaFacturacionFin to set
	 */
	public void setFechaFacturacionFin(Date fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the centroAcopio
	 */
	public String getCentroAcopio() {
		return centroAcopio;
	}

	/**
	 * @param centroAcopio the centroAcopio to set
	 */
	public void setCentroAcopio(String centroAcopio) {
		this.centroAcopio = centroAcopio;
	}

	/**
	 * @return the companhiaTransporte
	 */
	public String getCompanhiaTransporte() {
		return companhiaTransporte;
	}

	/**
	 * @param companhiaTransporte the companhiaTransporte to set
	 */
	public void setCompanhiaTransporte(String companhiaTransporte) {
		this.companhiaTransporte = companhiaTransporte;
	}

	/**
	 * @return the mostrarBotonExcel
	 */
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	/**
	 * @param mostrarBotonExcel the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}
	
	
	

	
}
