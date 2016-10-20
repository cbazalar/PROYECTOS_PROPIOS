/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoPEDProductoAsociadoSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7372857935223030349L;
	
	private String codigoPais;
	private String codigoSap;
	private String descripcion;
	private String oidCatalogo;
	private String paginaDesde;
	private String paginaHasta;
	private String codigoPeriodo;
	
	protected String[] asociadosSelectedItems = {};

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
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the paginaDesde
	 */
	public String getPaginaDesde() {
		return paginaDesde;
	}

	/**
	 * @param paginaDesde the paginaDesde to set
	 */
	public void setPaginaDesde(String paginaDesde) {
		this.paginaDesde = paginaDesde;
	}

	/**
	 * @return the paginaHasta
	 */
	public String getPaginaHasta() {
		return paginaHasta;
	}

	/**
	 * @param paginaHasta the paginaHasta to set
	 */
	public void setPaginaHasta(String paginaHasta) {
		this.paginaHasta = paginaHasta;
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
	 * @return the asociadosSelectedItems
	 */
	public String[] getAsociadosSelectedItems() {
		return asociadosSelectedItems;
	}

	/**
	 * @param asociadosSelectedItems the asociadosSelectedItems to set
	 */
	public void setAsociadosSelectedItems(String[] asociadosSelectedItems) {
		this.asociadosSelectedItems = asociadosSelectedItems;
	}

}
