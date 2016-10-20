package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteRECIndFactDevolucionesVentaForm extends BaseReporteForm	implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1922725769083099903L;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String codigoPeriodoRefInicio;
	private String codigoPeriodoRefFin;
	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the codigoPeriodoRefInicio
	 */
	public String getCodigoPeriodoRefInicio() {
		return codigoPeriodoRefInicio;
	}
	/**
	 * @param codigoPeriodoRefInicio the codigoPeriodoRefInicio to set
	 */
	public void setCodigoPeriodoRefInicio(String codigoPeriodoRefInicio) {
		this.codigoPeriodoRefInicio = codigoPeriodoRefInicio;
	}
	/**
	 * @return the codigoPeriodoRefFin
	 */
	public String getCodigoPeriodoRefFin() {
		return codigoPeriodoRefFin;
	}
	/**
	 * @param codigoPeriodoRefFin the codigoPeriodoRefFin to set
	 */
	public void setCodigoPeriodoRefFin(String codigoPeriodoRefFin) {
		this.codigoPeriodoRefFin = codigoPeriodoRefFin;
	}
			
	
	

	
}

