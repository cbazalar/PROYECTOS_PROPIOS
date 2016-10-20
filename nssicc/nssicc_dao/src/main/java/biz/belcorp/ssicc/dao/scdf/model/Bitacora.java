/*
 * Created on 20/05/2005 11:50:06 AM biz.belcorp.privilege.model.Bitacora
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Bitacora.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Bitacora extends BaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5461828604361978056L;

	/**
	 * @uml.property name="numero" multiplicity="(0 1)"
	 */
	private String numero;

	/**
	 * @uml.property name="codigoPais" multiplicity="(0 1)"
	 */
	private String codigoPais;

	/**
	 * @uml.property name="usuarioProcesa" multiplicity="(0 1)"
	 */
	private String usuarioProcesa;

	/**
	 * @uml.property name="nombreArchivo" multiplicity="(0 1)"
	 */
	private String nombreArchivo;

	/**
	 * @uml.property name="fechaProceso" multiplicity="(0 1)"
	 */
	private Date fechaProceso;

	/**
	 * @uml.property name="tipoProceso" multiplicity="(0 1)"
	 */
	private String tipoProceso;

	/**
	 * @uml.property name="numeroRegistros" multiplicity="(0 1)"
	 */
	private int numeroRegistros;

	/**
	 * @uml.property name="comentarios" multiplicity="(0 1)"
	 */
	private String comentarios;

	/**
	 * @uml.property name="numeroLineaError" multiplicity="(0 1)"
	 */
	private int numeroLineaError;

	/**
	 * Constructor por defecto
	 */
	public Bitacora() {
		super();
		this.fechaProceso = new Date();
		this.numeroLineaError = 0;
		this.numeroRegistros = 0;
		this.comentarios = Constants.OK_MESSAGE;
	}

	/**
	 * @uml.property name="numero"
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @uml.property name="numero"
	 */
	public void setNumero(String codigo) {
		this.numero = codigo;
	}

	/**
	 * @uml.property name="codigoPais"
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @uml.property name="codigoPais"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @uml.property name="usuarioProcesa"
	 * @struts.form-field
	 */
	public String getUsuarioProcesa() {
		return usuarioProcesa;
	}

	/**
	 * @uml.property name="usuarioProcesa"
	 */
	public void setUsuarioProcesa(String loginUsuario) {
		this.usuarioProcesa = loginUsuario;
	}

	/**
	 * @uml.property name="nombreArchivo"
	 * @struts.form-field
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @uml.property name="nombreArchivo"
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @uml.property name="fechaProceso"
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @uml.property name="fechaProceso"
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @uml.property name="tipoProceso"
	 * @struts.form-field
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @uml.property name="tipoProceso"
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/**
	 * @uml.property name="numeroRegistros"
	 */
	public int getNumeroRegistros() {
		return numeroRegistros;
	}

	/**
	 * @uml.property name="numeroRegistros"
	 */
	public void setNumeroRegistros(int numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	/**
	 * @uml.property name="comentarios"
	 * @struts.form-field
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @uml.property name="comentarios"
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @uml.property name="numeroLineaError"
	 */
	public int getNumeroLineaError() {
		return numeroLineaError;
	}

	/**
	 * @uml.property name="numeroLineaError"
	 */
	public void setNumeroLineaError(int numeroLineaError) {
		this.numeroLineaError = numeroLineaError;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Bitacora)) {
			return false;
		}

		Bitacora rhs = (Bitacora) object;

		return new EqualsBuilder().append(this.numeroLineaError,
				rhs.numeroLineaError).append(this.codigoPais, rhs.codigoPais)
				.append(this.numeroRegistros, rhs.numeroRegistros).append(
						this.tipoProceso, rhs.tipoProceso).append(
						this.usuarioProcesa, rhs.usuarioProcesa).append(
						this.comentarios, rhs.comentarios).append(
						this.fechaProceso, rhs.fechaProceso).append(
						this.nombreArchivo, rhs.nombreArchivo).append(
						this.numero, rhs.numero).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(370723239, -699123951).append(
				this.numeroLineaError).append(this.codigoPais).append(
				this.numeroRegistros).append(this.tipoProceso).append(
				this.usuarioProcesa).append(this.comentarios).append(
				this.fechaProceso).append(this.nombreArchivo).append(
				this.numero).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("numeroRegistros", this.numeroRegistros).append(
						"codigoPais", this.codigoPais).append("tipoProceso",
						this.tipoProceso).append("comentarios",
						this.comentarios).append("nombreArchivo",
						this.nombreArchivo).append("fechaProceso",
						this.fechaProceso).append("numero", this.numero)
				.append("usuarioProcesa", this.usuarioProcesa).append(
						"numeroLineaError", this.numeroLineaError).toString();
	}
}
