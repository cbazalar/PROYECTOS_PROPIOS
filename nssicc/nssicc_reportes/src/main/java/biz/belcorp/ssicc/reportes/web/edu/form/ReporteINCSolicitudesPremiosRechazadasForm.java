package biz.belcorp.ssicc.reportes.web.edu.form;


import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a>Jesse James Rios Franco</a>
 * 
 * @struts.form name = "reporteINCSolicitudesPremiosRechazadasForm"
 */

public class ReporteINCSolicitudesPremiosRechazadasForm extends BaseReporteForm implements Serializable{
	
	private String codigoPais;
	private String codigoConcurso;
	private String codigoPeriodo;
	
	public void reset() {

	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return codigoConcurso
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso
	 * @struts.validator type = "required"
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
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
}