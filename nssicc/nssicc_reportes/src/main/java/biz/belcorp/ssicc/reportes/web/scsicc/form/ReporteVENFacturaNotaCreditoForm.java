package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/** 
 * Clase que controla los request/response de los jsp llevando los datos al ActionController relacionado
 *  
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */

public class ReporteVENFacturaNotaCreditoForm extends BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802777455578561429L;
	//-- Variables Instancia
	private String codigoPais;
	private String fechaInicio;
	private String fechaFin;
	private String tipoReporte;
	
	private String oidPais;
	
	private Date calendarInicio;	
	private Date calendarFin;

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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return the calendarInicio
	 */
	public Date getCalendarInicio() {
		return calendarInicio;
	}
	/**
	 * @param calendarInicio the calendarInicio to set
	 */
	public void setCalendarInicio(Date calendarInicio) {		
		if(calendarInicio!=null && calendarInicio.toString().length()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaInicio = sdf.format(calendarInicio);			
		}
		this.calendarInicio = calendarInicio;		
	}
	/**
	 * @return the calendarFin
	 */
	public Date getCalendarFin() {
		return calendarFin;
	}
	/**
	 * @param calendarFin the calendarFin to set
	 */
	public void setCalendarFin(Date calendarFin) {		
		if(calendarFin!=null && calendarFin.toString().length()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaFin = sdf.format(calendarFin);			
		}
		this.calendarFin = calendarFin;
	}
}
