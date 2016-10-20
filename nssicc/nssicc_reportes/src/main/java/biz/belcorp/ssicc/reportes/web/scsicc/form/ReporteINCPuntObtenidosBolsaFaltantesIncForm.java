package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCPuntObtenidosBolsaFaltantesIncForm extends BaseReporteForm
implements Serializable {

	private static final long serialVersionUID = -3715016105931368668L;
	private String codigoPais;
	private String descPais;
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String[] codigoConcurso;	
	private String[] codigoRegion;
	private String descripcionRegion;
	private String[] codigoZona;
	private String descripcionZona;
	private String detalleTipoReporte;
	private String tipoFaltante;
	
	private String periodoInicio;
	private String periodoFin;
	
	
	public ReporteINCPuntObtenidosBolsaFaltantesIncForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
		
	}


	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}


	/**
	 * @param codigoCanal The codigoCanal to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}


	/**
	 * @return Returns the codigoConcurso.
	 */
	public String[] getCodigoConcurso() {
		return codigoConcurso;
	}


	/**
	 * @param codigoConcurso The codigoConcurso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoConcurso(String[] codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}


	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	/**
	 * @param codigoMarca The codigoMarca to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}


	public String getDescPais() {
		return descPais;
	}


	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}


	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}


	/**
	 * @return Returns the codigoZona.
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}


	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}


	/**
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}


	/**
	 * @param descripcionCanal The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}


	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}


	/**
	 * @param descripcionMarca The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}


	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}


	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		String temp = StringUtils.replace(descripcionRegion, "&&","\n" );
		this.descripcionRegion = temp;
	}


	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}


	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		String temp = StringUtils.replace(descripcionZona, "&&","\n" );
		this.descripcionZona = temp;
	}


	public String getDetalleTipoReporte() {
		return detalleTipoReporte;
	}

	/**
	 * @param detalleTipoReporte The detalleTipoReporte to set.
	 * @struts.validator type = "required"
	 */
	public void setDetalleTipoReporte(String detalleTipoReporte) {
		this.detalleTipoReporte = detalleTipoReporte;
	}

	/**
	 * @return the tipoFaltante
	 */
	public String getTipoFaltante() {
		return tipoFaltante;
	}

	/**
	 * @param tipoFaltante the tipoFaltante to set
	 */
	public void setTipoFaltante(String tipoFaltante) {
		this.tipoFaltante = tipoFaltante;
	}


	/**
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}


	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}


	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}


	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
}
