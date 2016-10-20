package biz.belcorp.ssicc.web.spncd.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCUPDespachoProductoSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -2958947544442557177L;
	private String id;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoPrograma;
	private String codigoNivel;
	private String codigoVenta;
	private String valorUnitario;
	private String descripcionProducto;
	private String codigoProducto;
	private String nivelPriorizacion;
	private String estadoRegistro;
	private String codigoValida; // 0: sin validar 1: valida Periodo, 2: valida
									// Codigo de Venta

	protected String[] selectedItems = {};
	protected String selectedItem = null;

	/* INI SA PER-SiCC--2012-0467 */
	private String indicadorKit;
	private boolean mostrarIndicadorKit;
	/* FIN SA PER-SiCC--2012-0467 */

	/* INI PER-SiCC-2012-1055 NVS */
	private String indicadorKitSegundoPedido;
	private boolean mostrarIndicadorSegundoKit;
	/* FINI PER-SiCC-2012-1055 NVS */

	private String indicadorPremioWeb;
	private boolean mostrarIndicadorPremioWeb;

	private String tipoDespacho;

	/**
	 * @return
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getNivelPriorizacion() {
		return nivelPriorizacion;
	}

	/**
	 * @param nivelPriorizacion
	 */
	public void setNivelPriorizacion(String nivelPriorizacion) {
		this.nivelPriorizacion = nivelPriorizacion;
	}

	/**
	 * @return
	 */
	public String getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario
	 */
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return
	 */
	public String getCodigoValida() {
		return codigoValida;
	}

	/**
	 * @param codigoValida
	 */
	public void setCodigoValida(String codigoValida) {
		this.codigoValida = codigoValida;
	}

	/**
	 * @return
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the indicadorKit
	 */
	public String getIndicadorKit() {
		return indicadorKit;
	}

	/**
	 * @param indicadorKit
	 *            the indicadorKit to set
	 */
	public void setIndicadorKit(String indicadorKit) {
		this.indicadorKit = indicadorKit;
	}

	/**
	 * @return the mostrarIndicadorKit
	 */
	public boolean isMostrarIndicadorKit() {
		return mostrarIndicadorKit;
	}

	/**
	 * @param mostrarIndicadorKit
	 *            the mostrarIndicadorKit to set
	 */
	public void setMostrarIndicadorKit(boolean mostrarIndicadorKit) {
		this.mostrarIndicadorKit = mostrarIndicadorKit;
	}

	/**
	 * @return the mostrarIndicadorSegundoKit
	 */
	public boolean isMostrarIndicadorSegundoKit() {
		return mostrarIndicadorSegundoKit;
	}

	/**
	 * @param mostrarIndicadorSegundoKit
	 *            the mostrarIndicadorSegundoKit to set
	 */
	public void setMostrarIndicadorSegundoKit(boolean mostrarIndicadorSegundoKit) {
		this.mostrarIndicadorSegundoKit = mostrarIndicadorSegundoKit;
	}

	/**
	 * @return the indicadorKitSegundoPedido
	 */
	public String getIndicadorKitSegundoPedido() {
		return indicadorKitSegundoPedido;
	}

	/**
	 * @param indicadorKitSegundoPedido
	 *            the indicadorKitSegundoPedido to set
	 */
	public void setIndicadorKitSegundoPedido(String indicadorKitSegundoPedido) {
		this.indicadorKitSegundoPedido = indicadorKitSegundoPedido;
	}

	/**
	 * @return the indicadorPremioWeb
	 */
	public String getIndicadorPremioWeb() {
		return indicadorPremioWeb;
	}

	/**
	 * @param indicadorPremioWeb
	 *            the indicadorPremioWeb to set
	 */
	public void setIndicadorPremioWeb(String indicadorPremioWeb) {
		this.indicadorPremioWeb = indicadorPremioWeb;
	}

	/**
	 * @return the mostrarIndicadorPremioWeb
	 */
	public boolean isMostrarIndicadorPremioWeb() {
		return mostrarIndicadorPremioWeb;
	}

	/**
	 * @param mostrarIndicadorPremioWeb
	 *            the mostrarIndicadorPremioWeb to set
	 */
	public void setMostrarIndicadorPremioWeb(boolean mostrarIndicadorPremioWeb) {
		this.mostrarIndicadorPremioWeb = mostrarIndicadorPremioWeb;
	}

	/**
	 * @return the tipoDespacho
	 */
	public String getTipoDespacho() {
		return tipoDespacho;
	}

	/**
	 * @param tipoDespacho
	 *            the tipoDespacho to set
	 */
	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
}