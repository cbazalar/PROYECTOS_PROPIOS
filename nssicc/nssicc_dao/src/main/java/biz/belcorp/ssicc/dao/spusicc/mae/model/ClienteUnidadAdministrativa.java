package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteUnidadAdministrativa extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidCliente;
	private Long periodoInicio;
	private Long periodoFin;
	private Long oidTerritorioAdministrativo;
	private Integer indicadorActivo;
	private String codigoZona;
	private String codigoTerritorio;
	
	private boolean esPeriodoInicioMayorIgualPeriodoVigente;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	private String codigoRegion;
	private String codigoSeccion;
	
	private String indicadorCambio;
		
	private String codigoPeriodoInicio;
	private boolean requiereGenerarEstatus;
		
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

	private boolean isEliminar;

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	public ClienteUnidadAdministrativa() {
		
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
	 * @return Returns the indicadorActivo.
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo The indicadorActivo to set.
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
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
	 * @return Returns the oidTerritorioAdministrativo.
	 */
	public Long getOidTerritorioAdministrativo() {
		return oidTerritorioAdministrativo;
	}

	/**
	 * @param oidTerritorioAdministrativo The oidTerritorioAdministrativo to set.
	 */
	public void setOidTerritorioAdministrativo(Long oidTerritorioAdministrativo) {
		this.oidTerritorioAdministrativo = oidTerritorioAdministrativo;
	}

	/**
	 * @return Returns the periodoFin.
	 */
	public Long getPeriodoFin() {
		return periodoFin;
	}

	/**
	 * @param periodoFin The periodoFin to set.
	 */
	public void setPeriodoFin(Long periodoFin) {
		this.periodoFin = periodoFin;
	}

	/**
	 * @return Returns the periodoInicio.
	 */
	public Long getPeriodoInicio() {
		return periodoInicio;
	}

	/**
	 * @param periodoInicio The periodoInicio to set.
	 */
	public void setPeriodoInicio(Long periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	/**
	 * @return the esPeriodoInicioMayorIgualPeriodoVigente
	 */
	public boolean isEsPeriodoInicioMayorIgualPeriodoVigente() {
		return esPeriodoInicioMayorIgualPeriodoVigente;
	}

	/**
	 * @param esPeriodoInicioMayorIgualPeriodoVigente the esPeriodoInicioMayorIgualPeriodoVigente to set
	 */
	public void setEsPeriodoInicioMayorIgualPeriodoVigente(
			boolean esPeriodoInicioMayorIgualPeriodoVigente) {
		this.esPeriodoInicioMayorIgualPeriodoVigente = esPeriodoInicioMayorIgualPeriodoVigente;
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the indicadorCambio
	 */
	public String getIndicadorCambio() {
		return indicadorCambio;
	}

	/**
	 * @param indicadorCambio the indicadorCambio to set
	 */
	public void setIndicadorCambio(String indicadorCambio) {
		this.indicadorCambio = indicadorCambio;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the requiereGenerarEstatus
	 */
	public boolean isRequiereGenerarEstatus() {
		return requiereGenerarEstatus;
	}

	/**
	 * @param requiereGenerarEstatus the requiereGenerarEstatus to set
	 */
	public void setRequiereGenerarEstatus(boolean requiereGenerarEstatus) {
		this.requiereGenerarEstatus = requiereGenerarEstatus;
	}

}
