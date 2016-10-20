package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
public class ComisionPeriodoGerenteZona extends AuditableBaseObject implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	private String codigoComision;
	private String codigoLiderZona;
	private String nombreLiderZona;
	private String codigoRegion;
	private String codigoZona;
	private String tipoComision;
	private Date   fechaCalculo;
	private Double importeNetoSinReclamo;
	private Double importeAntesLimiteTramo1;
	private Double importeAntesLimiteTramo2;
	private Double porcentajeRecuperacionTramo1;
	private Double porcentajeRecuperacionTramo2;
	private Double importeComisionTramo1;
	private Double importeComisionTramo2;
	private String codigoPlanilla;
	private String descripcionRegion;
	private String descripcionZona;
	private Double importeSumaComision;
	private Double importeComisionActividad;
	private Double valorPorcentajeActividad;
	private Double valorPorcentajeComisionTramo1;
	private Double valorPorcentajeComisionTramo2;
	
	private String numeroDocumentoIdentidad;
	private Double cargoFraccionado;
	private Double facturadoTotal;
	private Double importeReclamo;
	private Double saldo;
	private Double ventaNetaEfectiva;
	private Double diferenciaVentaNetaEfectiva;
	private Double escalaInicial;
	private Double escalaFinal;
	private Double porcentajeComisionParametrizado;
	private Double porcentajeIncremento;
	private Double importeImpuesto;	
	private String vencimiento;
	
	/* INI SA PER-SICC-2012-0421 */
	private Double montoFacturado;
	private String demandaAnticipada;
	/* FIN SA PER-SICC-2012-0421 */
	
	/* INI SA CHI-SiCC-2012-0051 */
	private String simporteAntesLimiteTramo1;
	private String simporteNetoSinReclamo;
	private String smontoFacturado;
	private String scargoFraccionado;
	private String sfacturadoTotal;
	
	private String simporteImpuesto;
	private String simporteReclamo;
	private String ssaldo;
	private String sventaNetaEfectiva;
	private String sdiferenciaVentaNetaEfectiva;
	private String simporteComisionTramo1;
	/* FIN SA CHI-SiCC-2012-0051 */
	
	/**
	 * @return
	 */
	public Double getImporteComisionActividad() {
		return importeComisionActividad;
	}

	/**
	 * @param importeComisionActividad
	 */
	public void setImporteComisionActividad(Double importeComisionActividad) {
		this.importeComisionActividad = importeComisionActividad;
	}

	/**
	 * @return
	 */
	public Double getValorPorcentajeActividad() {
		return valorPorcentajeActividad;
	}

	/**
	 * @param valorPorcentajeActividad
	 */
	public void setValorPorcentajeActividad(Double valorPorcentajeActividad) {
		this.valorPorcentajeActividad = valorPorcentajeActividad;
	}

	/**
	 * @return
	 */
	public Double getValorPorcentajeComisionTramo1() {
		return valorPorcentajeComisionTramo1;
	}

	/**
	 * @param valorPorcentajeComisionTramo1
	 */
	public void setValorPorcentajeComisionTramo1(Double valorPorcentajeComisionTramo1) {
		this.valorPorcentajeComisionTramo1 = valorPorcentajeComisionTramo1;
	}

	/**
	 * @return
	 */
	public Double getValorPorcentajeComisionTramo2() {
		return valorPorcentajeComisionTramo2;
	}

	/**
	 * @param valorPorcentajeComisionTramo2
	 */
	public void setValorPorcentajeComisionTramo2(Double valorPorcentajeComisionTramo2) {
		this.valorPorcentajeComisionTramo2 = valorPorcentajeComisionTramo2;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComisionPeriodoGerenteZona [codigoPeriodo=" + codigoPeriodo
				+ ", codigoComision=" + codigoComision + ", codigoLiderZona="
				+ codigoLiderZona + ", nombreLiderZona=" + nombreLiderZona
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", tipoComision=" + tipoComision
				+ ", fechaCalculo=" + fechaCalculo + ", importeNetoSinReclamo="
				+ importeNetoSinReclamo + ", importeAntesLimiteTramo1="
				+ importeAntesLimiteTramo1 + ", importeAntesLimiteTramo2="
				+ importeAntesLimiteTramo2 + ", porcentajeRecuperacionTramo1="
				+ porcentajeRecuperacionTramo1
				+ ", porcentajeRecuperacionTramo2="
				+ porcentajeRecuperacionTramo2 + ", importeComisionTramo1="
				+ importeComisionTramo1 + ", importeComisionTramo2="
				+ importeComisionTramo2 + ", codigoPlanilla=" + codigoPlanilla
				+ ", descripcionRegion=" + descripcionRegion
				+ ", descripcionZona=" + descripcionZona
				+ ", importeSumaComision=" + importeSumaComision
				+ ", importeComisionActividad=" + importeComisionActividad
				+ ", valorPorcentajeActividad=" + valorPorcentajeActividad
				+ ", valorPorcentajeComisionTramo1="
				+ valorPorcentajeComisionTramo1
				+ ", valorPorcentajeComisionTramo2="
				+ valorPorcentajeComisionTramo2 + ", numeroDocumentoIdentidad="
				+ numeroDocumentoIdentidad + ", cargoFraccionado="
				+ cargoFraccionado + ", facturadoTotal=" + facturadoTotal
				+ ", importeReclamo=" + importeReclamo + ", saldo=" + saldo
				+ ", ventaNetaEfectiva=" + ventaNetaEfectiva
				+ ", diferenciaVentaNetaEfectiva="
				+ diferenciaVentaNetaEfectiva + ", escalaInicial="
				+ escalaInicial + ", escalaFinal=" + escalaFinal
				+ ", porcentajeComisionParametrizado="
				+ porcentajeComisionParametrizado + ", porcentajeIncremento="
				+ porcentajeIncremento + ", importeImpuesto=" + importeImpuesto
				+ ", vencimiento=" + vencimiento + ", montoFacturado="
				+ montoFacturado + ", demandaAnticipada=" + demandaAnticipada
				+ ", simporteAntesLimiteTramo1=" + simporteAntesLimiteTramo1
				+ ", simporteNetoSinReclamo=" + simporteNetoSinReclamo
				+ ", smontoFacturado=" + smontoFacturado
				+ ", scargoFraccionado=" + scargoFraccionado
				+ ", sfacturadoTotal=" + sfacturadoTotal
				+ ", simporteImpuesto=" + simporteImpuesto
				+ ", simporteReclamo=" + simporteReclamo + ", ssaldo=" + ssaldo
				+ ", sventaNetaEfectiva=" + sventaNetaEfectiva
				+ ", sdiferenciaVentaNetaEfectiva="
				+ sdiferenciaVentaNetaEfectiva + ", simporteComisionTramo1="
				+ simporteComisionTramo1 + "]";
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the codigoLiderZona.
	 */
	public String getCodigoLiderZona() {
		return codigoLiderZona;
	}

	/**
	 * @param codigoLiderZona The codigoLiderZona to set.
	 */
	public void setCodigoLiderZona(String codigoLiderZona) {
		this.codigoLiderZona = codigoLiderZona;
	}

	/**
	 * @return Returns the codigoPlanilla.
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}

	/**
	 * @param codigoPlanilla The codigoPlanilla to set.
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the fechaCalculo.
	 */
	public Date getFechaCalculo() {
		return fechaCalculo;
	}

	/**
	 * @param fechaCalculo The fechaCalculo to set.
	 */
	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

	/**
	 * @return Returns the importeAntesLimiteTramo1.
	 */
	public Double getImporteAntesLimiteTramo1() {
		return importeAntesLimiteTramo1;
	}

	/**
	 * @param importeAntesLimiteTramo1 The importeAntesLimiteTramo1 to set.
	 */
	public void setImporteAntesLimiteTramo1(Double importeAntesLimiteTramo1) {
		this.importeAntesLimiteTramo1 = importeAntesLimiteTramo1;
	}

	/**
	 * @return Returns the importeAntesLimiteTramo2.
	 */
	public Double getImporteAntesLimiteTramo2() {
		return importeAntesLimiteTramo2;
	}

	/**
	 * @param importeAntesLimiteTramo2 The importeAntesLimiteTramo2 to set.
	 */
	public void setImporteAntesLimiteTramo2(Double importeAntesLimiteTramo2) {
		this.importeAntesLimiteTramo2 = importeAntesLimiteTramo2;
	}

	
	
	
	/**
	 * @return Returns the importeComisionTramo1.
	 */
	public Double getImporteComisionTramo1() {
		return importeComisionTramo1;
	}

	/**
	 * @param importeComisionTramo1 The importeComisionTramo1 to set.
	 */
	public void setImporteComisionTramo1(Double importeComisionTramo1) {
		this.importeComisionTramo1 = importeComisionTramo1;
	}

	/**
	 * @return Returns the importeComisionTramo2.
	 */
	public Double getImporteComisionTramo2() {
		return importeComisionTramo2;
	}

	/**
	 * @param importeComisionTramo2 The importeComisionTramo2 to set.
	 */
	public void setImporteComisionTramo2(Double importeComisionTramo2) {
		this.importeComisionTramo2 = importeComisionTramo2;
	}

	/**
	 * @return Returns the importeNetoSinReclamo.
	 */
	public Double getImporteNetoSinReclamo() {
		return importeNetoSinReclamo;
	}

	/**
	 * @param importeNetoSinReclamo The importeNetoSinReclamo to set.
	 */
	public void setImporteNetoSinReclamo(Double importeNetoSinReclamo) {
		this.importeNetoSinReclamo = importeNetoSinReclamo;
	}

	/**
	 * @return Returns the nombreLiderZona.
	 */
	public String getNombreLiderZona() {
		return nombreLiderZona;
	}

	/**
	 * @param nombreLiderZona The nombreLiderZona to set.
	 */
	public void setNombreLiderZona(String nombreLiderZona) {
		this.nombreLiderZona = nombreLiderZona;
	}

	/**
	 * @return Returns the porcentajeRecuperacionTramo1.
	 */
	public Double getPorcentajeRecuperacionTramo1() {
		return porcentajeRecuperacionTramo1;
	}

	/**
	 * @param porcentajeRecuperacionTramo1 The porcentajeRecuperacionTramo1 to set.
	 */
	public void setPorcentajeRecuperacionTramo1(Double porcentajeRecuperacionTramo1) {
		this.porcentajeRecuperacionTramo1 = porcentajeRecuperacionTramo1;
	}

	/**
	 * @return Returns the porcentajeRecuperacionTramo2.
	 */
	public Double getPorcentajeRecuperacionTramo2() {
		return porcentajeRecuperacionTramo2;
	}

	/**
	 * @param porcentajeRecuperacionTramo2 The porcentajeRecuperacionTramo2 to set.
	 */
	public void setPorcentajeRecuperacionTramo2(Double porcentajeRecuperacionTramo2) {
		this.porcentajeRecuperacionTramo2 = porcentajeRecuperacionTramo2;
	}

	/**
	 * @return Returns the tipoComision.
	 */
	public String getTipoComision() {
		return tipoComision;
	}

	/**
	 * @param tipoComision The tipoComision to set.
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return Returns the importeSumaComision.
	 */
	public Double getImporteSumaComision() {
		return importeSumaComision;
	}

	/**
	 * @param importeSumaComision The importeSumaComision to set.
	 */
	public void setImporteSumaComision(Double importeSumaComision) {
		this.importeSumaComision = importeSumaComision;
	}
	
	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the cargoFraccionado
	 */
	public Double getCargoFraccionado() {
		return cargoFraccionado;
	}

	/**
	 * @param cargoFraccionado the cargoFraccionado to set
	 */
	public void setCargoFraccionado(Double cargoFraccionado) {
		this.cargoFraccionado = cargoFraccionado;
	}

	/**
	 * @return the facturadoTotal
	 */
	public Double getFacturadoTotal() {
		return facturadoTotal;
	}

	/**
	 * @param facturadoTotal the facturadoTotal to set
	 */
	public void setFacturadoTotal(Double facturadoTotal) {
		this.facturadoTotal = facturadoTotal;
	}

	/**
	 * @return the importeReclamo
	 */
	public Double getImporteReclamo() {
		return importeReclamo;
	}

	/**
	 * @param importeReclamo the importeReclamo to set
	 */
	public void setImporteReclamo(Double importeReclamo) {
		this.importeReclamo = importeReclamo;
	}

	/**
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the ventaNetaEfectiva
	 */
	public Double getVentaNetaEfectiva() {
		return ventaNetaEfectiva;
	}

	/**
	 * @param ventaNetaEfectiva the ventaNetaEfectiva to set
	 */
	public void setVentaNetaEfectiva(Double ventaNetaEfectiva) {
		this.ventaNetaEfectiva = ventaNetaEfectiva;
	}

	/**
	 * @return the diferenciaVentaNetaEfectiva
	 */
	public Double getDiferenciaVentaNetaEfectiva() {
		return diferenciaVentaNetaEfectiva;
	}

	/**
	 * @param diferenciaVentaNetaEfectiva the diferenciaVentaNetaEfectiva to set
	 */
	public void setDiferenciaVentaNetaEfectiva(Double diferenciaVentaNetaEfectiva) {
		this.diferenciaVentaNetaEfectiva = diferenciaVentaNetaEfectiva;
	}

	/**
	 * @return the escalaInicial
	 */
	public Double getEscalaInicial() {
		return escalaInicial;
	}

	/**
	 * @param escalaInicial the escalaInicial to set
	 */
	public void setEscalaInicial(Double escalaInicial) {
		this.escalaInicial = escalaInicial;
	}

	/**
	 * @return the escalaFinal
	 */
	public Double getEscalaFinal() {
		return escalaFinal;
	}

	/**
	 * @param escalaFinal the escalaFinal to set
	 */
	public void setEscalaFinal(Double escalaFinal) {
		this.escalaFinal = escalaFinal;
	}

	/**
	 * @return the porcentajeComisionParametrizado
	 */
	public Double getPorcentajeComisionParametrizado() {
		return porcentajeComisionParametrizado;
	}

	/**
	 * @param porcentajeComisionParametrizado the porcentajeComisionParametrizado to set
	 */
	public void setPorcentajeComisionParametrizado(
			Double porcentajeComisionParametrizado) {
		this.porcentajeComisionParametrizado = porcentajeComisionParametrizado;
	}

	/**
	 * @return the porcentajeIncremento
	 */
	public Double getPorcentajeIncremento() {
		return porcentajeIncremento;
	}

	/**
	 * @param porcentajeIncremento the porcentajeIncremento to set
	 */
	public void setPorcentajeIncremento(Double porcentajeIncremento) {
		this.porcentajeIncremento = porcentajeIncremento;
	}

	/**
	 * @return the importeImpuesto
	 */
	public Double getImporteImpuesto() {
		return importeImpuesto;
	}

	/**
	 * @param importeImpuesto the importeImpuesto to set
	 */
	public void setImporteImpuesto(Double importeImpuesto) {
		this.importeImpuesto = importeImpuesto;
	}

	/**
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param vencimiento the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	/**
	 * @return the montoFacturado
	 */
	public Double getMontoFacturado() {
		return montoFacturado;
	}

	/**
	 * @param montoFacturado the montoFacturado to set
	 */
	public void setMontoFacturado(Double montoFacturado) {
		this.montoFacturado = montoFacturado;
	}

	/**
	 * @return the demandaAnticipada
	 */
	public String getDemandaAnticipada() {
		return demandaAnticipada;
	}

	/**
	 * @param demandaAnticipada the demandaAnticipada to set
	 */
	public void setDemandaAnticipada(String demandaAnticipada) {
		this.demandaAnticipada = demandaAnticipada;
	}

	/**
	 * @return the simporteAntesLimiteTramo1
	 */
	public String getSimporteAntesLimiteTramo1() {
		return simporteAntesLimiteTramo1;
	}

	/**
	 * @param simporteAntesLimiteTramo1 the simporteAntesLimiteTramo1 to set
	 */
	public void setSimporteAntesLimiteTramo1(String simporteAntesLimiteTramo1) {
		this.simporteAntesLimiteTramo1 = simporteAntesLimiteTramo1;
	}

	/**
	 * @return the simporteNetoSinReclamo
	 */
	public String getSimporteNetoSinReclamo() {
		return simporteNetoSinReclamo;
	}

	/**
	 * @param simporteNetoSinReclamo the simporteNetoSinReclamo to set
	 */
	public void setSimporteNetoSinReclamo(String simporteNetoSinReclamo) {
		this.simporteNetoSinReclamo = simporteNetoSinReclamo;
	}

	/**
	 * @return the smontoFacturado
	 */
	public String getSmontoFacturado() {
		return smontoFacturado;
	}

	/**
	 * @param smontoFacturado the smontoFacturado to set
	 */
	public void setSmontoFacturado(String smontoFacturado) {
		this.smontoFacturado = smontoFacturado;
	}

	/**
	 * @return the scargoFraccionado
	 */
	public String getScargoFraccionado() {
		return scargoFraccionado;
	}

	/**
	 * @param scargoFraccionado the scargoFraccionado to set
	 */
	public void setScargoFraccionado(String scargoFraccionado) {
		this.scargoFraccionado = scargoFraccionado;
	}

	/**
	 * @return the sfacturadoTotal
	 */
	public String getSfacturadoTotal() {
		return sfacturadoTotal;
	}

	/**
	 * @param sfacturadoTotal the sfacturadoTotal to set
	 */
	public void setSfacturadoTotal(String sfacturadoTotal) {
		this.sfacturadoTotal = sfacturadoTotal;
	}

	/**
	 * @return the simporteImpuesto
	 */
	public String getSimporteImpuesto() {
		return simporteImpuesto;
	}

	/**
	 * @param simporteImpuesto the simporteImpuesto to set
	 */
	public void setSimporteImpuesto(String simporteImpuesto) {
		this.simporteImpuesto = simporteImpuesto;
	}

	/**
	 * @return the simporteReclamo
	 */
	public String getSimporteReclamo() {
		return simporteReclamo;
	}

	/**
	 * @param simporteReclamo the simporteReclamo to set
	 */
	public void setSimporteReclamo(String simporteReclamo) {
		this.simporteReclamo = simporteReclamo;
	}

	/**
	 * @return the ssaldo
	 */
	public String getSsaldo() {
		return ssaldo;
	}

	/**
	 * @param ssaldo the ssaldo to set
	 */
	public void setSsaldo(String ssaldo) {
		this.ssaldo = ssaldo;
	}

	/**
	 * @return the sventaNetaEfectiva
	 */
	public String getSventaNetaEfectiva() {
		return sventaNetaEfectiva;
	}

	/**
	 * @param sventaNetaEfectiva the sventaNetaEfectiva to set
	 */
	public void setSventaNetaEfectiva(String sventaNetaEfectiva) {
		this.sventaNetaEfectiva = sventaNetaEfectiva;
	}

	/**
	 * @return the sdiferenciaVentaNetaEfectiva
	 */
	public String getSdiferenciaVentaNetaEfectiva() {
		return sdiferenciaVentaNetaEfectiva;
	}

	/**
	 * @param sdiferenciaVentaNetaEfectiva the sdiferenciaVentaNetaEfectiva to set
	 */
	public void setSdiferenciaVentaNetaEfectiva(String sdiferenciaVentaNetaEfectiva) {
		this.sdiferenciaVentaNetaEfectiva = sdiferenciaVentaNetaEfectiva;
	}

	/**
	 * @return the simporteComisionTramo1
	 */
	public String getSimporteComisionTramo1() {
		return simporteComisionTramo1;
	}

	/**
	 * @param simporteComisionTramo1 the simporteComisionTramo1 to set
	 */
	public void setSimporteComisionTramo1(String simporteComisionTramo1) {
		this.simporteComisionTramo1 = simporteComisionTramo1;
	}	
	

}
