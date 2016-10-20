package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * <p>
 * 
 * </p>
 * 
 * @author <a href="mailto:dhinostroza@belcorp.biz">David Hinostroza Vintes</a>
 */
public class AccesoForm   extends BaseEditForm {
	
	private static final long serialVersionUID = -4576013317373787587L;
	
	protected String codigo;
	protected String descripcion;
	protected boolean tipoAdministrador;
	protected String estado;
	protected String codigoOpcion;
	protected String codigoMenu;
	protected String codigoPais;
	protected String codigoRol;
	protected String descripcionRol;
	protected String tipoAdministradorRol;
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	protected String[][] checkbox;
	protected String codigoPaisUsuario;

	/**
	 * @return codigoPaisUsuario
	 */
	public String getCodigoPaisUsuario() {
		return codigoPaisUsuario;
	}
	
	/**
	 * @param codigoPaisUsuario
	 */
	public void setCodigoPaisUsuario(String codigoPaisUsuario) {
		this.codigoPaisUsuario = codigoPaisUsuario;
	}
	
	/**
	 * @return checkbox
	 */
	public String[][] getCheckbox() {
		return checkbox;
	}
	
	/**
	 * @param checkbox
	 */
	public void setCheckbox(String[][] checkbox) {
		this.checkbox = checkbox;
	}
	
	/**
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @return codigoMenu
	 */
	public String getCodigoMenu() {
		return codigoMenu;
	}
	
	/**
	 * @param codigoMenu
	 */
	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}
	
	/**
	 * @return codigoOpcion
	 */
	public String getCodigoOpcion() {
		return codigoOpcion;
	}
	
	/**
	 * @param codigoOpcion
	 */
	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	
	/**
	 * @return codigoPais
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
	 * @return codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}
	
	/**
	 * @param codigoRol
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	/**
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return tipoAdministrador
	 */
	public boolean isTipoAdministrador() {
		return tipoAdministrador;
	}
	
	/**
	 * @param tipoAdministrador
	 */
	public void setTipoAdministrador(boolean tipoAdministrador) {
		this.tipoAdministrador = tipoAdministrador;
	}
	
	/**
	 * @return descripcionRol
	 */
	public String getDescripcionRol() {
		return descripcionRol;
	}
	
	/**
	 * @param descripcionRol
	 */
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	
	/**
	 * @return tipoAdministradorRol
	 */
	public String getTipoAdministradorRol() {
		return tipoAdministradorRol;
	}
	
	/**
	 * @param tipoAdministradorRol
	 */
	public void setTipoAdministradorRol(String tipoAdministradorRol) {
		this.tipoAdministradorRol = tipoAdministradorRol;
	}
	
	/**
	 * @return selectedItem
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
	 * @return selectedItems
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

}
