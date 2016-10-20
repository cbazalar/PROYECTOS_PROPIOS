package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCDiferenciaPreciosSearchForm extends BaseSearchForm  implements Serializable {
			 
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVenta;
	private String codigoSAP;
	
	private String descripcionSAP;
	
	private String []selectedItems; 
	private String precioUnitario;
	
	private String tipoOferta;  
	private String precioCorrecto;
	
	private String codigoRegion;
	private String codigoZona;
	
	private String codigoConsultora;
	private UploadedFile clienteFile;
	private String[] clienteList;
	
	private String codigosErradosFile;
	private int errados;
	
	/**
	 * @return the codigoPais
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
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	
	/**
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getDescripcionSAP() {
		return descripcionSAP;
	}
	public void setDescripcionSAP(String descripcionSAP) {
		this.descripcionSAP = descripcionSAP;
	}
	public String[] getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getTipoOferta() {
		return tipoOferta;
	}
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
	public String getPrecioCorrecto() {
		return precioCorrecto;
	}
	
	/**
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setPrecioCorrecto(String precioCorrecto) {
		this.precioCorrecto = precioCorrecto;
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
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
	/**
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}
	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}
	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	/**
	 * @return the errados
	 */
	public int getErrados() {
		return errados;
	}
	/**
	 * @param errados the errados to set
	 */
	public void setErrados(int errados) {
		this.errados = errados;
	}		
}