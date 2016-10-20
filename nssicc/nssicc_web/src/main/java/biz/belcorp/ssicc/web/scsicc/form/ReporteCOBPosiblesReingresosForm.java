package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBPosiblesReingresosForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String campanaPrimerPedidoDesde;
	private String campanaPrimerPedidoHasta;
	private String campanaUltimoPedidoDesde;
	private String campanaUltimoPedidoHasta;
	private String importeDeudaDesde;
	private String importeDeudaHasta;
	private String[] estatus;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String[] codigoSeccion;
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
	 * @return the campanaPrimerPedidoDesde
	 */
	public String getCampanaPrimerPedidoDesde() {
		return campanaPrimerPedidoDesde;
	}
	/**
	 * @param campanaPrimerPedidoDesde the campanaPrimerPedidoDesde to set
	 */
	public void setCampanaPrimerPedidoDesde(String campanaPrimerPedidoDesde) {
		this.campanaPrimerPedidoDesde = campanaPrimerPedidoDesde;
	}
	/**
	 * @return the campanaPrimerPedidoHasta
	 */
	public String getCampanaPrimerPedidoHasta() {
		return campanaPrimerPedidoHasta;
	}
	/**
	 * @param campanaPrimerPedidoHasta the campanaPrimerPedidoHasta to set
	 */
	public void setCampanaPrimerPedidoHasta(String campanaPrimerPedidoHasta) {
		this.campanaPrimerPedidoHasta = campanaPrimerPedidoHasta;
	}
	/**
	 * @return the campanaUltimoPedidoDesde
	 */
	public String getCampanaUltimoPedidoDesde() {
		return campanaUltimoPedidoDesde;
	}
	/**
	 * @param campanaUltimoPedidoDesde the campanaUltimoPedidoDesde to set
	 */
	public void setCampanaUltimoPedidoDesde(String campanaUltimoPedidoDesde) {
		this.campanaUltimoPedidoDesde = campanaUltimoPedidoDesde;
	}
	/**
	 * @return the campanaUltimoPedidoHasta
	 */
	public String getCampanaUltimoPedidoHasta() {
		return campanaUltimoPedidoHasta;
	}
	/**
	 * @param campanaUltimoPedidoHasta the campanaUltimoPedidoHasta to set
	 */
	public void setCampanaUltimoPedidoHasta(String campanaUltimoPedidoHasta) {
		this.campanaUltimoPedidoHasta = campanaUltimoPedidoHasta;
	}
	/**
	 * @return the importeDeudaDesde
	 */
	public String getImporteDeudaDesde() {
		return importeDeudaDesde;
	}
	/**
	 * @param importeDeudaDesde the importeDeudaDesde to set
	 */
	public void setImporteDeudaDesde(String importeDeudaDesde) {
		this.importeDeudaDesde = importeDeudaDesde;
	}
	/**
	 * @return the importeDeudaHasta
	 */
	public String getImporteDeudaHasta() {
		return importeDeudaHasta;
	}
	/**
	 * @param importeDeudaHasta the importeDeudaHasta to set
	 */
	public void setImporteDeudaHasta(String importeDeudaHasta) {
		this.importeDeudaHasta = importeDeudaHasta;
	}
	/**
	 * @return the estatus
	 */
	public String[] getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String[] estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String[] getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String[] codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
}