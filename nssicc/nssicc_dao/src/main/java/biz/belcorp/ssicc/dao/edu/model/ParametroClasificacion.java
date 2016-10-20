package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



public class ParametroClasificacion extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8719296304991503374L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoClasificacion;
	private String nombreClasificacion;
	private String indSeleccionCapacitadas;
	private String nivelCapacitacion;
	private String descripcionTipoAsistenteCurso;
	private Integer numeroCampanhaPostCurso;
	private String codigoEstadoNivel;
	private String descripcionEstadoNivel;
	private String codigoTipoAsistenteCurso;
	private String estadoClasificacion;
	
	private String codigoClasificacionEquivalencia;
	private String tipoClasificacionEquivalencia;
	private boolean mostrarClasificacionEquivalencia = false;
	private String estadoRegistro;
	
	private String tipoClasificacion;
	private String tipoCurso;
	private String indicadorTodasCampanhas;
	private String indicadorCampanhasAnteriores;

	private String indicadorDespachoClasificacion;
	private String numeroCampMaximoDespacho;
	
	
	/**
	 * @return Returns the indicadorDespachoClasificacion.
	 */
	public String getIndicadorDespachoClasificacion() {
		return indicadorDespachoClasificacion;
	}
	/**
	 * @param indicadorDespachoClasificacion The indicadorDespachoClasificacion to set.
	 */
	public void setIndicadorDespachoClasificacion(
			String indicadorDespachoClasificacion) {
		this.indicadorDespachoClasificacion = indicadorDespachoClasificacion;
	}
	/**
	 * @return Returns the numeroCampMaximoDespacho.
	 */
	public String getNumeroCampMaximoDespacho() {
		return numeroCampMaximoDespacho;
	}
	/**
	 * @param numeroCampMaximoDespacho The numeroCampMaximoDespacho to set.
	 */
	public void setNumeroCampMaximoDespacho(String numeroCampMaximoDespacho) {
		this.numeroCampMaximoDespacho = numeroCampMaximoDespacho;
	}
	/**
	 * @return Returns the indicadorCampanhasAnteriores.
	 */
	public String getIndicadorCampanhasAnteriores() {
		return indicadorCampanhasAnteriores;
	}
	/**
	 * @param indicadorCampanhasAnteriores The indicadorCampanhasAnteriores to set.
	 */
	public void setIndicadorCampanhasAnteriores(String indicadorCampanhasAnteriores) {
		this.indicadorCampanhasAnteriores = indicadorCampanhasAnteriores;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the codigoEstadoNivel.
	 */
	public String getCodigoEstadoNivel() {
		return codigoEstadoNivel;
	}
	/**
	 * @param codigoEstadoNivel The codigoEstadoNivel to set.
	 */
	public void setCodigoEstadoNivel(String codigoEstadoNivel) {
		this.codigoEstadoNivel = codigoEstadoNivel;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoTipoAsistenteCurso.
	 */
	public String getCodigoTipoAsistenteCurso() {
		return codigoTipoAsistenteCurso;
	}
	/**
	 * @param codigoTipoAsistenteCurso The codigoTipoAsistenteCurso to set.
	 */
	public void setCodigoTipoAsistenteCurso(String codigoTipoAsistenteCurso) {
		this.codigoTipoAsistenteCurso = codigoTipoAsistenteCurso;
	}
	/**
	 * @return Returns the estadoClasificacion.
	 */
	public String getEstadoClasificacion() {
		return estadoClasificacion;
	}
	/**
	 * @param estadoClasificacion The estadoClasificacion to set.
	 */
	public void setEstadoClasificacion(String estadoClasificacion) {
		this.estadoClasificacion = estadoClasificacion;
	}
	/**
	 * @return Returns the nivelCapacitacion.
	 */
	public String getNivelCapacitacion() {
		return nivelCapacitacion;
	}
	/**
	 * @param nivelCapacitacion The nivelCapacitacion to set.
	 */
	public void setNivelCapacitacion(String nivelCapacitacion) {
		this.nivelCapacitacion = nivelCapacitacion;
	}
	/**
	 * @return Returns the numeroCampanhaPostCurso.
	 */
	public Integer getNumeroCampanhaPostCurso() {
		return numeroCampanhaPostCurso;
	}
	/**
	 * @param numeroCampanhaPostCurso The numeroCampanhaPostCurso to set.
	 */
	public void setNumeroCampanhaPostCurso(Integer numeroCampanhaPostCurso) {
		this.numeroCampanhaPostCurso = numeroCampanhaPostCurso;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return Returns the descripcionEstadoNivel.
	 */
	public String getDescripcionEstadoNivel() {
		return descripcionEstadoNivel;
	}
	/**
	 * @param descripcionEstadoNivel The descripcionEstadoNivel to set.
	 */
	public void setDescripcionEstadoNivel(String descripcionEstadoNivel) {
		this.descripcionEstadoNivel = descripcionEstadoNivel;
	}
	/**
	 * @return Returns the descripcionTipoAsistenteCurso.
	 */
	public String getDescripcionTipoAsistenteCurso() {
		return descripcionTipoAsistenteCurso;
	}
	/**
	 * @param descripcionTipoAsistenteCurso The descripcionTipoAsistenteCurso to set.
	 */
	public void setDescripcionTipoAsistenteCurso(
			String descripcionTipoAsistenteCurso) {
		this.descripcionTipoAsistenteCurso = descripcionTipoAsistenteCurso;
	}
	
	public String getIndSeleccionCapacitadas() {
		return indSeleccionCapacitadas;
	}
	
	public void setIndSeleccionCapacitadas(String indSeleccionCapacitadas) {
		this.indSeleccionCapacitadas = indSeleccionCapacitadas;
	}
	
	public String getNombreClasificacion() {
		return nombreClasificacion;
	}
	
	public void setNombreClasificacion(String nombreClasificacion) {
		this.nombreClasificacion = nombreClasificacion;
	}
	
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}
	
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}
	
	public String getTipoCurso() {
		return tipoCurso;
	}
	
	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
	
	/**
	 * @return Returns the codigoClasificacionEquivalencia.
	 */
	public String getCodigoClasificacionEquivalencia() {
		return codigoClasificacionEquivalencia;
	}
	
	/**
	 * @param codigoClasificacionEquivalencia The codigoClasificacionEquivalencia to set.
	 */
	public void setCodigoClasificacionEquivalencia(
			String codigoClasificacionEquivalencia) {
		this.codigoClasificacionEquivalencia = codigoClasificacionEquivalencia;
	}
	
	/**
	 * @return Returns the tipoClasificacionEquivalencia.
	 */
	public String getTipoClasificacionEquivalencia() {
		return tipoClasificacionEquivalencia;
	}
	
	/**
	 * @param tipoClasificacionEquivalencia The tipoClasificacionEquivalencia to set.
	 */
	public void setTipoClasificacionEquivalencia(
			String tipoClasificacionEquivalencia) {
		this.tipoClasificacionEquivalencia = tipoClasificacionEquivalencia;
	}
	/**
	 * @return Returns the mostrarClasificacionEquivalencia.
	 */
	public boolean isMostrarClasificacionEquivalencia() {
		return mostrarClasificacionEquivalencia;
	}
	/**
	 * @param mostrarClasificacionEquivalencia The mostrarClasificacionEquivalencia to set.
	 */
	public void setMostrarClasificacionEquivalencia(
			boolean mostrarClasificacionEquivalencia) {
		this.mostrarClasificacionEquivalencia = mostrarClasificacionEquivalencia;
	}
	/**
	 * @return Returns the indicadorTodasCampanhas.
	 */
	public String getIndicadorTodasCampanhas() {
		return indicadorTodasCampanhas;
	}
	/**
	 * @param indicadorTodasCampanhas The indicadorTodasCampanhas to set.
	 */
	public void setIndicadorTodasCampanhas(String indicadorTodasCampanhas) {
		this.indicadorTodasCampanhas = indicadorTodasCampanhas;
	}
	
	

}
