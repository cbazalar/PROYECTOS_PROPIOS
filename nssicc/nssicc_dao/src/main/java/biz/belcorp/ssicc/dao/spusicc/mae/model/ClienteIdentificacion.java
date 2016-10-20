package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteIdentificacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidTipoDocumento;
	private Long oidCliente;
	private String numeroDocumento;
	private Integer indicadorPrincipal;
	private String identificadorPersonal;
	private String codigoSubtipoDocumento;

	private String codigoUsuario;

	private boolean isEliminar;
	/**
	 * @return the isEliminar
	 */
	public boolean isEliminar() {
		return isEliminar;
	}

	/**
	 * @param isEliminar the isEliminar to set
	 */
	public void setEliminar(boolean isEliminar) {
		this.isEliminar = isEliminar;
	}

	public ClienteIdentificacion() {
		
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
	 * @return Returns the identificadorPersonal.
	 */
	public String getIdentificadorPersonal() {
		return identificadorPersonal;
	}

	/**
	 * @param identificadorPersonal The identificadorPersonal to set.
	 */
	public void setIdentificadorPersonal(String identificadorPersonal) {
		this.identificadorPersonal = identificadorPersonal;
	}

	/**
	 * @return Returns the indicadorPrincipal.
	 */
	public Integer getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	/**
	 * @param indicadorPrincipal The indicadorPrincipal to set.
	 */
	public void setIndicadorPrincipal(Integer indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public Long getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidTipoDocumento.
	 */
	public Long getOidTipoDocumento() {
		return oidTipoDocumento;
	}

	/**
	 * @param oidTipoDocumento The oidTipoDocumento to set.
	 */
	public void setOidTipoDocumento(Long oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the codigoSubtipoDocumento
	 */
	public String getCodigoSubtipoDocumento() {
		return codigoSubtipoDocumento;
	}

	/**
	 * @param codigoSubtipoDocumento the codigoSubtipoDocumento to set
	 */
	public void setCodigoSubtipoDocumento(String codigoSubtipoDocumento) {
		this.codigoSubtipoDocumento = codigoSubtipoDocumento;
	}

}