package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaCOMComisionGerenteRegionSearchForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 4996132305781198115L;

	private String codigoPais;
	
	private String descPais;
	
	private String[] codigoComision = {};
		
	private String codigoPeriodoIni;
	
	private String codigoPeriodoFin;

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

	public String[] getCodigoComision() {
		return codigoComision;
	}

	public void setCodigoComision(String[] codigoComision) {
		this.codigoComision = codigoComision;
	}

	public String getCodigoPeriodoIni() {
		return codigoPeriodoIni;
	}

	public void setCodigoPeriodoIni(String codigoPeriodoIni) {
		this.codigoPeriodoIni = codigoPeriodoIni;
	}

	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
			

}
