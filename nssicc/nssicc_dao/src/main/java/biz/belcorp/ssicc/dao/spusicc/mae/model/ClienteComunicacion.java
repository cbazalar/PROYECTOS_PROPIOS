package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteComunicacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidCliente;
	private Long oidTipoComunicacion;
	private String diaComunicacion;
	private String textoComunicacion;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer intervaloComunicacion;
	private Integer indicadorPrincipal;
	private String codigoTipoComunicacion;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
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

	/**
	 * @return the codigoTipoComunicacion
	 */
	public String getCodigoTipoComunicacion() {
		return codigoTipoComunicacion;
	}

	/**
	 * @param codigoTipoComunicacion the codigoTipoComunicacion to set
	 */
	public void setCodigoTipoComunicacion(String codigoTipoComunicacion) {
		this.codigoTipoComunicacion = codigoTipoComunicacion;
	}

	public ClienteComunicacion() {
		
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
	 * @return Returns the diaComunicacion.
	 */
	public String getDiaComunicacion() {
		return diaComunicacion;
	}

	/**
	 * @param diaComunicacion The diaComunicacion to set.
	 */
	public void setDiaComunicacion(String diaComunicacion) {
		this.diaComunicacion = diaComunicacion;
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
	 * @return Returns the intervaloComunicacion.
	 */
	public Integer getIntervaloComunicacion() {
		return intervaloComunicacion;
	}

	/**
	 * @param intervaloComunicacion The intervaloComunicacion to set.
	 */
	public void setIntervaloComunicacion(Integer intervaloComunicacion) {
		this.intervaloComunicacion = intervaloComunicacion;
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
	 * @return Returns the oidTipoComunicacion.
	 */
	public Long getOidTipoComunicacion() {
		return oidTipoComunicacion;
	}

	/**
	 * @param oidTipoComunicacion The oidTipoComunicacion to set.
	 */
	public void setOidTipoComunicacion(Long oidTipoComunicacion) {
		this.oidTipoComunicacion = oidTipoComunicacion;
	}

	/**
	 * @return Returns the textoComunicacion.
	 */
	public String getTextoComunicacion() {
		return textoComunicacion;
	}

	/**
	 * @param textoComunicacion The textoComunicacion to set.
	 */
	public void setTextoComunicacion(String textoComunicacion) {
		this.textoComunicacion = textoComunicacion;
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
