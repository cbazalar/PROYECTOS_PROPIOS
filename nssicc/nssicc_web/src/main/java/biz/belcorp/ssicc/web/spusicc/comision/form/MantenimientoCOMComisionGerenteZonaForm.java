package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMComisionGerenteZonaForm extends BaseEditForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108999428017945580L;
	private String codigoPeriodo;
	private String codigoComision;
	private String codigoLiderZona;
	private String nombreLiderZona;
	private String codigoRegion;
	private String codigoZona;
	private String tipoComision;
	private Date fechaCalculo;
	private String importeNetoSinReclamo;
	private String importeAntesLimiteTramo1;
	private String importeAntesLimiteTramo2;
	private String porcentajeRecuperacionTramo1;
	private String porcentajeRecuperacionTramo2;
	private String importeComisionTramo1;
	private String importeComisionTramo2;
	private String codigoPlanilla;
	private String descripcionRegion;
	private String descripcionZona;
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}
	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}
	/**
	 * @return the codigoLiderZona
	 */
	public String getCodigoLiderZona() {
		return codigoLiderZona;
	}
	/**
	 * @param codigoLiderZona the codigoLiderZona to set
	 */
	public void setCodigoLiderZona(String codigoLiderZona) {
		this.codigoLiderZona = codigoLiderZona;
	}
	/**
	 * @return the nombreLiderZona
	 */
	public String getNombreLiderZona() {
		return nombreLiderZona;
	}
	/**
	 * @param nombreLiderZona the nombreLiderZona to set
	 */
	public void setNombreLiderZona(String nombreLiderZona) {
		this.nombreLiderZona = nombreLiderZona;
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
	 * @return the tipoComision
	 */
	public String getTipoComision() {
		return tipoComision;
	}
	/**
	 * @param tipoComision the tipoComision to set
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}
	
	/**
	 * @return the fechaCalculo
	 */
	public Date getFechaCalculo() {
		return fechaCalculo;
	}
	/**
	 * @param fechaCalculo the fechaCalculo to set
	 */
	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}
	/**
	 * @return the importeNetoSinReclamo
	 */
	public String getImporteNetoSinReclamo() {
		return importeNetoSinReclamo;
	}
	/**
	 * @param importeNetoSinReclamo the importeNetoSinReclamo to set
	 */
	public void setImporteNetoSinReclamo(String importeNetoSinReclamo) {
		this.importeNetoSinReclamo = importeNetoSinReclamo;
	}
	/**
	 * @return the importeAntesLimiteTramo1
	 */
	public String getImporteAntesLimiteTramo1() {
		return importeAntesLimiteTramo1;
	}
	/**
	 * @param importeAntesLimiteTramo1 the importeAntesLimiteTramo1 to set
	 */
	public void setImporteAntesLimiteTramo1(String importeAntesLimiteTramo1) {
		this.importeAntesLimiteTramo1 = importeAntesLimiteTramo1;
	}
	/**
	 * @return the importeAntesLimiteTramo2
	 */
	public String getImporteAntesLimiteTramo2() {
		return importeAntesLimiteTramo2;
	}
	/**
	 * @param importeAntesLimiteTramo2 the importeAntesLimiteTramo2 to set
	 */
	public void setImporteAntesLimiteTramo2(String importeAntesLimiteTramo2) {
		this.importeAntesLimiteTramo2 = importeAntesLimiteTramo2;
	}
	/**
	 * @return the porcentajeRecuperacionTramo1
	 */
	public String getPorcentajeRecuperacionTramo1() {
		return porcentajeRecuperacionTramo1;
	}
	/**
	 * @param porcentajeRecuperacionTramo1 the porcentajeRecuperacionTramo1 to set
	 */
	public void setPorcentajeRecuperacionTramo1(String porcentajeRecuperacionTramo1) {
		this.porcentajeRecuperacionTramo1 = porcentajeRecuperacionTramo1;
	}
	/**
	 * @return the porcentajeRecuperacionTramo2
	 */
	public String getPorcentajeRecuperacionTramo2() {
		return porcentajeRecuperacionTramo2;
	}
	/**
	 * @param porcentajeRecuperacionTramo2 the porcentajeRecuperacionTramo2 to set
	 */
	public void setPorcentajeRecuperacionTramo2(String porcentajeRecuperacionTramo2) {
		this.porcentajeRecuperacionTramo2 = porcentajeRecuperacionTramo2;
	}
	/**
	 * @return the importeComisionTramo1
	 */
	public String getImporteComisionTramo1() {
		return importeComisionTramo1;
	}
	/**
	 * @param importeComisionTramo1 the importeComisionTramo1 to set
	 */
	public void setImporteComisionTramo1(String importeComisionTramo1) {
		this.importeComisionTramo1 = importeComisionTramo1;
	}
	/**
	 * @return the importeComisionTramo2
	 */
	public String getImporteComisionTramo2() {
		return importeComisionTramo2;
	}
	/**
	 * @param importeComisionTramo2 the importeComisionTramo2 to set
	 */
	public void setImporteComisionTramo2(String importeComisionTramo2) {
		this.importeComisionTramo2 = importeComisionTramo2;
	}
	/**
	 * @return the codigoPlanilla
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	/**
	 * @param codigoPlanilla the codigoPlanilla to set
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	
	
	

}
