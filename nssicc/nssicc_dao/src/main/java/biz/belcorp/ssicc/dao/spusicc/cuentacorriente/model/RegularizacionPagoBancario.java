package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author JFA
 *
 */
public class RegularizacionPagoBancario extends AuditableBaseObject implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String oidMovimientoBancario;
	private String codigoPais;
	private String codigoSociedad;
	private String codigoBanco;
	private String codigoCuentaCorriente;
	private String codigoConsultora;	
	private String importePago;
	private String fechaPago;				
    
	public RegularizacionPagoBancario() {
	
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
	 * @return the oidMovimientoBancario
	 */
	public String getOidMovimientoBancario() {
		return oidMovimientoBancario;
	}

	/**
	 * @param oidMovimientoBancario the oidMovimientoBancario to set
	 */
	public void setOidMovimientoBancario(String oidMovimientoBancario) {
		this.oidMovimientoBancario = oidMovimientoBancario;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
    
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
    
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the codigoCuentaCorriente
	 */
	public String getCodigoCuentaCorriente() {
		return codigoCuentaCorriente;
	}

	/**
	 * @param codigoCuentaCorriente the codigoCuentaCorriente to set
	 */
	public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
		this.codigoCuentaCorriente = codigoCuentaCorriente;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the importePago
	 */
	public String getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago the importePago to set
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}


}