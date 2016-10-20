package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author JFA
 *
 */
public class ErrorPagoBancario extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidMovimientoBancario;
	private String descripcionCuentaCorrienteBancaria;
	private String descripcionError;
	private String codigoConsultora;
	private String fechaProceso;
	private String fechaPago;
	private String importePago;				
    
	public ErrorPagoBancario() {
	
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

	public String getOidMovimientoBancario() {
		return oidMovimientoBancario;
	}

	public void setOidMovimientoBancario(String oidMovimientoBancario) {
		this.oidMovimientoBancario = oidMovimientoBancario;
	}

	public String getDescripcionCuentaCorrienteBancaria() {
		return descripcionCuentaCorrienteBancaria;
	}

	public void setDescripcionCuentaCorrienteBancaria(
			String descripcionCuentaCorrienteBancaria) {
		this.descripcionCuentaCorrienteBancaria = descripcionCuentaCorrienteBancaria;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getImportePago() {
		return importePago;
	}

	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}		
	
	

}
