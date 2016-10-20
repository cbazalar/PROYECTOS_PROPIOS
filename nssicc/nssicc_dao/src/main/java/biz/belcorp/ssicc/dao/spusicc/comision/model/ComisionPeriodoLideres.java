package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
public class ComisionPeriodoLideres extends AuditableBaseObject implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8308292583654826905L;
	private String codigoPeriodo;
	private String codigoComision;
	private String codigoComisionIngreso;
	private String codigoLiderSeccion;
	private String nombreLiderSeccion;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private Double importeNetoSinReclamo;
	private Double importeAntesLimiteTramo1;
	private Double importeAntesLimiteTramo2;
	private Double porcentajeRecuperacionTramo1;
	private Double porcentajeRecuperacionTramo2;
	private Double importeComisionTramo1;
	private Double importeComisionTramo2;
	private String descripcionRegion;
	private String descripcionZona;
	private Double importeRetribucionTotal;
	private Double importeVentaIngreso;
	private Double importeComisionIngreso;
	private Double importeComisionIngresoAnterior;
	private Double importeComisionTotal;
	
	
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
	 * @return Returns the codigoLiderSeccion.
	 */
	public String getCodigoLiderSeccion() {
		return codigoLiderSeccion;
	}

	/**
	 * @param codigoLiderSeccion The codigoLiderSeccion to set.
	 */
	public void setCodigoLiderSeccion(String codigoLiderSeccion) {
		this.codigoLiderSeccion = codigoLiderSeccion;
	}

	/**
	 * @return Returns the codigoSeccion.
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion The codigoSeccion to set.
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return Returns the nombreLiderSeccion.
	 */
	public String getNombreLiderSeccion() {
		return nombreLiderSeccion;
	}

	/**
	 * @param nombreLiderSeccion The nombreLiderSeccion to set.
	 */
	public void setNombreLiderSeccion(String nombreLiderSeccion) {
		this.nombreLiderSeccion = nombreLiderSeccion;
	}

	/**
	 * @return Returns the importeComisionIngreso.
	 */
	public Double getImporteComisionIngreso() {
		return importeComisionIngreso;
	}

	/**
	 * @param importeComisionIngreso The importeComisionIngreso to set.
	 */
	public void setImporteComisionIngreso(Double importeComisionIngreso) {
		this.importeComisionIngreso = importeComisionIngreso;
	}

	/**
	 * @return Returns the importeComisionIngresoAnterior.
	 */
	public Double getImporteComisionIngresoAnterior() {
		return importeComisionIngresoAnterior;
	}

	/**
	 * @param importeComisionIngresoAnterior The importeComisionIngresoAnterior to set.
	 */
	public void setImporteComisionIngresoAnterior(
			Double importeComisionIngresoAnterior) {
		this.importeComisionIngresoAnterior = importeComisionIngresoAnterior;
	}

	/**
	 * @return Returns the importeComisionTotal.
	 */
	public Double getImporteComisionTotal() {
		return importeComisionTotal;
	}

	/**
	 * @param importeComisionTotal The importeComisionTotal to set.
	 */
	public void setImporteComisionTotal(Double importeComisionTotal) {
		this.importeComisionTotal = importeComisionTotal;
	}

	/**
	 * @return Returns the importeRetribucionTotal.
	 */
	public Double getImporteRetribucionTotal() {
		return importeRetribucionTotal;
	}

	/**
	 * @param importeRetribucionTotal The importeRetribucionTotal to set.
	 */
	public void setImporteRetribucionTotal(Double importeRetribucionTotal) {
		this.importeRetribucionTotal = importeRetribucionTotal;
	}

	/**
	 * @return Returns the importeVentaIngreso.
	 */
	public Double getImporteVentaIngreso() {
		return importeVentaIngreso;
	}

	/**
	 * @param importeVentaIngreso The importeVentaIngreso to set.
	 */
	public void setImporteVentaIngreso(Double importeVentaIngreso) {
		this.importeVentaIngreso = importeVentaIngreso;
	}

	/**
	 * @return Returns the codigoComisionIngreso.
	 */
	public String getCodigoComisionIngreso() {
		return codigoComisionIngreso;
	}

	/**
	 * @param codigoComisionIngreso The codigoComisionIngreso to set.
	 */
	public void setCodigoComisionIngreso(String codigoComisionIngreso) {
		this.codigoComisionIngreso = codigoComisionIngreso;
	}
	
	
	
	

}
