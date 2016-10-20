package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:gascarza@sigcomt.com">Giovanni Ascarza</a>
 * <p>
 */
public class ProcesoVENLibroVentasDetalleSIIForm extends BaseReporteForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String fechaDesde;
	private String fechaHasta;
	private Date fechaDesdeD;
	private Date fechaHastaD;
	
	private String accion;
	private String formatoExportacion;
	private String tipoReporteAMostrar;
	

	public void reset() {
		this.fechaDesde=this.fechaHasta="";
		this.fechaDesdeD = this.fechaHastaD = null;
		this.accion=this.formatoExportacion=Constants.NUMERO_CERO;
		this.tipoReporteAMostrar="";
	}


	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * @param fechaDesde the fechaDesde to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}


	/**
	 * @param fechaHasta the fechaHasta to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}


	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}


	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}


	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}


	/**
	 * @return the tipoReporteAMostrar
	 */
	public String getTipoReporteAMostrar() {
		return tipoReporteAMostrar;
	}


	/**
	 * @param tipoReporteAMostrar the tipoReporteAMostrar to set
	 */
	public void setTipoReporteAMostrar(String tipoReporteAMostrar) {
		this.tipoReporteAMostrar = tipoReporteAMostrar;
	}


	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}


	/**
	 * @param fechaDesdeD the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}


	/**
	 * @return the fechaHastaD
	 */
	public Date getFechaHastaD() {
		return fechaHastaD;
	}


	/**
	 * @param fechaHastaD the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
}
