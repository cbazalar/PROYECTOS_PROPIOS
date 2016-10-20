package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 *
 */
public class MensajeEducacion extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 681620406145601799L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String codigoMensaje;
	private String descripcionMensaje;
	private String tipoMensaje;
	private String codigoEstadoCapacitacion;  
	private String descripcionEstadoCapacitacion;
	private Integer opcionMensaje;
	private String  estadoMensaje;
	private String  estadoRegistro;
	//codigo mensaje equivalente
	private String codigoMensajeEquivalencia;
	private boolean isMensajeEquivalente;
	private String codigoEquivalenciaAnt;
	private String indicadorCursoCosto;
	/**
	 * @return Returns the codigoMensajeEquivalencia.
	 */
	public String getCodigoMensajeEquivalencia() {
		return codigoMensajeEquivalencia;
	}

	/**
	 * @param codigoMensajeEquivalencia The codigoMensajeEquivalencia to set.
	 */
	public void setCodigoMensajeEquivalencia(String codigoMensajeEquivalencia) {
		this.codigoMensajeEquivalencia = codigoMensajeEquivalencia;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
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
	 * @return Returns the codigoMensaje.
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje The codigoMensaje to set.
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
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
	 * @return Returns the descripcionMensaje.
	 */
	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}

	/**
	 * @param descripcionMensaje The descripcionMensaje to set.
	 */
	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * @return Returns the estadoMensaje.
	 */
	public String getEstadoMensaje() {
		return estadoMensaje;
	}

	/**
	 * @param estadoMensaje The estadoMensaje to set.
	 */
	public void setEstadoMensaje(String estadoMensaje) {
		this.estadoMensaje = estadoMensaje;
	}

	/**
	 * @return Returns the nombreCurso.
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * @param nombreCurso The nombreCurso to set.
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	/**
	 * @return Returns the codigoEstadoCapacitacion.
	 */
	public String getCodigoEstadoCapacitacion() {
		return codigoEstadoCapacitacion;
	}

	/**
	 * @param codigoEstadoCapacitacion The codigoEstadoCapacitacion to set.
	 */
	public void setCodigoEstadoCapacitacion(String codigoEstadoCapacitacion) {
		this.codigoEstadoCapacitacion = codigoEstadoCapacitacion;
	}

	/**
	 * @return Returns the descripcionEstadoCapacitacion.
	 */
	public String getDescripcionEstadoCapacitacion() {
		return descripcionEstadoCapacitacion;
	}

	/**
	 * @param descripcionEstadoCapacitacion The descripcionEstadoCapacitacion to set.
	 */
	public void setDescripcionEstadoCapacitacion(
			String descripcionEstadoCapacitacion) {
		this.descripcionEstadoCapacitacion = descripcionEstadoCapacitacion;
	}

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	
	/**
	 * @return Returns the opcionMensaje.
	 */
	public Integer getOpcionMensaje() {
		return opcionMensaje;
	}

	/**
	 * @param opcionMensaje The opcionMensaje to set.
	 */
	public void setOpcionMensaje(Integer opcionMensaje) {
		this.opcionMensaje = opcionMensaje;
	}

	/**
	 * @return Returns the tipoMensaje.
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}

	/**
	 * @param tipoMensaje The tipoMensaje to set.
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	/**
	 * @return Returns the isMensajeEquivalente.
	 */
	public boolean isMensajeEquivalente() {
		return isMensajeEquivalente;
	}

	/**
	 * @param isMensajeEquivalente The isMensajeEquivalente to set.
	 */
	public void setMensajeEquivalente(boolean isMensajeEquivalente) {
		this.isMensajeEquivalente = isMensajeEquivalente;
	}

	/**
	 * @return Returns the codigoEquivalenciaAnt.
	 */
	public String getCodigoEquivalenciaAnt() {
		return codigoEquivalenciaAnt;
	}

	/**
	 * @param codigoEquivalenciaAnt The codigoEquivalenciaAnt to set.
	 */
	public void setCodigoEquivalenciaAnt(String codigoEquivalenciaAnt) {
		this.codigoEquivalenciaAnt = codigoEquivalenciaAnt;
	}

	/**
	 * @return Returns the indicadorCursoCosto.
	 */
	public String getIndicadorCursoCosto() {
		return indicadorCursoCosto;
	}

	/**
	 * @param indicadorCursoCosto The indicadorCursoCosto to set.
	 */
	public void setIndicadorCursoCosto(String indicadorCursoCosto) {
		this.indicadorCursoCosto = indicadorCursoCosto;
	}

	
	
	
}
