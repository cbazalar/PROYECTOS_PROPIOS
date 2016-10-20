package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class CalificacionAptasDemandaCurso extends AuditableBaseObject  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198918803236810910L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String descripcionAmbitoDictado;	
	private String estadoCurso;
	private String indicadorDetalle="N";
	private String campanhaCalificacion;
	private String estadoRegistro;
	private Integer cantidadCampanha;
	private Integer cantidadCliente;
	private Integer cantidadRegion;
	private Integer cantidadZona;
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
	 * @return Returns the indicadorDetalle.
	 */
	public String getIndicadorDetalle() {
		return indicadorDetalle;
	}
	/**
	 * @param indicadorDetalle The indicadorDetalle to set.
	 */
	public void setIndicadorDetalle(String indicadorDetalle) {
		this.indicadorDetalle = indicadorDetalle;
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
	 * @return Returns the descripcionAmbitoDictado.
	 */
	public String getDescripcionAmbitoDictado() {
		return descripcionAmbitoDictado;
	}
	/**
	 * @param descripcionAmbitoDictado The descripcionAmbitoDictado to set.
	 */
	public void setDescripcionAmbitoDictado(String descripcionAmbitoDictado) {
		this.descripcionAmbitoDictado = descripcionAmbitoDictado;
	}
	/**
	 * @return Returns the estadoCurso.
	 */
	public String getEstadoCurso() {
		return estadoCurso;
	}
	/**
	 * @param estadoCurso The estadoCurso to set.
	 */
	public void setEstadoCurso(String estadoCurso) {
		this.estadoCurso = estadoCurso;
	}
	/**
	 * @return Returns the campanhaCalificacion.
	 */
	public String getCampanhaCalificacion() {
		return campanhaCalificacion;
	}
	/**
	 * @param campanhaCalificacion The campanhaCalificacion to set.
	 */
	public void setCampanhaCalificacion(String campanhaCalificacion) {
		this.campanhaCalificacion = campanhaCalificacion;
	}
	/**
	 * @return Returns the cantidadCampanha.
	 */
	public Integer getCantidadCampanha() {
		return cantidadCampanha;
	}
	/**
	 * @param cantidadCampanha The cantidadCampanha to set.
	 */
	public void setCantidadCampanha(Integer cantidadCampanha) {
		this.cantidadCampanha = cantidadCampanha;
		if ((cantidadCampanha!=null) && (cantidadCampanha.intValue() > 0)) this.indicadorDetalle = "S";
	}
	/**
	 * @return Returns the cantidadCliente.
	 */
	public Integer getCantidadCliente() {
		return cantidadCliente;
	}
	/**
	 * @param cantidadCliente The cantidadCliente to set.
	 */
	public void setCantidadCliente(Integer cantidadCliente) {
		this.cantidadCliente = cantidadCliente;
		if ((cantidadCliente!=null) && (cantidadCliente.intValue() > 0)) this.indicadorDetalle = "S";		
	}
	/**
	 * @return Returns the cantidadRegion.
	 */
	public Integer getCantidadRegion() {
		return cantidadRegion;
	}
	/**
	 * @param cantidadRegion The cantidadRegion to set.
	 */
	public void setCantidadRegion(Integer cantidadRegion) {
		this.cantidadRegion = cantidadRegion;
		if ((cantidadRegion!=null) && (cantidadRegion.intValue() > 0)) this.indicadorDetalle = "S";
	}
	/**
	 * @return Returns the cantidadZona.
	 */
	public Integer getCantidadZona() {
		return cantidadZona;
	}
	/**
	 * @param cantidadZona The cantidadZona to set.
	 */
	public void setCantidadZona(Integer cantidadZona) {
		this.cantidadZona = cantidadZona;
		if ((cantidadZona!=null) && (cantidadZona.intValue() > 0)) this.indicadorDetalle = "S";		
	}

}
