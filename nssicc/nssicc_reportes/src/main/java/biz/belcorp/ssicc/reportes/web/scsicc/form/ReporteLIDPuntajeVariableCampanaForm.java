package biz.belcorp.ssicc.reportes.web.scsicc.form;



import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteLIDPuntajeVariableCampanaForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String descPais;
	private String codigoTipoAsignacion;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
		
	private String[] codigoRegion;
	private String[] codigoZona;
	private String[] codigoSeccion;

	private String descripcionRegion;
	private String descripcionZona;
	private String descripcionSeccion;
	
	
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String[] getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String[] codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	

	public String getDescPais() {
		return descPais;
	}
	public void setDescPais(String descPais) {
		this.descPais = descPais;
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
	/**
	 * @return the codigoTipoAsignacion
	 */
	public String getCodigoTipoAsignacion() {
		return codigoTipoAsignacion;
	}
	/**
	 * @param codigoTipoAsignacion the codigoTipoAsignacion to set
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoAsignacion(String codigoTipoAsignacion) {
		this.codigoTipoAsignacion = codigoTipoAsignacion;
	}
	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the descripcionSeccion
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}
	/**
	 * @param descripcionSeccion the descripcionSeccion to set
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		String temp = StringUtils.replace(descripcionSeccion, "&&","\n" );
		this.descripcionSeccion = temp;
	}
	
}
