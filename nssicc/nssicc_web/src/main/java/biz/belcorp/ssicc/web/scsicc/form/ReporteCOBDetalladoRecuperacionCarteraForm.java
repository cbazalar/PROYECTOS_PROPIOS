package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteCOBDetalladoRecuperacionCarteraForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 17/11/2014
 */
public class ReporteCOBDetalladoRecuperacionCarteraForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;
		
	private String codigoSociedad;
	
	private String codigoEtapaDeuda;
	
	private String codigoRegion;
	
	private String descripcionRegion;

	private String codigoZona;	
		
	private String descripcionZona;
	
	private String codigoCobrador;	
	
	private String descripcionCobrador;
			
	private String tipoVista;
	
	private String vistaReporte;
	

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * 
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoSociedad
	 *            The CodigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	/**
	 * @return Returns the codigoEtapaDeuda.
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda
	 *            The codigoEtapaDeuda to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}

	
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
			
	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            The codigoZona to set.
	 * 
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	
	/**
	 * @return Returns the codigoCobrador.
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}

	/**
	 * @param codigoCobrador
	 *            The codigoCobrador to set.
	 * 
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}
	
	/**
	 * @return Returns the descripcionCobrador
	 */
	public String getDescripcionCobrador() {
		return descripcionCobrador;
	}

	/**
	 * @param descripcionCobrador The descripcionCobrador to set.
	 */
	public void setDescripcionCobrador(String descripcionCobrador) {
		this.descripcionCobrador = descripcionCobrador;
	}
	

		
	/**
	 * 
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**	 
	 * @param tipoVista
	 *            The tipoVista to set.
	 * @struts.validator type="required"
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	* 
	* @return Returns the vistaReporte.
	*/
	public String getVistaReporte() {
		return vistaReporte;
	}
	
	/**	 
	* @param vistaReporte
	*            The vistaReporte to set.
	* @struts.validator type="required"
	*/
	public void setVistaReporte(String vistaReporte) {
		this.vistaReporte = vistaReporte;
	}
}
