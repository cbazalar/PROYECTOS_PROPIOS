package biz.belcorp.ssicc.web.spusicc.inc.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCCargaDespachoConcursoRxPForm extends BaseProcesoForm{

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = 1729412437293343815L;
	private String codigoPeriodo;
	private String oidConcurso;

	private String codigoPeriodoSeleccionado;
	private String oidConcursoSeleccionado;
	
	private String indicadorValido;
	private String encontrado;
	
	private String[] label;
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}
	/**
	 * @param oidConcurso the oidConcurso to set
	 * @struts.validator type = "required"
	 */
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}
	/**
	 * @return the codigoPeriodoSeleccionado
	 */
	public String getCodigoPeriodoSeleccionado() {
		return codigoPeriodoSeleccionado;
	}
	/**
	 * @param codigoPeriodoSeleccionado the codigoPeriodoSeleccionado to set
	 */
	public void setCodigoPeriodoSeleccionado(String codigoPeriodoSeleccionado) {
		this.codigoPeriodoSeleccionado = codigoPeriodoSeleccionado;
	}
	/**
	 * @return the oidConcursoSeleccionado
	 */
	public String getOidConcursoSeleccionado() {
		return oidConcursoSeleccionado;
	}
	/**
	 * @param oidConcursoSeleccionado the oidConcursoSeleccionado to set
	 */
	public void setOidConcursoSeleccionado(String oidConcursoSeleccionado) {
		this.oidConcursoSeleccionado = oidConcursoSeleccionado;
	}
	
	/**
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}
	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}
	/**
	 * @return the encontrado
	 */
	public String getEncontrado() {
		return encontrado;
	}
	/**
	 * @param encontrado the encontrado to set
	 */
	public void setEncontrado(String encontrado) {
		this.encontrado = encontrado;
	}
	/**
	 * @return the label
	 */
	public String[] getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String[] label) {
		this.label = label;
	}
	
}
