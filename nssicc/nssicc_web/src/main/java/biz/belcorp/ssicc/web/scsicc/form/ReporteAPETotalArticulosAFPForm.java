package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPETotalArticulosAFPForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoLote;
	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codsublinea;

	private String codigoPeriodo;
	
	private String codigoCentro;
	
	private String codigoLinea;
	
	private String codigoTipo;
	
	private String fuente;
	
	private String orden;

	private String fechaFacturacion;
	
	private Date fechaFacturacionDt;
	/**
	 * @return the codigoTipo
	 */
	
	public String getCodigoTipo() {
		return this.codigoTipo;
	}

	/**
	 * @return
	 */
	public Date getFechaFacturacionDt() {
		return fechaFacturacionDt;
	}

	public void setFechaFacturacionDt(Date fechaFacturacionDt) {
		this.fechaFacturacionDt = fechaFacturacionDt;
	}

	/**
	 * @param codigoTipo the codigoTipo to set
	 */
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	/**
	 * @return the fuente
	 */
	public String getFuente() {
		return this.fuente;
	}

	/**
	 * @param fuente the fuente to set
	 */
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	/**
	 * @return the orden
	 */
	public String getOrden() {
		return this.orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
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
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

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
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPeriodo(java.lang.String)
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoLote() {
		return codigoLote;
	}

	/**
	 * @param codigoLote
	 */
	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	/**
	 * @return
	 */
	public String getCodsublinea() {
		return codsublinea;
	}

	/**
	 * @param codsublinea
	 */
	public void setCodsublinea(String codsublinea) {
		this.codsublinea = codsublinea;
	}
}