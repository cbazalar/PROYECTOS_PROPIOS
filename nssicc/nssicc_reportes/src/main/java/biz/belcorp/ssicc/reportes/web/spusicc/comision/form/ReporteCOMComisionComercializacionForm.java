package biz.belcorp.ssicc.reportes.web.spusicc.comision.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a> 
 */

public class ReporteCOMComisionComercializacionForm extends BaseReporteForm
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2119885107202580152L;

	private String codigoPais;
	
	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codigoComision;
	
	private String descripcionComision;

	private String fechaDesde;
	
	private String fechaHasta;
	
	private String tipoComision;

	private Date calendarDesde;
	
	private Date calendarHasta;

	/**
	 * Valores por default
	 */
	public ReporteCOMComisionComercializacionForm(){
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));
		this.calendarDesde = new Date();
		this.calendarHasta = new Date();
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
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde The fechaDesde to set.
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.	 
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the descripcionComision.
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision The descripcionComision to set.
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}

	/**
	 * @return Returns the tipoComision.
	 */
	public String getTipoComision() {
		return tipoComision;
	}

	/**
	 * @param tipoComision The tipoComision to set.
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the calendarDesde
	 */
	public Date getCalendarDesde() {
		return calendarDesde;
	}

	/**
	 * @param calendarDesde the calendarDesde to set
	 */
	public void setCalendarDesde(Date calendarDesde) {
		if(calendarDesde!=null && calendarDesde.toString().length()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaDesde = sdf.format(calendarDesde);			
		}
		this.calendarDesde = calendarDesde;
	}

	/**
	 * @return the calendarHasta
	 */
	public Date getCalendarHasta() {
		return calendarHasta;
	}

	/**
	 * @param calendarHasta the calendarHasta to set
	 */
	public void setCalendarHasta(Date calendarHasta) {
		if(calendarHasta!=null && calendarHasta.toString().length()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaHasta = sdf.format(calendarHasta);			
		}
		this.calendarHasta = calendarHasta;
	}

}
