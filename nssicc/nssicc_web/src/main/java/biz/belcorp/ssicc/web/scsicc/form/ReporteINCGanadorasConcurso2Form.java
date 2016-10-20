package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCGanadorasConcurso2Form extends BaseReporteForm {
    private static final long serialVersionUID = 1L;

    private String codigoPais;
    private String codigoMarca;
    private String codigoCanal;
    private String oidConcurso;
    private String codigoTipoReporte;
    private String[] codigoRegion;
    private String[] codigoZona;
    private String codigoTerritorio;
    private String codigoConsultora;
    private String indicadorRegion;
    private String indicadorTerritorio;
    private String indicadorConsultora;

    private String descripcionRegion;
    private String descripcionZona;

    /**
     * @return the indicadorRegion
     */
    public String getIndicadorRegion() {
	return indicadorRegion;
    }

    /**
     * @param indicadorRegion
     *            the indicadorRegion to set
     */
    public void setIndicadorRegion(String indicadorRegion) {
	this.indicadorRegion = indicadorRegion;
    }

    /**
     * @return the indicadorTerritorio
     */
    public String getIndicadorTerritorio() {
	return indicadorTerritorio;
    }

    /**
     * @param indicadorTerritorio
     *            the indicadorTerritorio to set
     */
    public void setIndicadorTerritorio(String indicadorTerritorio) {
	this.indicadorTerritorio = indicadorTerritorio;
    }

    /**
     * @return the indicadorConsultora
     */
    public String getIndicadorConsultora() {
	return indicadorConsultora;
    }

    /**
     * @param indicadorConsultora
     *            the indicadorConsultora to set
     */
    public void setIndicadorConsultora(String indicadorConsultora) {
	this.indicadorConsultora = indicadorConsultora;
    }

//    public void reset(ActionMapping mapping, HttpServletRequest request) {
//	this.codigoRegion = this.codigoZona = null;
//	this.descripcionRegion = this.descripcionZona = null;
//	this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
//	this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
//	this.codigoTerritorio = this.codigoConsultora = "";
//	this.indicadorRegion = this.indicadorConsultora = this.indicadorTerritorio = Constants.NUMERO_CERO;
//    }

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

    /**
     * @return Returns the descripcionRegion.
     */
    public String getDescripcionRegion() {
	return descripcionRegion;
    }

    /**
     * @param descripcionRegion
     *            The descripcionRegion to set.
     */
    public void setDescripcionRegion(String descripcionRegion) {
	String temp = StringUtils.replace(descripcionRegion, "&&", "\n");
	this.descripcionRegion = temp;
    }

    /**
     * @return Returns the descripcionZona.
     */
    public String getDescripcionZona() {
	return descripcionZona;
    }

    /**
     * @param descripcionZona
     *            The descripcionZona to set.
     */
    public void setDescripcionZona(String descripcionZona) {
	String temp = StringUtils.replace(descripcionZona, "&&", "\n");
	this.descripcionZona = temp;
    }

    /**
     * @return the oidConcurso
     */
    public String getOidConcurso() {
	return oidConcurso;
    }

    /**
     * @param oidConcurso
     *            the oidConcurso to set
     * @struts.validator type="required"
     */
    public void setOidConcurso(String oidConcurso) {
	this.oidConcurso = oidConcurso;
    }

    /**
     * @return the codigoMarca
     */
    public String getCodigoMarca() {
	return codigoMarca;
    }

    /**
     * @param codigoMarca
     *            the codigoMarca to set
     * @struts.validator type="required"
     */
    public void setCodigoMarca(String codigoMarca) {
	this.codigoMarca = codigoMarca;
    }

    /**
     * @return the codigoCanal
     */
    public String getCodigoCanal() {
	return codigoCanal;
    }

    /**
     * @param codigoCanal
     *            the codigoCanal to set
     * @struts.validator type="required"
     */
    public void setCodigoCanal(String codigoCanal) {
	this.codigoCanal = codigoCanal;
    }

    /**
     * @return the codigoTipoReporte
     */
    public String getCodigoTipoReporte() {
	return codigoTipoReporte;
    }

    /**
     * @param codigoTipoReporte
     *            the codigoTipoReporte to set
     * @struts.validator type="required"
     */
    public void setCodigoTipoReporte(String codigoTipoReporte) {
	this.codigoTipoReporte = codigoTipoReporte;
    }

    /**
     * @return the codigoRegion
     */
    public String[] getCodigoRegion() {
	return codigoRegion;
    }

    /**
     * @param codigoRegion
     *            the codigoRegion to set
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
     * @param codigoZona
     *            the codigoZona to set
     */
    public void setCodigoZona(String[] codigoZona) {
	this.codigoZona = codigoZona;
    }

    /**
     * @return the codigoTerritorio
     */
    public String getCodigoTerritorio() {
	return codigoTerritorio;
    }

    /**
     * @param codigoTerritorio
     *            the codigoTerritorio to set
     */
    public void setCodigoTerritorio(String codigoTerritorio) {
	this.codigoTerritorio = codigoTerritorio;
    }

    /**
     * @return the codigoConsultora
     */
    public String getCodigoConsultora() {
	return codigoConsultora;
    }

    /**
     * @param codigoConsultora
     *            the codigoConsultora to set
     */
    public void setCodigoConsultora(String codigoConsultora) {
	this.codigoConsultora = codigoConsultora;
    }

}
