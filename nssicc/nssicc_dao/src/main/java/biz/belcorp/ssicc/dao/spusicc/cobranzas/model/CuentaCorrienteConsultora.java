package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class CuentaCorrienteConsultora extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fechaEmision;
	private String campanaReferencia;
	private String codigoTipoMovimiento;
	private String tipoMovimiento;
	private String oidMovimiento;
	private String oidConsolidado;
	private String numeroDocumento;
	private String fechaVencimiento;
	private String fechaPago;
	private String cargo; 
	private String abono;
	private String saldo;
	
	private Double dcargo;
	private Double dabono;
	private Double dsaldo;
	
	public CuentaCorrienteConsultora() {
	
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

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCampanaReferencia() {
		return campanaReferencia;
	}

	public void setCampanaReferencia(String campanaReferencia) {
		this.campanaReferencia = campanaReferencia;
	}

	/**
	 * @return the codigoTipoMovimiento
	 */
	public String getCodigoTipoMovimiento() {
		return codigoTipoMovimiento;
	}

	/**
	 * @param codigoTipoMovimiento the codigoTipoMovimiento to set
	 */
	public void setCodigoTipoMovimiento(String codigoTipoMovimiento) {
		this.codigoTipoMovimiento = codigoTipoMovimiento;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
    
	/**
	 * @return the oidMovimiento
	 */
	public String getOidMovimiento() {
		return oidMovimiento;
	}

	/**
	 * @param oidMovimiento the oidMovimiento to set
	 */
	public void setOidMovimiento(String oidMovimiento) {
		this.oidMovimiento = oidMovimiento;
	}

	public String getOidConsolidado() {
		return oidConsolidado;
	}

	public void setOidConsolidado(String oidConsolidado) {
		this.oidConsolidado = oidConsolidado;
	}

	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getAbono() {
		return abono;
	}

	public void setAbono(String abono) {
		this.abono = abono;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the dcargo
	 */
	public Double getDcargo() {
		return dcargo;
	}

	/**
	 * @param dcargo the dcargo to set
	 */
	public void setDcargo(Double dcargo) {
		this.dcargo = dcargo;
	}

	/**
	 * @return the dabono
	 */
	public Double getDabono() {
		return dabono;
	}

	/**
	 * @param dabono the dabono to set
	 */
	public void setDabono(Double dabono) {
		this.dabono = dabono;
	}

	/**
	 * @return the dsaldo
	 */
	public Double getDsaldo() {
		return dsaldo;
	}

	/**
	 * @param dsaldo the dsaldo to set
	 */
	public void setDsaldo(Double dsaldo) {
		this.dsaldo = dsaldo;
	}
	
}
