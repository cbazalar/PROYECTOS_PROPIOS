package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteSubTipo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidCliente;
	private Long oidTipoCliente;
	private Long oidSubTipoCliente;
	private Integer indicadorPrincipal;

	private List listClienteClasificacion;
	
	//Para la parte WEB
	private String codigoTipoCliente;
	private String codigoSubTipoCliente;
	private String descripcionTipoCliente;
	private String descripcionSubTipoCliente;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	//
	private boolean isEliminar;
	private boolean principal;
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

	public ClienteSubTipo() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String toString() {
		return "ClienteSubTipo [oid=" + oid + ", oidCliente=" + oidCliente
				+ ", oidTipoCliente=" + oidTipoCliente + ", oidSubTipoCliente="
				+ oidSubTipoCliente + ", indicadorPrincipal="
				+ indicadorPrincipal + ", listClienteClasificacion="
				+ listClienteClasificacion + ", codigoTipoCliente="
				+ codigoTipoCliente + ", codigoSubTipoCliente="
				+ codigoSubTipoCliente + ", descripcionTipoCliente="
				+ descripcionTipoCliente + ", descripcionSubTipoCliente="
				+ descripcionSubTipoCliente + ", codigoUsuario="
				+ codigoUsuario + ", isEliminar=" + isEliminar + ", principal="
				+ principal + "]";
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
	 * @return Returns the oidSubTipoCliente.
	 */
	public Long getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente The oidSubTipoCliente to set.
	 */
	public void setOidSubTipoCliente(Long oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return Returns the oidTipoCliente.
	 */
	public Long getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente The oidTipoCliente to set.
	 */
	public void setOidTipoCliente(Long oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return Returns the descripcionSubTipoCliente.
	 */
	public String getDescripcionSubTipoCliente() {
		return descripcionSubTipoCliente;
	}

	/**
	 * @param descripcionSubTipoCliente The descripcionSubTipoCliente to set.
	 */
	public void setDescripcionSubTipoCliente(String descripcionSubTipoCliente) {
		this.descripcionSubTipoCliente = descripcionSubTipoCliente;
	}

	/**
	 * @return Returns the descripcionTipoCliente.
	 */
	public String getDescripcionTipoCliente() {
		return descripcionTipoCliente;
	}

	/**
	 * @param descripcionTipoCliente The descripcionTipoCliente to set.
	 */
	public void setDescripcionTipoCliente(String descripcionTipoCliente) {
		this.descripcionTipoCliente = descripcionTipoCliente;
	}

	/**
	 * @return Returns the listClienteClasificacion.
	 */
	public List getListClienteClasificacion() {
		return listClienteClasificacion;
	}

	/**
	 * @param listClienteClasificacion The listClienteClasificacion to set.
	 */
	public void setListClienteClasificacion(List listClienteClasificacion) {
		this.listClienteClasificacion = listClienteClasificacion;
	}

	/**
	 * @return Returns the codigoSubTipoCliente.
	 */
	public String getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}

	/**
	 * @param codigoSubTipoCliente The codigoSubTipoCliente to set.
	 */
	public void setCodigoSubTipoCliente(String codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}

	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente The codigoTipoCliente to set.
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
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

	public boolean isPrincipal() {
		return this.getIndicadorPrincipal().equals(1)?true:false;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	
}
