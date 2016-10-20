package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicolás López</a>
 * 
 */
public class ReporteAPEMapaAnaquelBalanceoDiarioForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	
	private String codigoPais;

	private String codigoCentro;
	
	private String codigoLinea;
	
	private String mapa;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;

	private Date fechaFacturacion;
	
	private String tipoReporte;
	
	/**
	 * @return tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCentro
	 */
	public String getCodigoCentro() {
		return this.codigoCentro;
	}

	/**
	 * @param codigoCentro the codigoCentro to set
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	/**
	 * @return the codigoLinea
	 */
	public String getCodigoLinea() {
		return this.codigoLinea;
	}

	/**
	 * @param codigoLinea the codigoLinea to set
	 */
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	/**
	 * @return the mapa
	 */
	public String getMapa() {
		return this.mapa;
	}

	/**
	 * @param mapa the mapa to set
	 */
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	
	
}
