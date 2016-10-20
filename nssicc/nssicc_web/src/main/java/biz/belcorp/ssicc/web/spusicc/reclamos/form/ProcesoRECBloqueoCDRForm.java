package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoRECBloqueoCDRForm extends BaseProcesoForm
implements Serializable {
	
	private static final long serialVersionUID = -2863755775405971344L;
	
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String periodo;
	private String codigoConsultora;

	
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String[] getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	
}
