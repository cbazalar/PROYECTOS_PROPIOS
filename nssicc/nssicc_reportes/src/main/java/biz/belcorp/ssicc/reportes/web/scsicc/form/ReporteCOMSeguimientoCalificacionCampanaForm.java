package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMSeguimientoCalificacionCampanaForm extends BaseReporteForm
	implements Serializable {

	private static final long serialVersionUID = 3795939268395846520L;

	private String codigoPais;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String anioInicial;
	
	private String codigoTramo;
	
	private String tipoComisionista;

	public ReporteCOMSeguimientoCalificacionCampanaForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.tipoComisionista = Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT;
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

	public String getAnioInicial() {
		return anioInicial;
	}

	public void setAnioInicial(String anioInicial) {
		this.anioInicial = anioInicial;
	}

	public String getCodigoTramo() {
		return codigoTramo;
	}

	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	public String getTipoComisionista() {
		return tipoComisionista;
	}

	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	
}
