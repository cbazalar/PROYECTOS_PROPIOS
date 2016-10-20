package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClientePrimerContacto extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidCliente;
	private Long oidClienteSubTipo;
	private Date fechaContacto;
	private Date fechaSiguienteContacto;
	private String codigoTipoContacto;
	private Long oidMarca;
	private Long oidCanal;
	private Long oidPeriodo;
	private String sFechaContacto;
	private String sFechaSiguienteContacto;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	public ClientePrimerContacto() {
		
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
	 * @return Returns the codigoTipoContacto.
	 */
	public String getCodigoTipoContacto() {
		return codigoTipoContacto;
	}

	/**
	 * @param codigoTipoContacto The codigoTipoContacto to set.
	 */
	public void setCodigoTipoContacto(String codigoTipoContacto) {
		this.codigoTipoContacto = codigoTipoContacto;
	}

	/**
	 * @return Returns the fechaContacto.
	 */
	public Date getFechaContacto() {
		return fechaContacto;
	}

	/**
	 * @param fechaContacto The fechaContacto to set.
	 */
	public void setFechaContacto(Date fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

	/**
	 * @return Returns the fechaSiguienteContacto.
	 */
	public Date getFechaSiguienteContacto() {
		return fechaSiguienteContacto;
	}

	/**
	 * @param fechaSiguienteContacto The fechaSiguienteContacto to set.
	 */
	public void setFechaSiguienteContacto(Date fechaSiguienteContacto) {
		this.fechaSiguienteContacto = fechaSiguienteContacto;
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
	 * @return Returns the oidCanal.
	 */
	public Long getOidCanal() {
		return oidCanal;
	}

	/**
	 * @param oidCanal The oidCanal to set.
	 */
	public void setOidCanal(Long oidCanal) {
		this.oidCanal = oidCanal;
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
	 * @return Returns the oidClienteSubTipo.
	 */
	public Long getOidClienteSubTipo() {
		return oidClienteSubTipo;
	}

	/**
	 * @param oidClienteSubTipo The oidClienteSubTipo to set.
	 */
	public void setOidClienteSubTipo(Long oidClienteSubTipo) {
		this.oidClienteSubTipo = oidClienteSubTipo;
	}

	/**
	 * @return Returns the oidMarca.
	 */
	public Long getOidMarca() {
		return oidMarca;
	}

	/**
	 * @param oidMarca The oidMarca to set.
	 */
	public void setOidMarca(Long oidMarca) {
		this.oidMarca = oidMarca;
	}

	/**
	 * @return Returns the oidPeriodo.
	 */
	public Long getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo The oidPeriodo to set.
	 */
	public void setOidPeriodo(Long oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
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

	public String getsFechaContacto() {
		return sFechaContacto;
	}

	public void setsFechaContacto(String sFechaContacto) {
		this.sFechaContacto = sFechaContacto;
	}

	public String getsFechaSiguienteContacto() {
		return sFechaSiguienteContacto;
	}

	public void setsFechaSiguienteContacto(String sFechaSiguienteContacto) {
		this.sFechaSiguienteContacto = sFechaSiguienteContacto;
	}
		
}
