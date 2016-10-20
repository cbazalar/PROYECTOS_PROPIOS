package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class BusquedaConsultoraSearchForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 12/02/2014
 */
public class ReporteRECMercaderiaSiniestradaForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String descripcionPais;

	private String tipoReporte;

	private String codigoCampanha;

	public String getCodigoPais() {
		return codigoPais;
	}

	public String getDescripcionPais() {
		return descripcionPais;
	}

	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @struts.validator type="required"
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getCodigoCampanha() {
		return codigoCampanha;
	}

	/**
	 * @struts.validator type="required"
	 */
	public void setCodigoCampanha(String codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
}