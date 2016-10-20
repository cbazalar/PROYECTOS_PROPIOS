package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRCargaPedidosDigitadosForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String periodo;
	private String txtnumPedidos="0";// numero de Items
	private String numPedidosRegistrados= "0";
	private String codigoConsultora;
	private String nombreConsultora;
	private String telefono;
	private String estatus;
	private String chkBloqueado;
	private String primerPedido;	
	private String ultimoPedido;	
	private String region;	
	private String zona;
	private String fechaFacturacion;
	private String valorCodigoVta;
	private String valorDescripcion;
	private String valorPrecioCat;
	private String valorUnidades;
	private String valorTotal;
	private String repinta="N";
	private String[] label;
	private String[] label2;
	private String[] label3;
	private String[] label4;
	private String[] label5;
	private int indice=0;
	private String totalUnid="0";
	private String totalTot="0";
	private String codigoPais;
	private String periodoSearch;
	private Date fechaFacturacionSearch;
	private String codigoPaisSearch;
	protected String[] selectedItems = {};
	
	private String numLote;
	
	private String chkFiltro;
	
	private String[] regionList;

	private String[] zonaList;
	
	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String monto;
	
	private String codRegion;
	private String codZona;
	
	private String indicador;
	
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
	 * @return Returns the label.
	 */
	public String[] getLabel() {
		return label;
	}
	/**
	 * @param label The label to set.
	 */
	public void setLabel(String[] label) {
		this.label = label;
	}
	/**
	 * @return Returns the repinta.
	 */
	public String getRepinta() {
		return repinta;
	}
	/**
	 * @param repinta The repinta to set.
	 */
	public void setRepinta(String repinta) {
		this.repinta = repinta;
	}
	/**
	 * @return Returns the chkBloqueado.
	 */
	public String getChkBloqueado() {
		return chkBloqueado;
	}
	/**
	 * @param chkBloqueado The chkBloqueado to set.
	 */
	public void setChkBloqueado(String chkBloqueado) {
		this.chkBloqueado = chkBloqueado;
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
	 * @return Returns the estatus.
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus The estatus to set.
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return Returns the nombreConsultora.
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora The nombreConsultora to set.
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return Returns the primerPedido.
	 */
	public String getPrimerPedido() {
		return primerPedido;
	}
	/**
	 * @param primerPedido The primerPedido to set.
	 */
	public void setPrimerPedido(String primerPedido) {
		this.primerPedido = primerPedido;
	}
	/**
	 * @return Returns the region.
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}
		
	/**
	 * @return Returns the ultimoPedido.
	 */
	public String getUltimoPedido() {
		return ultimoPedido;
	}
	/**
	 * @param ultimoPedido The ultimoPedido to set.
	 */
	public void setUltimoPedido(String ultimoPedido) {
		this.ultimoPedido = ultimoPedido;
	}
	/**
	 * @return Returns the zona.
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona The zona to set.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return Returns the valorCodigoVta.
	 */
	public String getValorCodigoVta() {
		return valorCodigoVta;
	}
	/**
	 * @param valorCodigoVta The valorCodigoVta to set.
	 */
	public void setValorCodigoVta(String valorCodigoVta) {
		this.valorCodigoVta = valorCodigoVta;
	}
	/**
	 * @return Returns the valorDescripcion.
	 */
	public String getValorDescripcion() {
		return valorDescripcion;
	}
	/**
	 * @param valorDescripcion The valorDescripcion to set.
	 */
	public void setValorDescripcion(String valorDescripcion) {
		this.valorDescripcion = valorDescripcion;
	}
	/**
	 * @return Returns the valorPrecioCat.
	 */
	public String getValorPrecioCat() {
		return valorPrecioCat;
	}
	/**
	 * @param valorPrecioCat The valorPrecioCat to set.
	 */
	public void setValorPrecioCat(String valorPrecioCat) {
		this.valorPrecioCat = valorPrecioCat;
	}
	/**
	 * @return Returns the valorTotal.
	 */
	public String getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal The valorTotal to set.
	 */
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	/**
	 * @return Returns the valorUnidades.
	 */
	public String getValorUnidades() {
		return valorUnidades;
	}
	/**
	 * @param valorUnidades The valorUnidades to set.
	 */
	public void setValorUnidades(String valorUnidades) {
		this.valorUnidades = valorUnidades;
	}
	/**
	 * @return Returns the label2.
	 */
	public String[] getLabel2() {
		return label2;
	}
	/**
	 * @param label2 The label2 to set.
	 */
	public void setLabel2(String[] label2) {
		this.label2 = label2;
	}
	/**
	 * @return Returns the label3.
	 */
	public String[] getLabel3() {
		return label3;
	}
	/**
	 * @param label3 The label3 to set.
	 */
	public void setLabel3(String[] label3) {
		this.label3 = label3;
	}
	/**
	 * @return Returns the label4.
	 */
	public String[] getLabel4() {
		return label4;
	}
	/**
	 * @param label4 The label4 to set.
	 */
	public void setLabel4(String[] label4) {
		this.label4 = label4;
	}
	/**
	 * @return Returns the label5.
	 */
	public String[] getLabel5() {
		return label5;
	}
	/**
	 * @param label5 The label5 to set.
	 */
	public void setLabel5(String[] label5) {
		this.label5 = label5;
	}
	/**
	 * @return Returns the indice.
	 */
	public int getIndice() {
		return indice;
	}
	/**
	 * @param indice The indice to set.
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
	/**
	 * @return Returns the txtnumPedidos.
	 */
	public String getTxtnumPedidos() {
		return txtnumPedidos;
	}
	/**
	 * @param txtnumPedidos The txtnumPedidos to set.
	 */
	public void setTxtnumPedidos(String txtnumPedidos) {
		this.txtnumPedidos = txtnumPedidos;
	}
	/**
	 * @return Returns the txtperiodo.
	 */
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * 	@param periodo The periodo to set.
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	/**
    * @param fechaFacturacion The fechaFacturacion to set.

	 */
	
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return Returns the totalTot.
	 */
	public String getTotalTot() {
		return totalTot;
	}
	/**
	 * @param totalTot The totalTot to set.
	 */
	public void setTotalTot(String totalTot) {
		this.totalTot = totalTot;
	}
	/**
	 * @return Returns the totalUnid.
	 */
	public String getTotalUnid() {
		return totalUnid;
	}
	/**
	 * @param totalUnid The totalUnid to set.
	 */
	public void setTotalUnid(String totalUnid) {
		this.totalUnid = totalUnid;
	}
	/**
	 * @return Returns the codigoPaisSearch.
	 */
	public String getCodigoPaisSearch() {
		return codigoPaisSearch;
	}
	/**
	 * @param codigoPaisSearch The codigoPaisSearch to set.
	 */
	public void setCodigoPaisSearch(String codigoPaisSearch) {
		this.codigoPaisSearch = codigoPaisSearch;
	}
	/**
	 * @return Returns the periodoSearch.
	 */
	public String getPeriodoSearch() {
		return periodoSearch;
	}
	/**
	 * @param periodoSearch The periodoSearch to set.
	 */
	public void setPeriodoSearch(String periodoSearch) {
		this.periodoSearch = periodoSearch;
	}
	/**
	 * @return Returns the txtnumPedidosRegistrados.
	 */
	public String getNumPedidosRegistrados() {
		return numPedidosRegistrados;
	}
	
	/**
	 * @param numPedidosRegistrados The numPedidosRegistrados to set.
	 */
	public void setNumPedidosRegistrados(String numPedidosRegistrados) {
		this.numPedidosRegistrados = numPedidosRegistrados;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return Returns the chkFiltro.
	 */
	public String getChkFiltro() {
		return chkFiltro;
	}
	/**
	 * @param chkFiltro The chkFiltro to set.
	 */
	public void setChkFiltro(String chkFiltro) {
		this.chkFiltro = chkFiltro;
	}
	
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}
	/**
	 * @param zonaList The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	/**
	 * @return Returns the monto.
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto The monto to set.
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	/**
	 * @return Returns the codRegion.
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion The codRegion to set.
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	/**
	 * @return Returns the codZona.
	 */
	public String getCodZona() {
		return codZona;
	}
	/**
	 * @param codZona The codZona to set.
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}
	/**
	 * @param indicador The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	/**
	 * @return the fechaFacturacionSearch
	 */
	public Date getFechaFacturacionSearch() {
		return fechaFacturacionSearch;
	}
	/**
	 * @param fechaFacturacionSearch the fechaFacturacionSearch to set
	 */
	public void setFechaFacturacionSearch(Date fechaFacturacionSearch) {
		this.fechaFacturacionSearch = fechaFacturacionSearch;
	}
}