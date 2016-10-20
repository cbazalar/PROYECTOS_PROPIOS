package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteCOBDetalladoGestionCobranzaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 20/11/2014
 */
public class ReporteCOBDetalladoGestionCobranzaForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoSociedad;
	
	private String codigoEtapaDeuda;
	
	private String tipoVista;
	
    private String codigoCobrador;	
	
	private String descripcionCobrador;
	
	private String codigoPeriodo;
	
	private String codigoGestion;	
	
	private String descripcionGestion;
	
	private String fechaInicioGestion;

	private String fechaFinGestion;
	
	private String indicadorGestion;	
	
	private String[] regionList;

	private String[] zonaList;	
	
	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private Date fechaInicioGestionD;

	private Date fechaFinGestionD;
		

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
	 * 
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**	 
	 * @param tipoVista
	 *            The tipoVista to set.
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return Returns the codigoGestion.
	 */
	public String getCodigoGestion() {
		return codigoGestion;
	}

	/**
	 * @param codigoGestion
	 *            The codigoGestion to set.
	 * 
	 */
	public void setCodigoGestion(String codigoGestion) {
		this.codigoGestion = codigoGestion;
	}
	
	/**
	 * @return Returns the descripcionGestion
	 */
	public String getDescripcionGestion() {
		return descripcionGestion;
	}

	/**
	 * @param descripcionGestion The descripcionGestion to set.
	 */
	public void setDescripcionGestion(String descripcionGestion) {
		this.descripcionGestion = descripcionGestion;
	}
	
	/**
	 * @return Returns the fechaInicioGestion.
	 */
	public String getFechaInicioGestion() {
		return fechaInicioGestion;
	}

	/**
	 * @param fechaInicioGestion
	 *            The fechaInicioGestion to set.
	 * 
	 */
	public void setFechaInicioGestion(String fechaInicioGestion) {
		this.fechaInicioGestion = fechaInicioGestion;
	}

	/**
	 * @return Returns the fechaFinGestion.
	 */
	public String getFechaFinGestion() {
		return fechaFinGestion;
	}

	/**
	 * @param fechaFinGestion
	 *            The fechaFinGestion to set.
	 * 
	 */
	public void setFechaFinGestion(String fechaFinGestion) {
		this.fechaFinGestion = fechaFinGestion;
	}

	
	/**
	 * @return Returns the indicadorGestion.
	 */
	public String getIndicadorGestion() {
		return indicadorGestion;
	}

	/**
	 * @param indicadorGestion
	 *            The indicadorGestion to set.
	 * 
	 */
	public void setIndicadorGestion(String indicadorGestion) {
		this.indicadorGestion = indicadorGestion;
	}
	
	
	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 * 
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
	 * @param zonaList
	 *            The zonaList to set.
	 * 
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
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
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
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
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}

	/**
	 * @return the fechaInicioGestionD
	 */
	public Date getFechaInicioGestionD() {
		return fechaInicioGestionD;
	}

	/**
	 * @param fechaInicioGestionD the fechaInicioGestionD to set
	 */
	public void setFechaInicioGestionD(Date fechaInicioGestionD) {
		this.fechaInicioGestionD = fechaInicioGestionD;
	}

	/**
	 * @return the fechaFinGestionD
	 */
	public Date getFechaFinGestionD() {
		return fechaFinGestionD;
	}

	/**
	 * @param fechaFinGestionD the fechaFinGestionD to set
	 */
	public void setFechaFinGestionD(Date fechaFinGestionD) {
		this.fechaFinGestionD = fechaFinGestionD;
	}	

}
