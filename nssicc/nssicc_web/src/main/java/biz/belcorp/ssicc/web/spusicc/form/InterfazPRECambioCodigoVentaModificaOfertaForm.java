package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
public class InterfazPRECambioCodigoVentaModificaOfertaForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618374606637192847L;
	private String codigoPais;
	private String codigoCatalogo;
	private String codigoFormaPago;
	private String codigoRegion;
	private String codigoZona;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String oidEstatus;
	private String indicadorCatalogo;
	private String indicadorFormaPago;
	private String codigoPeriodo;
	private String indicadorResecuenciar;
	private String codigoPaisExportar;
	private String[] selectedItems = {};
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoCatalogo
	 */
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	/**
	 * @param codigoCatalogo the codigoCatalogo to set
	 */
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	/**
	 * @return the codigoFormaPago
	 */
	public String getCodigoFormaPago() {
		return codigoFormaPago;
	}
	/**
	 * @param codigoFormaPago the codigoFormaPago to set
	 */
	public void setCodigoFormaPago(String codigoFormaPago) {
		this.codigoFormaPago = codigoFormaPago;
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
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}
	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}
	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}
	/**
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}
	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}
	/**
	 * @return the indicadorCatalogo
	 */
	public String getIndicadorCatalogo() {
		return indicadorCatalogo;
	}
	/**
	 * @param indicadorCatalogo the indicadorCatalogo to set
	 */
	public void setIndicadorCatalogo(String indicadorCatalogo) {
		this.indicadorCatalogo = indicadorCatalogo;
	}
	/**
	 * @return the indicadorFormaPago
	 */
	public String getIndicadorFormaPago() {
		return indicadorFormaPago;
	}
	/**
	 * @param indicadorFormaPago the indicadorFormaPago to set
	 */
	public void setIndicadorFormaPago(String indicadorFormaPago) {
		this.indicadorFormaPago = indicadorFormaPago;
	}
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
	 * @return the indicadorResecuenciar
	 */
	public String getIndicadorResecuenciar() {
		return indicadorResecuenciar;
	}
	/**
	 * @param indicadorResecuenciar the indicadorResecuenciar to set
	 */
	public void setIndicadorResecuenciar(String indicadorResecuenciar) {
		this.indicadorResecuenciar = indicadorResecuenciar;
	}
	/**
	 * @return the codigoPaisExportar
	 */
	public String getCodigoPaisExportar() {
		return codigoPaisExportar;
	}
	/**
	 * @param codigoPaisExportar the codigoPaisExportar to set
	 */
	public void setCodigoPaisExportar(String codigoPaisExportar) {
		this.codigoPaisExportar = codigoPaisExportar;
	}
	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	

}
