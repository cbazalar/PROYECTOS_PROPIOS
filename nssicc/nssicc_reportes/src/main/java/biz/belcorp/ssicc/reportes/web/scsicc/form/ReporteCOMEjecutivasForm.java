/**
 * 
 */
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMEjecutivasForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = -1689124803629866483L;

	private String codigoPais;

	private String anhoInicial;

	private String numeroTramo;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getAnhoInicial() {
		return anhoInicial;
	}

	public void setAnhoInicial(String anhoInicial) {
		this.anhoInicial = anhoInicial;
	}

	public String getNumeroTramo() {
		return numeroTramo;
	}

	public void setNumeroTramo(String numeroTramo) {
		this.numeroTramo = numeroTramo;
	}

}
