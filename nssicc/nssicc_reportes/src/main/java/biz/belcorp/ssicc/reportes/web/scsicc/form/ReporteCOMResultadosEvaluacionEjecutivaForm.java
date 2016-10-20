package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMResultadosEvaluacionEjecutivaForm extends BaseReporteForm implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5030281038017084059L;

	private String codigoPais;
	
	private String descPais;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String anioInicial;
	
	private String codigoTramo;
	


	public ReporteCOMResultadosEvaluacionEjecutivaForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		
	}



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


}
