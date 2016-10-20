package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteFLXGestionServicioSACForm extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4442608586183306196L;

	private String codigoPais;
	private String codigoCampana;
	private String codigoCampanaFin;
	private String[] regionList;
	private String[] zonaList;		
	private String descripcionRegionList;
	private String descripcionZonaList;
	
	
	public ReporteFLXGestionServicioSACForm()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		
		if (StringUtils.isEmpty(this.codigoCampana))
		{
			this.codigoCampana = periodo;
			this.codigoCampanaFin= periodo;
		}			
	}
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoCampana() {
		return codigoCampana;
	}
	public void setCodigoCampana(String codigoCampana) {
		this.codigoCampana = codigoCampana;
	}
	public String getCodigoCampanaFin() {
		return codigoCampanaFin;
	}
	public void setCodigoCampanaFin(String codigoCampanaFin) {
		this.codigoCampanaFin = codigoCampanaFin;
	}
	public String[] getRegionList() {
		return regionList;
	}
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	public String[] getZonaList() {
		return zonaList;
	}
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}
	
	
	

}
