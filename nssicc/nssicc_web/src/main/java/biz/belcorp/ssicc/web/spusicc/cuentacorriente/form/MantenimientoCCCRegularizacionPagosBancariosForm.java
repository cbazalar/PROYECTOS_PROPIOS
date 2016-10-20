package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


public class MantenimientoCCCRegularizacionPagosBancariosForm extends BaseEditForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String codigoPais;	
	private String codigoSociedad;
	private String codigoBanco;
	private String codigoCuentaCorriente;	
	private String oidMovimientoBancario;
	private String codigoConsultora;
	private String fechaPago;
	private String importePago;
	
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *            The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	/**
	 * @return Returns the codigoBanco.
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return Returns the codigoCuentaCorriente.
	 */
	public String getCodigoCuentaCorriente() {
		return codigoCuentaCorriente;
	}

	/**
	 * @param codigoCuentaCorriente
	 *            The codigoCuentaCorriente to set.
	 */
	public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
		this.codigoCuentaCorriente = codigoCuentaCorriente;
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
	 * @return Returns the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * 
	 * @param fechaPago
	 *            The fechaPago to set.
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
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
}