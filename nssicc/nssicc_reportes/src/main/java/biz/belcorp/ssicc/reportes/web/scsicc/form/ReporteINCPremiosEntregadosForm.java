package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCPremiosEntregadosForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String descPais;

	private String codigoMarca;

	private String codigoCanal;

	private Date fechaInicioFacturacion;
	
	private Date fechaFinFacturacion;
	
	private String[] codigoConcurso;	
	
	private String[] codigoTipoPrograma;
	
	private String codigoSAP;
	
	private String campoFiltro;
	
	private String[] codigoConsultora;
	
	public ReporteINCPremiosEntregadosForm() {
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

	public Date getFechaInicioFacturacion() {
		return fechaInicioFacturacion;
	}

	public void setFechaInicioFacturacion(Date fechaInicioFacturacion) {
		this.fechaInicioFacturacion = fechaInicioFacturacion;
	}

	public Date getFechaFinFacturacion() {
		return fechaFinFacturacion;
	}

	public void setFechaFinFacturacion(Date fechaFinFacturacion) {
		this.fechaFinFacturacion = fechaFinFacturacion;
	}

	public String[] getCodigoConcurso() {
		return codigoConcurso;
	}

	public void setCodigoConcurso(String[] codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	public String[] getCodigoTipoPrograma() {
		return codigoTipoPrograma;
	}

	public void setCodigoTipoPrograma(String[] codigoTipoPrograma) {
		this.codigoTipoPrograma = codigoTipoPrograma;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public String getCampoFiltro() {
		return campoFiltro;
	}

	public void setCampoFiltro(String campoFiltro) {
		this.campoFiltro = campoFiltro;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String[] getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String[] codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

}
