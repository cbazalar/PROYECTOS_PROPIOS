package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDUCierreProcesosCampannaForm extends BaseProcesoForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4682338687463728980L;
	private String codigoPais;
	private String codigoEmpresa;
	private String codigoPeriodo;
	private String indicadorBloqueo;
	private String indicadorConsultoraRezagada;
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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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
	 * @return the indicadorBloqueo
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}
	/**
	 * @param indicadorBloqueo the indicadorBloqueo to set
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}
	/**
	 * @return the indicadorConsultoraRezagada
	 */
	public String getIndicadorConsultoraRezagada() {
		return indicadorConsultoraRezagada;
	}
	/**
	 * @param indicadorConsultoraRezagada the indicadorConsultoraRezagada to set
	 */
	public void setIndicadorConsultoraRezagada(String indicadorConsultoraRezagada) {
		this.indicadorConsultoraRezagada = indicadorConsultoraRezagada;
	}
	
	

}
