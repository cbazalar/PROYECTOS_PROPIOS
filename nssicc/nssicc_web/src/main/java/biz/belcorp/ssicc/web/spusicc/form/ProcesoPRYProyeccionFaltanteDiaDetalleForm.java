package biz.belcorp.ssicc.web.spusicc.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class ProcesoPRYProyeccionFaltanteDiaDetalleForm extends BaseSearchForm  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7801413500533014832L;
	private String codigoProcesoBatch;
	private String codigoGrupo;
	private String descripcionGrupo;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Integer totalSolicitud;
	private Integer numeroVersion;
	private String descripcionPeriodo;
	
	
	/**
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}


	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}
	
	/**
	 * @return Returns the codigoGrupo.
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}


	/**
	 * @param codigoGrupo The codigoGrupo to set.
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}


	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}


	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}


	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}


	/**
	 * @return Returns the descripcionGrupo.
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}


	/**
	 * @param descripcionGrupo The descripcionGrupo to set.
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}


	/**
	 * @return Returns the totalSolicitud.
	 */
	public Integer getTotalSolicitud() {
		return totalSolicitud;
	}


	/**
	 * @param totalSolicitud The totalSolicitud to set.
	 */
	public void setTotalSolicitud(Integer totalSolicitud) {
		this.totalSolicitud = totalSolicitud;
	}
	
	
	/**
	 * @return Returns the numeroVersion.
	 */
	public Integer getNumeroVersion() {
		return numeroVersion;
	}


	/**
	 * @param numeroVersion The numeroVersion to set.
	 */
	public void setNumeroVersion(Integer numeroVersion) {
		this.numeroVersion = numeroVersion;
	}


	/**
	 * @return Returns the descripcionPeriodo.
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}


	/**
	 * @param descripcionPeriodo The descripcionPeriodo to set.
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}
	
	
	

}
