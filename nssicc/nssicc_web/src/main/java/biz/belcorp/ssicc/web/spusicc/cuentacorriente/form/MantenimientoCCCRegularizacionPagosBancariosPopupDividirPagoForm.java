package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCRegularizacionPagosBancariosPopupDividirPagoForm extends BaseSearchForm  implements Serializable{
			 
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;					
	private String tipoError;
	private String fechaPago;
	private String cuentaCorriente;
	private String importePago;
	
	private String numeroDecimales;
	
	private String[] listNumeroRegistro = {};
	private String[] listCodigoConsultora = {};
	private String[] listCedulaConsultora = {};
	private String[] listNombreConsultora = {};
	private String[] listImportePagoDetalle = {};
	
	private String numeroRegistro;
	private String codigoConsultora;
	private String cedulaConsultora;
	private String nombreConsultora;
	private String importePagoDetalle;
	
	private String indicadorCerrar;
				
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the tipoError
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * @param tipoError the tipoError to set
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
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

	/**
	 * @return the cuentaCorriente
	 */
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}

	/**
	 * @param cuentaCorriente the cuentaCorriente to set
	 */
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
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
	 * @return the listCodigoConsultora
	 */
	public String[] getListCodigoConsultora() {
		return listCodigoConsultora;
	}

	/**
	 * @param listCodigoConsultora the listCodigoConsultora to set
	 */
	public void setListCodigoConsultora(String[] listCodigoConsultora) {
		this.listCodigoConsultora = listCodigoConsultora;
	}

	/**
	 * @return the listCedulaConsultora
	 */
	public String[] getListCedulaConsultora() {
		return listCedulaConsultora;
	}

	/**
	 * @param listCedulaConsultora the listCedulaConsultora to set
	 */
	public void setListCedulaConsultora(String[] listCedulaConsultora) {
		this.listCedulaConsultora = listCedulaConsultora;
	}

	/**
	 * @return the listNombreConsultora
	 */
	public String[] getListNombreConsultora() {
		return listNombreConsultora;
	}

	/**
	 * @param listNombreConsultora the listNombreConsultora to set
	 */
	public void setListNombreConsultora(String[] listNombreConsultora) {
		this.listNombreConsultora = listNombreConsultora;
	}

	/**
	 * @return the listImportePagoDetalle
	 */
	public String[] getListImportePagoDetalle() {
		return listImportePagoDetalle;
	}

	/**
	 * @param listImportePagoDetalle the listImportePagoDetalle to set
	 */
	public void setListImportePagoDetalle(String[] listImportePagoDetalle) {
		this.listImportePagoDetalle = listImportePagoDetalle;
	}

	/**
	 * @return the listNumeroRegistro
	 */
	public String[] getListNumeroRegistro() {
		return listNumeroRegistro;
	}

	/**
	 * @param listNumeroRegistro the listNumeroRegistro to set
	 */
	public void setListNumeroRegistro(String[] listNumeroRegistro) {
		this.listNumeroRegistro = listNumeroRegistro;
	}

	/**
	 * @return the numeroDecimales
	 */
	public String getNumeroDecimales() {
		return numeroDecimales;
	}

	/**
	 * @param numeroDecimales the numeroDecimales to set
	 */
	public void setNumeroDecimales(String numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	/**
	 * @return the indicadorCerrar
	 */
	public String getIndicadorCerrar() {
		return indicadorCerrar;
	}

	/**
	 * @param indicadorCerrar the indicadorCerrar to set
	 */
	public void setIndicadorCerrar(String indicadorCerrar) {
		this.indicadorCerrar = indicadorCerrar;
	}

	/**
	 * @return the numeroRegistro
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
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
	 * @return the cedulaConsultora
	 */
	public String getCedulaConsultora() {
		return cedulaConsultora;
	}

	/**
	 * @param cedulaConsultora the cedulaConsultora to set
	 */
	public void setCedulaConsultora(String cedulaConsultora) {
		this.cedulaConsultora = cedulaConsultora;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the importePagoDetalle
	 */
	public String getImportePagoDetalle() {
		return importePagoDetalle;
	}

	/**
	 * @param importePagoDetalle the importePagoDetalle to set
	 */
	public void setImportePagoDetalle(String importePagoDetalle) {
		this.importePagoDetalle = importePagoDetalle;
	}
}