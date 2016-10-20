package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
public class ReporteCOBDetalladoRebajasCarteraForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;

	private String codigoEtapaDeuda;
	
    private String codigoPeriodoInicial;
	
	private String codigoPeriodoFinal;
					
	private String[] regionList;

	private String[] zonaList;
	
	private String descripcionRegionList;

	private String descripcionZonaList;
			

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
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
	 * @param codigoSociedad
	 *            The CodigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
		
	/**
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	
	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal The codigoPeriodoFinal to set.
	 * 
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
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
	
}
