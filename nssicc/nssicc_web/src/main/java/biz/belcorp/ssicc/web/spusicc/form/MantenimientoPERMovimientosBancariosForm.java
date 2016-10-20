package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoPERMovimientosBancariosForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/02/2015
 */
public class MantenimientoPERMovimientosBancariosForm extends BaseEditForm {
	
	private static final long serialVersionUID = -5940212077078159374L;

	private String codigoPais;
	
	private String codigoTipoOrigenDatos;
	
	private String numeroLoteInterno;
	
	private String fechaProceso;
	
	private String codigoSociedad;
	
	private String codigoBancoSicc;
	
	private String consecutivo;
	
	private String tipoTransaccion;
	
	private String importePagoAplicado;
	
	private String importePago;
	
	private String fechaPago;
	
	private String codigoConsultora;
	
	private String descripcionConsultora;
	
	private String numeroFacturaBoleta;

	private String digitoChequeo;
	
	private String numeroCupon;	
	
	private String numeroDocumento;
	
	private String oficinaRecaudadora;
	
	private String nombreOficina;
	
	private String horario;
	
	private String observacion;
	
	protected String[] selectedItems = {};
	
	protected String selectedItem = null;
	
	private boolean modified = false; 
	
	

	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * @param modified The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}

	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	/**
	 * @return Returns the codigoBancoSicc.
	 */
	public String getCodigoBancoSicc() {
		return codigoBancoSicc;
	}

	/**
	 * @param codigoBancoSicc The codigoBancoSicc to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoBancoSicc(String codigoBancoSicc) {
		this.codigoBancoSicc = codigoBancoSicc;
	}


	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso The fechaProceso to set.
	 * @struts.validator type="required"
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}

	/**
	 * @param numeroLote The numeroLoteInterno to set.
	 * @struts.validator type="required"
	 */
	public void setNumeroLoteInterno(String numeroLote) {
		this.numeroLoteInterno = numeroLote;
	}

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return Returns the consecutivo.
	 */
	public String getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @param consecutivo The consecutivo to set.
	 */
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	/**
	 * @return Returns the digitoChequeo.
	 */
	public String getDigitoChequeo() {
		return digitoChequeo;
	}

	/**
	 * @param digitoChequeo The digitoChequeo to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,2}$"
	 */
	public void setDigitoChequeo(String digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
	}

	/**
	 * @return Returns the fechaPago.
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago The fechaPago to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return Returns the horario.
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario The horario to set.
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return Returns the nombreOficina.
	 */
	public String getNombreOficina() {
		return nombreOficina;
	}

	/**
	 * @param nombreOficina The nombreOficina to set.
	 */
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}

	/**
	 * @return Returns the numeroCupon.
	 */
	public String getNumeroCupon() {
		return numeroCupon;
	}

	/**
	 * @param numeroCupon The numeroCupon to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,8}$"
	 */
	public void setNumeroCupon(String numeroCupon) {
		this.numeroCupon = numeroCupon;
	}

	/**
	 * @return Returns the numeroFacturaBoleta.
	 */
	public String getNumeroFacturaBoleta() {
		return numeroFacturaBoleta;
	}

	/**
	 * @param numeroFacturaBoleta The numeroFacturaBoleta to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,8}$"
	 */
	public void setNumeroFacturaBoleta(String numeroFacturaBoleta) {
		this.numeroFacturaBoleta = numeroFacturaBoleta;
	}

	/**
	 * @return Returns the observacion.
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion The observacion to set.
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return Returns the oficinaRecaudadora.
	 */
	public String getOficinaRecaudadora() {
		return oficinaRecaudadora;
	}

	/**
	 * @param oficinaRecaudadora The oficinaRecaudadora to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,5}$"
	 */
	public void setOficinaRecaudadora(String oficinaRecaudadora) {
		this.oficinaRecaudadora = oficinaRecaudadora;
	}

	/**
	 * @return Returns the tipoTransaccion.
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * @param tipoTransaccion The tipoTransaccion to set.
	 * @struts.validator type="required"
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	/**
	 * @return Returns the importePago.
	 */
	public String getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago The importePago to set.
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,13}(.(\d{1,2}))?$"
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	
	
	
	public String getImportePagoAplicado() {
		return importePagoAplicado;
	}

	public void setImportePagoAplicado(String importePagoAplicado) {
		this.importePagoAplicado = importePagoAplicado;
	}

	
	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return Returns the selectedItems.
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems The selectedItems to set.
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	

	/**
	 * @return Returns the descripcionConsultora.
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora The descripcionConsultora to set.
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,15}$"
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
