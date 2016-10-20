package biz.belcorp.ssicc.web.spncd.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;



public class MantenimientoCUPProgramaPeriodoForm extends BaseEditForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String codigoPeriodo;

	private String estadoProgramaPeriodo;
	
	private String nivel;

	private String[] cuponesAsignados;

	private String[] cuponesNoAsignados;
	
	private String valorUnidad;
	
	private String valorUnidadPremioElectivo;

	protected String[] selectedItems = {};

	protected String selectedItem = null;
	
	private String indicadorCuponReutilizable;

	/**
	 * @return Returns the codigoPais.
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * 
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * 
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
	 * 
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return Returns the estadoProgramaPeriodo.
	 */
	public String getEstadoProgramaPeriodo() {
		return estadoProgramaPeriodo;
	}

	/**
	 * @param estadoProgramaPeriodo The estadoProgramaPeriodo to set.
	 */
	public void setEstadoProgramaPeriodo(String estadoProgramaPeriodo) {
		this.estadoProgramaPeriodo = estadoProgramaPeriodo;
	}

	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @return the valorUnidadPremioElectivo
	 */
	public String getValorUnidadPremioElectivo() {
		return valorUnidadPremioElectivo;
	}

	/**
	 * @param valorUnidadPremioElectivo the valorUnidadPremioElectivo to set
	 */
	public void setValorUnidadPremioElectivo(String valorUnidadPremioElectivo) {
		this.valorUnidadPremioElectivo = valorUnidadPremioElectivo;
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
	 * @return Returns the cuponesAsignados.
	 */
	public String[] getCuponesAsignados() {
		return cuponesAsignados;
	}

	/**
	 * @param cuponesAsignados The cuponesAsignados to set.
	 */
	public void setCuponesAsignados(String[] cuponesAsignados) {
		this.cuponesAsignados = cuponesAsignados;
	}

	/**
	 * @return Returns the cuponesNoAsignados.
	 */
	public String[] getCuponesNoAsignados() {
		return cuponesNoAsignados;
	}

	/**
	 * @param cuponesNoAsignados The cuponesNoAsignados to set.
	 */
	public void setCuponesNoAsignados(String[] cuponesNoAsignados) {
		this.cuponesNoAsignados = cuponesNoAsignados;
	}

	/**
	 * @return Returns the nivel.
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel The nivel to set.
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return Returns the valorUnidad.
	 */
	public String getValorUnidad() {
		return valorUnidad;
	}

	/**
	 * @param valorUnidad The valorUnidad to set.
	 */
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	/**
	 * @return the indicadorCuponReutilizable
	 */
	public String getIndicadorCuponReutilizable() {
		return indicadorCuponReutilizable;
	}

	/**
	 * @param indicadorCuponReutilizable the indicadorCuponReutilizable to set
	 */
	public void setIndicadorCuponReutilizable(String indicadorCuponReutilizable) {
		this.indicadorCuponReutilizable = indicadorCuponReutilizable;
	}	
}