package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
  * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 * 
 * @struts.form name = "reporteFLXDetalladoConsultorasHabilesForm" extends =
 *              "baseReporteForm"
 */
public class ReporteFLXDetalladoConsultorasHabilesForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoCampana;

	private String[] regionList;

	private String[] zonaList;
	
	private String[] seccionList;

	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String descripcionSeccionList;
	
	private String idiomaReporte;
	
	private String paisReporte;
	
	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoCampana = Constants.CODIGO_PERIODO_DEFAULT;
		if (StringUtils.isEmpty(this.codigoCampana))
			this.codigoCampana = periodo;
		
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoCampana() {
		return codigoCampana;
	}

	public void setCodigoCampana(String codigoCampana) {
		this.codigoCampana = codigoCampana;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}

	/**
	 * @return the idiomaReporte
	 */
	public String getIdiomaReporte() {
		return idiomaReporte;
	}

	/**
	 * @param idiomaReporte the idiomaReporte to set
	 */
	public void setIdiomaReporte(String idiomaReporte) {
		this.idiomaReporte = idiomaReporte;
	}

	/**
	 * @return the paisReporte
	 */
	public String getPaisReporte() {
		return paisReporte;
	}

	/**
	 * @param paisReporte the paisReporte to set
	 */
	public void setPaisReporte(String paisReporte) {
		this.paisReporte = paisReporte;
	}	

}