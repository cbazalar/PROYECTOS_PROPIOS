package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar</a>
 * 
 */

public class ConsultaHIPPedidosForm extends BaseSearchForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5947832261390001605L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String estatus;
	
	private String campanaIngreso;
	private String selectedItem;
	
	private String oidSolicCabecera;
	
	private boolean mostrarLinks;
	
	private String montoPlansupera;
	
	/* INI SA PER-SiCC-2012-0765 */
	private String gastosAdministrativos;
	private String flete;
	private String flexipago;
	private String totalPedido;
	/* FIN SA PER-SiCC-2012-0765 */
	
	/* PER-SiCC-2013-0627 Consulta de Pedidos STO */
	private String codigoPais;
	private String codigoCampanya;
	private String codigoRegion;
	private String codigoZona;
	private String codigoVenta;
	private String codigoVentaLog;
	private String tipo;
	private String pantallaBusquedaActiva;
	/**/
	
	private String lineaDefecto;
	private String lineaMaxima;
	
	private String lineaDefectoPS;
	private String lineaMaximaPS;
	
	private String numeroPedidos;
	private String valorPromedio;
	
	private String codigoMarca;
	private String codigoCanal;
	
	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the campanaIngreso
	 */
	public String getCampanaIngreso() {
		return campanaIngreso;
	}
	/**
	 * @param campanaIngreso the campanaIngreso to set
	 */
	public void setCampanaIngreso(String campanaIngreso) {
		this.campanaIngreso = campanaIngreso;
	}
	/**
	 * @return the selectedItem
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return the oidSolicCabecera
	 */
	public String getOidSolicCabecera() {
		return oidSolicCabecera;
	}
	/**
	 * @param oidSolicCabecera the oidSolicCabecera to set
	 */
	public void setOidSolicCabecera(String oidSolicCabecera) {
		this.oidSolicCabecera = oidSolicCabecera;
	}
	/**
	 * @return the mostrarLinks
	 */
	public boolean isMostrarLinks() {
		return mostrarLinks;
	}
	/**
	 * @param mostrarLinks the mostrarLinks to set
	 */
	public void setMostrarLinks(boolean mostrarLinks) {
		this.mostrarLinks = mostrarLinks;
	}
	
	/**
	 * @return montoPlansupera
	 */
	public String getMontoPlansupera() {
		return montoPlansupera;
	}
	
	/**
	 * @param montoPlansupera
	 */
	public void setMontoPlansupera(String montoPlansupera) {
		this.montoPlansupera = montoPlansupera;
	}
	/**
	 * @return the gastosAdministrativos
	 */
	public String getGastosAdministrativos() {
		return gastosAdministrativos;
	}
	/**
	 * @param gastosAdministrativos the gastosAdministrativos to set
	 */
	public void setGastosAdministrativos(String gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}
	/**
	 * @return the flete
	 */
	public String getFlete() {
		return flete;
	}
	/**
	 * @param flete the flete to set
	 */
	public void setFlete(String flete) {
		this.flete = flete;
	}
	/**
	 * @return the flexipago
	 */
	public String getFlexipago() {
		return flexipago;
	}
	/**
	 * @param flexipago the flexipago to set
	 */
	public void setFlexipago(String flexipago) {
		this.flexipago = flexipago;
	}
	/**
	 * @return the totalPedido
	 */
	public String getTotalPedido() {
		return totalPedido;
	}
	/**
	 * @param totalPedido the totalPedido to set
	 */
	public void setTotalPedido(String totalPedido) {
		this.totalPedido = totalPedido;
	}
	/**
	 * @return the codigoCampanya
	 */
	public String getCodigoCampanya() {
		return codigoCampanya;
	}
	/**
	 * @param codigoCampanya the codigoCampanya to set
	 */
	public void setCodigoCampanya(String codigoCampanya) {
		this.codigoCampanya = codigoCampanya;
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
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return the codigoVentaLog
	 */
	public String getCodigoVentaLog() {
		return codigoVentaLog;
	}
	/**
	 * @param codigoVentaLog the codigoVentaLog to set
	 */
	public void setCodigoVentaLog(String codigoVentaLog) {
		this.codigoVentaLog = codigoVentaLog;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
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
	 * @return the pantallaBusquedaActiva
	 */
	public String getPantallaBusquedaActiva() {
		return pantallaBusquedaActiva;
	}
	/**
	 * @param pantallaBusquedaActiva the pantallaBusquedaActiva to set
	 */
	public void setPantallaBusquedaActiva(String pantallaBusquedaActiva) {
		this.pantallaBusquedaActiva = pantallaBusquedaActiva;
	}
	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}
	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}
	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}
	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}
	/**
	 * @return the lineaDefectoPS
	 */
	public String getLineaDefectoPS() {
		return lineaDefectoPS;
	}
	/**
	 * @param lineaDefectoPS the lineaDefectoPS to set
	 */
	public void setLineaDefectoPS(String lineaDefectoPS) {
		this.lineaDefectoPS = lineaDefectoPS;
	}
	/**
	 * @return the lineaMaximaPS
	 */
	public String getLineaMaximaPS() {
		return lineaMaximaPS;
	}
	/**
	 * @param lineaMaximaPS the lineaMaximaPS to set
	 */
	public void setLineaMaximaPS(String lineaMaximaPS) {
		this.lineaMaximaPS = lineaMaximaPS;
	}
	/**
	 * @return the numeroPedidos
	 */
	public String getNumeroPedidos() {
		return numeroPedidos;
	}
	/**
	 * @param numeroPedidos the numeroPedidos to set
	 */
	public void setNumeroPedidos(String numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}
	/**
	 * @return the valorPromedio
	 */
	public String getValorPromedio() {
		return valorPromedio;
	}
	/**
	 * @param valorPromedio the valorPromedio to set
	 */
	public void setValorPromedio(String valorPromedio) {
		this.valorPromedio = valorPromedio;
	}
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
}
