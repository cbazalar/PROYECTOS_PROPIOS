package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class DespachoProductoCurso extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 795983910290364014L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String codigoCurso;
	private int    correlativoDespachoCurso;
	private String codigoTipoDespacho;
	private String descripcionTipoDespacho;
	private String codigoProducto;
	private String codigoTipoIndicadorDespacho;
	private String descripcionTipoIndicadorDespacho;
	
	private String estadoRegistro;
	
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
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the codigoTipoDespacho.
	 */
	public String getCodigoTipoDespacho() {
		return codigoTipoDespacho;
	}

	/**
	 * @param codigoTipoDespacho The codigoTipoDespacho to set.
	 */
	public void setCodigoTipoDespacho(String codigoTipoDespacho) {
		this.codigoTipoDespacho = codigoTipoDespacho;
	}

	/**
	 * @return Returns the codigoTipoIndicadorDespacho.
	 */
	public String getCodigoTipoIndicadorDespacho() {
		return codigoTipoIndicadorDespacho;
	}

	/**
	 * @param codigoTipoIndicadorDespacho The codigoTipoIndicadorDespacho to set.
	 */
	public void setCodigoTipoIndicadorDespacho(String codigoTipoIndicadorDespacho) {
		this.codigoTipoIndicadorDespacho = codigoTipoIndicadorDespacho;
	}

	/**
	 * @return Returns the correlativoDespachoCurso.
	 */
	public int getCorrelativoDespachoCurso() {
		return correlativoDespachoCurso;
	}

	/**
	 * @param correlativoDespachoCurso The correlativoDespachoCurso to set.
	 */
	public void setCorrelativoDespachoCurso(int correlativoDespachoCurso) {
		this.correlativoDespachoCurso = correlativoDespachoCurso;
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the descripcionTipoDespacho.
	 */
	public String getDescripcionTipoDespacho() {
		return descripcionTipoDespacho;
	}

	/**
	 * @param descripcionTipoDespacho The descripcionTipoDespacho to set.
	 */
	public void setDescripcionTipoDespacho(String descripcionTipoDespacho) {
		this.descripcionTipoDespacho = descripcionTipoDespacho;
	}

	/**
	 * @return Returns the descripcionTipoIndicadorDespacho.
	 */
	public String getDescripcionTipoIndicadorDespacho() {
		return descripcionTipoIndicadorDespacho;
	}

	/**
	 * @param descripcionTipoIndicadorDespacho The descripcionTipoIndicadorDespacho to set.
	 */
	public void setDescripcionTipoIndicadorDespacho(
			String descripcionTipoIndicadorDespacho) {
		this.descripcionTipoIndicadorDespacho = descripcionTipoIndicadorDespacho;
	}

	

}
