package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class reporteMAVConfiguracionPorCampanaForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7683916847707867283L;
	private String codigoPais;
	private String periodo;
	private String tipoCliente;
	private String tipoOferta;
	private String listaCliente;
	private String listaRegionZona;
	private String[] tipoOfertaList;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente
	 *            the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * @param tipoOferta
	 *            the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * @return the listaCliente
	 */
	public String getListaCliente() {
		return listaCliente;
	}

	/**
	 * @param listaCliente
	 *            the listaCliente to set
	 */
	public void setListaCliente(String listaCliente) {
		this.listaCliente = listaCliente;
	}

	/**
	 * @return the listaRegionZona
	 */
	public String getListaRegionZona() {
		return listaRegionZona;
	}

	/**
	 * @param listaRegionZona
	 *            the listaRegionZona to set
	 */
	public void setListaRegionZona(String listaRegionZona) {
		this.listaRegionZona = listaRegionZona;
	}

	/**
	 * @return the tipoOfertaList
	 */
	public String[] getTipoOfertaList() {
		return tipoOfertaList;
	}

	/**
	 * @param tipoOfertaList
	 *            the tipoOfertaList to set
	 */
	public void setTipoOfertaList(String[] tipoOfertaList) {
		this.tipoOfertaList = tipoOfertaList;
	}

}
