package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteVinculo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long oidCliente;
	private Long oidClienteVinculante;
	private Long oidTipoVinculo;
	private Integer indicadorPrincipal;
	private Date fechaActualizacion;
	
	private String codigoTipoVinculo;
	private String codigoClienteVinculante;
	private Long oidClienteVinculanteAnterior;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	public ClienteVinculo() {
		
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
	 * @return Returns the fechaDesde.
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde The fechaDesde to set.
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta The fechaHasta to set.
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * @return Returns the oidTipoVinculo.
	 */
	public Long getOidTipoVinculo() {
		return oidTipoVinculo;
	}

	/**
	 * @param oidTipoVinculo The oidTipoVinculo to set.
	 */
	public void setOidTipoVinculo(Long oidTipoVinculo) {
		this.oidTipoVinculo = oidTipoVinculo;
	}

	/**
	 * @return Returns the oidClienteVinculante.
	 */
	public Long getOidClienteVinculante() {
		return oidClienteVinculante;
	}

	/**
	 * @param oidClienteVinculante The oidClienteVinculante to set.
	 */
	public void setOidClienteVinculante(Long oidClienteVinculante) {
		this.oidClienteVinculante = oidClienteVinculante;
	}

	/**
	 * @return Returns the codigoTipoVinculo.
	 */
	public String getCodigoTipoVinculo() {
		return codigoTipoVinculo;
	}

	/**
	 * @param codigoTipoVinculo The codigoTipoVinculo to set.
	 */
	public void setCodigoTipoVinculo(String codigoTipoVinculo) {
		this.codigoTipoVinculo = codigoTipoVinculo;
	}

	/**
	 * @return Returns the fechaActualizacion.
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion The fechaActualizacion to set.
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the codigoClienteVinculante
	 */
	public String getCodigoClienteVinculante() {
		return codigoClienteVinculante;
	}

	/**
	 * @param codigoClienteVinculante the codigoClienteVinculante to set
	 */
	public void setCodigoClienteVinculante(String codigoClienteVinculante) {
		this.codigoClienteVinculante = codigoClienteVinculante;
	}

	/**
	 * @return the oidClienteVinculanteAnterior
	 */
	public Long getOidClienteVinculanteAnterior() {
		return oidClienteVinculanteAnterior;
	}

	/**
	 * @param oidClienteVinculanteAnterior the oidClienteVinculanteAnterior to set
	 */
	public void setOidClienteVinculanteAnterior(Long oidClienteVinculanteAnterior) {
		this.oidClienteVinculanteAnterior = oidClienteVinculanteAnterior;
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

}

