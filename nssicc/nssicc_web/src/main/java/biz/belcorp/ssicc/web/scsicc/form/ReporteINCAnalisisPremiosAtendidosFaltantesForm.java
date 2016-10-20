package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */

public class ReporteINCAnalisisPremiosAtendidosFaltantesForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String periodoInicio;
	
	private String periodoFin;
	
	private String fechaInicio;	
	
	private String fechaFin;
	
	private Date fechaDateInicio;
	
	private Date fechaDateFin;

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
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}

	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}

	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
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

	public Date getFechaDateInicio() {
		return fechaDateInicio;
	}

	public void setFechaDateInicio(Date fechaDateInicio) {
		this.fechaDateInicio = fechaDateInicio;
	}

	public Date getFechaDateFin() {
		return fechaDateFin;
	}

	public void setFechaDateFin(Date fechaDateFin) {
		this.fechaDateFin = fechaDateFin;
	}
	
	
}
