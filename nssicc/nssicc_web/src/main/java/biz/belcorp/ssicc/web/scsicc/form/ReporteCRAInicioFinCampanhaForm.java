package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteCRAInicioFinCampanhaForm extends BaseReporteForm	implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1611754760875931019L;
	private String codigoPais;
	private String anio;	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoPeriodo;
	private String opcionesImpresion;
	private String fechaInicioPeriodoFacturacion;
	private String fechaFinalPeriodoFacturacion;
	private Date fechaInicioPeriodoFacturacionD;
	private Date fechaFinalPeriodoFacturacionD;
	
	/**
	 * Datos por Defecto
	 */
	public ReporteCRAInicioFinCampanhaForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
		this.anio = anio;
		this.fechaFinalPeriodoFacturacion=this.fechaInicioPeriodoFacturacion="";
		this.opcionesImpresion="";
		this.codigoAcceso="";
		this.codigoPeriodo="";
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
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}
	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso the codigoAcceso to set
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
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
	 * @return the opcionesImpresion
	 */
	public String getOpcionesImpresion() {
		return opcionesImpresion;
	}
	/**
	 * @param opcionesImpresion the opcionesImpresion to set
	 */
	public void setOpcionesImpresion(String opcionesImpresion) {
		this.opcionesImpresion = opcionesImpresion;
	}
	/**
	 * @return the fechaInicioPeriodoFacturacion
	 */
	public String getFechaInicioPeriodoFacturacion() {
		return fechaInicioPeriodoFacturacion;
	}
	/**
	 * @param fechaInicioPeriodoFacturacion the fechaInicioPeriodoFacturacion to set
	 */
	public void setFechaInicioPeriodoFacturacion(
			String fechaInicioPeriodoFacturacion) {
		this.fechaInicioPeriodoFacturacion = fechaInicioPeriodoFacturacion;
	}
	/**
	 * @return the fechaFinalPeriodoFacturacion
	 */
	public String getFechaFinalPeriodoFacturacion() {
		return fechaFinalPeriodoFacturacion;
	}
	/**
	 * @param fechaFinalPeriodoFacturacion the fechaFinalPeriodoFacturacion to set
	 */
	public void setFechaFinalPeriodoFacturacion(String fechaFinalPeriodoFacturacion) {
		this.fechaFinalPeriodoFacturacion = fechaFinalPeriodoFacturacion;
	}
	/**
	 * @return the fechaInicioPeriodoFacturacionD
	 */
	public Date getFechaInicioPeriodoFacturacionD() {
		return fechaInicioPeriodoFacturacionD;
	}
	/**
	 * @param fechaInicioPeriodoFacturacionD the fechaInicioPeriodoFacturacionD to set
	 */
	public void setFechaInicioPeriodoFacturacionD(
			Date fechaInicioPeriodoFacturacionD) {
		this.fechaInicioPeriodoFacturacionD = fechaInicioPeriodoFacturacionD;
	}
	/**
	 * @return the fechaFinalPeriodoFacturacionD
	 */
	public Date getFechaFinalPeriodoFacturacionD() {
		return fechaFinalPeriodoFacturacionD;
	}
	/**
	 * @param fechaFinalPeriodoFacturacionD the fechaFinalPeriodoFacturacionD to set
	 */
	public void setFechaFinalPeriodoFacturacionD(Date fechaFinalPeriodoFacturacionD) {
		this.fechaFinalPeriodoFacturacionD = fechaFinalPeriodoFacturacionD;
	}
	
	


	
}



