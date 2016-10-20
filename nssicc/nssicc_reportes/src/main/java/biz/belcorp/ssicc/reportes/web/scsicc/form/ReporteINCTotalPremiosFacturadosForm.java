package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCTotalPremiosFacturadosForm extends BaseReporteForm {

	private static final long serialVersionUID = -1194852536239520855L;

	private String[] codigoRegion;
	
	private String[] numeroConcurso;

	private String codigoPeriodo;

	private String codigoPais;
	
	private String descPais;

	private String descripcionRegion;
	
	private String codTotales;
	
	private String whithFecha;
	
	private String codUnidAdim;

	private Date fechaInicioFacturacionD;

	private Date fechaFinFacturacionD;
	
	private String fechaInicioFacturacion;

	private String fechaFinFacturacion;

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
	 * @return the numeroConcurso
	 */
	public String[] getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String[] numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
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
	 * @return the descPais
	 */
	public String getDescPais() {
		return descPais;
	}

	/**
	 * @param descPais the descPais to set
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return the codTotales
	 */
	public String getCodTotales() {
		return codTotales;
	}

	/**
	 * @param codTotales the codTotales to set
	 */
	public void setCodTotales(String codTotales) {
		this.codTotales = codTotales;
	}

	/**
	 * @return the whithFecha
	 */
	public String getWhithFecha() {
		return whithFecha;
	}

	/**
	 * @param whithFecha the whithFecha to set
	 */
	public void setWhithFecha(String whithFecha) {
		this.whithFecha = whithFecha;
	}

	/**
	 * @return the codUnidAdim
	 */
	public String getCodUnidAdim() {
		return codUnidAdim;
	}

	/**
	 * @param codUnidAdim the codUnidAdim to set
	 */
	public void setCodUnidAdim(String codUnidAdim) {
		this.codUnidAdim = codUnidAdim;
	}

	/**
	 * @return the fechaInicioFacturacionD
	 */
	public Date getFechaInicioFacturacionD() {
		return fechaInicioFacturacionD;
	}

	/**
	 * @param fechaInicioFacturacionD the fechaInicioFacturacionD to set
	 */
	public void setFechaInicioFacturacionD(Date fechaInicioFacturacionD) {
		this.fechaInicioFacturacionD = fechaInicioFacturacionD;
	}

	/**
	 * @return the fechaFinFacturacionD
	 */
	public Date getFechaFinFacturacionD() {
		return fechaFinFacturacionD;
	}

	/**
	 * @param fechaFinFacturacionD the fechaFinFacturacionD to set
	 */
	public void setFechaFinFacturacionD(Date fechaFinFacturacionD) {
		this.fechaFinFacturacionD = fechaFinFacturacionD;
	}

	/**
	 * @return the fechaInicioFacturacion
	 */
	public String getFechaInicioFacturacion() {
		return fechaInicioFacturacion;
	}

	/**
	 * @param fechaInicioFacturacion the fechaInicioFacturacion to set
	 */
	public void setFechaInicioFacturacion(String fechaInicioFacturacion) {
		this.fechaInicioFacturacion = fechaInicioFacturacion;
	}

	/**
	 * @return the fechaFinFacturacion
	 */
	public String getFechaFinFacturacion() {
		return fechaFinFacturacion;
	}

	/**
	 * @param fechaFinFacturacion the fechaFinFacturacion to set
	 */
	public void setFechaFinFacturacion(String fechaFinFacturacion) {
		this.fechaFinFacturacion = fechaFinFacturacion;
	}

	
	

}