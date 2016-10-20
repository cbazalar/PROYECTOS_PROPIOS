package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCConfiguracionConcursoSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8888364356470229213L;
	private String codigoPais;
	private String oidClasificacionConcurso;
	private String numeroConcurso;
	private String nombreConcurso;

	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String codigoPeriodoDespacho;
	private String codigoPeriodoFinDespacho;
	
	private String oidEstado;
	private String oidVigencia;
		
	private String concurso;

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
	 * @return the oidClasificacionConcurso
	 */
	public String getOidClasificacionConcurso() {
		return oidClasificacionConcurso;
	}

	/**
	 * @param oidClasificacionConcurso the oidClasificacionConcurso to set
	 */
	public void setOidClasificacionConcurso(String oidClasificacionConcurso) {
		this.oidClasificacionConcurso = oidClasificacionConcurso;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	/**
	 * @return the nombreConcurso
	 */
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	/**
	 * @param nombreConcurso the nombreConcurso to set
	 */
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

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
	 * @return the codigoPeriodoDespacho
	 */
	public String getCodigoPeriodoDespacho() {
		return codigoPeriodoDespacho;
	}

	/**
	 * @param codigoPeriodoDespacho the codigoPeriodoDespacho to set
	 */
	public void setCodigoPeriodoDespacho(String codigoPeriodoDespacho) {
		this.codigoPeriodoDespacho = codigoPeriodoDespacho;
	}

	/**
	 * @return the codigoPeriodoFinDespacho
	 */
	public String getCodigoPeriodoFinDespacho() {
		return codigoPeriodoFinDespacho;
	}

	/**
	 * @param codigoPeriodoFinDespacho the codigoPeriodoFinDespacho to set
	 */
	public void setCodigoPeriodoFinDespacho(String codigoPeriodoFinDespacho) {
		this.codigoPeriodoFinDespacho = codigoPeriodoFinDespacho;
	}

	/**
	 * @return the oidEstado
	 */
	public String getOidEstado() {
		return oidEstado;
	}

	/**
	 * @param oidEstado the oidEstado to set
	 */
	public void setOidEstado(String oidEstado) {
		this.oidEstado = oidEstado;
	}

	/**
	 * @return the oidVigencia
	 */
	public String getOidVigencia() {
		return oidVigencia;
	}

	/**
	 * @param oidVigencia the oidVigencia to set
	 */
	public void setOidVigencia(String oidVigencia) {
		this.oidVigencia = oidVigencia;
	}

	/**
	 * @return the concurso
	 */
	public String getConcurso() {
		return concurso;
	}

	/**
	 * @param concurso the concurso to set
	 */
	public void setConcurso(String concurso) {
		this.concurso = concurso;
	}
	
	
	

}
