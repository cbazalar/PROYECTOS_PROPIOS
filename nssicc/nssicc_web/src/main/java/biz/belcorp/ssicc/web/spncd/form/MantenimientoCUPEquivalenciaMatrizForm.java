package biz.belcorp.ssicc.web.spncd.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCUPEquivalenciaMatrizForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String codigoPeriodo;

	private String codigoCupon;

	private String codigoNivel;

	private String codigoVenta;

	private String codigoProducto;

	private String descripcionProducto;

	private String valorUnitario;
	
	private boolean mostrarCodigoVentas;
	
	private boolean codigoVentaValido;
	
	private String valorPrioridad;
	
	protected String[] selectedItems = {};

	protected String selectedItem = null;
	
	private String indicadorEquivalenciaLove;
	private String indicadorDefaultLove;
	
	private String valorUnidadesMaximas;
	
	/* INI SA PER-SiCC--2012-0467 */
	private String indicadorKit;
	private boolean mostrarIndicadorKit;
	/* FIN SA PER-SiCC--2012-0467 */


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the codigoNivel.
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel The codigoNivel to set.
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return Returns the codigoCupon.
	 */
	public String getCodigoCupon() {
		return codigoCupon;
	}

	/**
	 * @param codigoCupon The codigoCupon to set.
	 */
	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	/**
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return Returns the valorUnitario.
	 */
	public String getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario The valorUnitario to set.
	 */
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return Returns the codigoVentaValido.
	 */
	public boolean isCodigoVentaValido() {
		return codigoVentaValido;
	}

	/**
	 * @param codigoVentaValido The codigoVentaValido to set.
	 */
	public void setCodigoVentaValido(boolean codigoVentaValido) {
		this.codigoVentaValido = codigoVentaValido;
	}

	/**
	 * @return Returns the mostrarCodigoVentas.
	 */
	public boolean isMostrarCodigoVentas() {
		return mostrarCodigoVentas;
	}

	/**
	 * @param mostrarCodigoVentaS The mostrarCodigoVentas to set.
	 */
	public void setMostrarCodigoVentas(boolean mostrarCodigoVentas) {
		this.mostrarCodigoVentas = mostrarCodigoVentas;
	}

	/**
	 * @return Returns the valorPrioridad.
	 */
	public String getValorPrioridad() {
		return valorPrioridad;
	}

	/**
	 * @param valorPrioridad The valorPrioridad to set.
	 * 
	 */
	public void setValorPrioridad(String valorPrioridad) {
		this.valorPrioridad = valorPrioridad;
	}

	/**
	 * @return the indicadorEquivalenciaLove
	 */
	public String getIndicadorEquivalenciaLove() {
		return indicadorEquivalenciaLove;
	}

	/**
	 * @param indicadorEquivalenciaLove the indicadorEquivalenciaLove to set
	 */
	public void setIndicadorEquivalenciaLove(String indicadorEquivalenciaLove) {
		this.indicadorEquivalenciaLove = indicadorEquivalenciaLove;
	}

	/**
	 * @return the indicadorDefaultLove
	 */
	public String getIndicadorDefaultLove() {
		return indicadorDefaultLove;
	}

	/**
	 * @param indicadorDefaultLove the indicadorDefaultLove to set
	 */
	public void setIndicadorDefaultLove(String indicadorDefaultLove) {
		this.indicadorDefaultLove = indicadorDefaultLove;
	}

	/**
	 * @return the valorUnidadesMaximas
	 */
	public String getValorUnidadesMaximas() {
		return valorUnidadesMaximas;
	}

	/**
	 * @param valorUnidadesMaximas the valorUnidadesMaximas to set
	 */
	public void setValorUnidadesMaximas(String valorUnidadesMaximas) {
		this.valorUnidadesMaximas = valorUnidadesMaximas;
	}

	/**
	 * @return the indicadorKit
	 */
	public String getIndicadorKit() {
		return indicadorKit;
	}

	/**
	 * @param indicadorKit the indicadorKit to set
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
	 * @param mostrarIndicadorKit the mostrarIndicadorKit to set
	 */
	public void setMostrarIndicadorKit(boolean mostrarIndicadorKit) {
		this.mostrarIndicadorKit = mostrarIndicadorKit;
	}
	
	

}
