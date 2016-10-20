package biz.belcorp.ssicc.reportes.web.scsicc.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCForm extends BaseReporteForm{

	private static final long serialVersionUID = 2403125844795246121L;

	private String oidPais;
	
	private String codigoPais;
	
	private String descPais;
	
	private Long oidMarca;
	
	private String codigoMarca;

	private Long oidCanal;
	
	private String codigoCanal;

	private String codigoPeriodo;

	private String codigoConcurso;
	
	private String descConcurso;
	
	private String tipoReporte;

	public ReporteINCForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	}
	

	public String getOidPais() {
		return oidPais;
	}
	
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
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
	
	public Long getOidMarca() {
		return oidMarca;
	}


	public void setOidMarca(Long oidMarca) {
		this.oidMarca = oidMarca;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	

	public Long getOidCanal() {
		return oidCanal;
	}


	public void setOidCanal(Long oidCanal) {
		this.oidCanal = oidCanal;
	}


	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	public String getDescConcurso() {
		return descConcurso;
	}


	public void setDescConcurso(String descConcurso) {
		this.descConcurso = descConcurso;
	}


	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	

}
