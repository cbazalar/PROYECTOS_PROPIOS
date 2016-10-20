package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaCOMComisionLideresSearchForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = -453498112861326419L;

	private String codigoPais;
	
	private String descPais;
	
	private String codigoComision;
	
	private String codigoComisionIngreso;
		
	private String codigoPeriodo;
	
	private String codigoRegion;
	
	private String codigoZona;
	

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescPais() {
		return descPais;
	}

	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	public String getCodigoComision() {
		return codigoComision;
	}

	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	public String getCodigoComisionIngreso() {
		return codigoComisionIngreso;
	}

	public void setCodigoComisionIngreso(String codigoComisionIngreso) {
		this.codigoComisionIngreso = codigoComisionIngreso;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	


}
