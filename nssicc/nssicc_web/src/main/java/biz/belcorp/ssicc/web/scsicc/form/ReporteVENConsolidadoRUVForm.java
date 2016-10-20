
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteVENConsolidadoRUVForm extends BaseReporteForm	implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4012443711759336618L;

	private String codigoPais;

	private String fechaDesde;

	private String fechaHasta;
	
	private Date fechaDesdeD;

	private Date fechaHastaD;
			
	public ReporteVENConsolidadoRUVForm() {
		
		
		this.fechaDesdeD = new Date();
		this.fechaHastaD = new Date();
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
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
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
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
