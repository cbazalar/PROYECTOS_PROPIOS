package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosComisiones extends AuditableBaseObject implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Comisin Recuperacin
	private Integer oidComision;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoComision;
	private String descripcion;
	private int indicadorConsideraSD;
	private int indicadorDctoImpuesto;
	private int indicadorComisionEscalonada;
	private int indicadorDsctoReclamoFacturacion;
	private int indicadorConsiderarCronograma;
	private String codigoBaseComision;
	private String codigoEstado;
	private String codigoTipoComisionista;
	private String estadoComision;
	private String montoFijoComision;
	private String comisionRetirada;
	private String porcentajeVentaConsultoras;
	private String nroDiasRecuperacion; 
	
	private String nombreUsuario;
	private Date fechaUsuario;
	
	private String nombreModiUsuario;
	private Date fechaModiUsuario;
	
	private String porcentajeComisionRetail;

	/**
	 * @return the oidComision
	 */
	public Integer getOidComision() {
		return oidComision;
	}

	/**
	 * @param oidComision the oidComision to set
	 */
	public void setOidComision(Integer oidComision) {
		this.oidComision = oidComision;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision
	 *            the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the indicadorConsideraSD
	 */
	public int getIndicadorConsideraSD() {
		return indicadorConsideraSD;
	}

	/**
	 * @param indicadorConsideraSD
	 *            the indicadorConsideraSD to set
	 */
	public void setIndicadorConsideraSD(int indicadorConsideraSD) {
		this.indicadorConsideraSD = indicadorConsideraSD;
	}

	/**
	 * @return the indicadorDctoImpuesto
	 */
	public int getIndicadorDctoImpuesto() {
		return indicadorDctoImpuesto;
	}

	/**
	 * @param indicadorDctoImpuesto
	 *            the indicadorDctoImpuesto to set
	 */
	public void setIndicadorDctoImpuesto(int indicadorDctoImpuesto) {
		this.indicadorDctoImpuesto = indicadorDctoImpuesto;
	}

	/**
	 * @return the indicadorComisionEscalonada
	 */
	public int getIndicadorComisionEscalonada() {
		return indicadorComisionEscalonada;
	}

	/**
	 * @param indicadorComisionEscalonada
	 *            the indicadorComisionEscalonada to set
	 */
	public void setIndicadorComisionEscalonada(int indicadorComisionEscalonada) {
		this.indicadorComisionEscalonada = indicadorComisionEscalonada;
	}

	/**
	 * @return the indicadorDsctoReclamoFacturacion
	 */
	public int getIndicadorDsctoReclamoFacturacion() {
		return indicadorDsctoReclamoFacturacion;
	}

	/**
	 * @param indicadorDsctoReclamoFacturacion
	 *            the indicadorDsctoReclamoFacturacion to set
	 */
	public void setIndicadorDsctoReclamoFacturacion(
			int indicadorDsctoReclamoFacturacion) {
		this.indicadorDsctoReclamoFacturacion = indicadorDsctoReclamoFacturacion;
	}

	/**
	 * @return the codigoBaseComision
	 */
	public String getCodigoBaseComision() {
		return codigoBaseComision;
	}

	/**
	 * @param codigoBaseComision
	 *            the codigoBaseComision to set
	 */
	public void setCodigoBaseComision(String codigoBaseComision) {
		this.codigoBaseComision = codigoBaseComision;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado
	 *            the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the codigoTipoComisionista
	 */
	public String getCodigoTipoComisionista() {
		return codigoTipoComisionista;
	}

	/**
	 * @param codigoTipoComisionista
	 *            the codigoTipoComisionista to set
	 */
	public void setCodigoTipoComisionista(String codigoTipoComisionista) {
		this.codigoTipoComisionista = codigoTipoComisionista;
	}

	/**
	 * @return the fechaUsuario
	 */
	public Date getFechaUsuario() {
		return fechaUsuario;
	}

	/**
	 * @param fechaUsuario the fechaUsuario to set
	 */
	public void setFechaUsuario(Date fechaUsuario) {
		this.fechaUsuario = fechaUsuario;
	}

	/**
	 * @return the fechaModiUsuario
	 */
	public Date getFechaModiUsuario() {
		return fechaModiUsuario;
	}

	/**
	 * @param fechaModiUsuario the fechaModiUsuario to set
	 */
	public void setFechaModiUsuario(Date fechaModiUsuario) {
		this.fechaModiUsuario = fechaModiUsuario;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the nombreModiUsuario
	 */
	public String getNombreModiUsuario() {
		return nombreModiUsuario;
	}

	/**
	 * @param nombreModiUsuario the nombreModiUsuario to set
	 */
	public void setNombreModiUsuario(String nombreModiUsuario) {
		this.nombreModiUsuario = nombreModiUsuario;
	}

	public String getEstadoComision() {
		return estadoComision;
	}

	public void setEstadoComision(String estadoComision) {
		this.estadoComision = estadoComision;
	}

	/**
	 * @return the indicadorConsiderarCronograma
	 */
	public int getIndicadorConsiderarCronograma() {
		return indicadorConsiderarCronograma;
	}

	/**
	 * @param indicadorConsiderarCronograma the indicadorConsiderarCronograma to set
	 */
	public void setIndicadorConsiderarCronograma(int indicadorConsiderarCronograma) {
		this.indicadorConsiderarCronograma = indicadorConsiderarCronograma;
	}

	/**
	 * @return the montoFijoComision
	 */
	public String getMontoFijoComision() {
		return montoFijoComision;
	}

	/**
	 * @param montoFijoComision the montoFijoComision to set
	 */
	public void setMontoFijoComision(String montoFijoComision) {
		this.montoFijoComision = montoFijoComision;
	}

	/**
	 * @return the comisionRetirada
	 */
	public String getComisionRetirada() {
		return comisionRetirada;
	}

	/**
	 * @param comisionRetirada the comisionRetirada to set
	 */
	public void setComisionRetirada(String comisionRetirada) {
		this.comisionRetirada = comisionRetirada;
	}

	/**
	 * @return the porcentajeVentaConsultoras
	 */
	public String getPorcentajeVentaConsultoras() {
		return porcentajeVentaConsultoras;
	}

	/**
	 * @param porcentajeVentaConsultoras the porcentajeVentaConsultoras to set
	 */
	public void setPorcentajeVentaConsultoras(String porcentajeVentaConsultoras) {
		this.porcentajeVentaConsultoras = porcentajeVentaConsultoras;
	}

	

	/**
	 * @return the nroDiasRecuperacion
	 */
	public String getNroDiasRecuperacion() {
		return nroDiasRecuperacion;
	}

	/**
	 * @param nroDiasRecuperacion the nroDiasRecuperacion to set
	 */
	public void setNroDiasRecuperacion(String nroDiasRecuperacion) {
		this.nroDiasRecuperacion = nroDiasRecuperacion;
	}

	
	
	/**
	 * @return the porcentajeComisionRetail
	 */
	public String getPorcentajeComisionRetail() {
		return porcentajeComisionRetail;
	}

	/**
	 * @param porcentajeComisionRetail the porcentajeComisionRetail to set
	 */
	public void setPorcentajeComisionRetail(String porcentajeComisionRetail) {
		this.porcentajeComisionRetail = porcentajeComisionRetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
