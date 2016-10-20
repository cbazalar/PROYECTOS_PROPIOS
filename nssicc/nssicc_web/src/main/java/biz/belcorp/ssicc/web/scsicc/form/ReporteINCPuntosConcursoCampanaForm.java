package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCPuntosConcursoCampanaForm extends BaseReporteForm
	implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoPais;

    private String codigoMarca;

    private String descripcionMarca;

    private String codigoCanal;

    private String descripcionCanal;

    private String codigoConcurso;

    /**
     * @return Returns the codigoCanal.
     */
    public String getCodigoCanal() {
	return codigoCanal;
    }

    /**
     * @param codigoCanal
     *            The codigoCanal to set.
     * @struts.validator type = "required"
     */
    public void setCodigoCanal(String codigoCanal) {
	this.codigoCanal = codigoCanal;
    }

    /**
     * @return Returns the codigoConcurso.
     */
    public String getCodigoConcurso() {
	return codigoConcurso;
    }

    /**
     * @param codigoConcurso
     *            The codigoConcurso to set.
     * @struts.validator type = "required"
     */
    public void setCodigoConcurso(String codigoConcurso) {
	this.codigoConcurso = codigoConcurso;
    }

    /**
     * @return Returns the codigoMarca.
     */
    public String getCodigoMarca() {
	return codigoMarca;
    }

    /**
     * @param codigoMarca
     *            The codigoMarca to set.
     * @struts.validator type = "required"
     */
    public void setCodigoMarca(String codigoMarca) {
	this.codigoMarca = codigoMarca;
    }

    /**
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
	return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
     * @struts.validator type = "required"
     */
    public void setCodigoPais(String codigoPais) {
	this.codigoPais = codigoPais;
    }

    /**
     * @return Returns the descripcionCanal.
     */
    public String getDescripcionCanal() {
	return descripcionCanal;
    }

    /**
     * @param descripcionCanal
     *            The descripcionCanal to set.
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
     * @param descripcionMarca
     *            The descripcionMarca to set.
     */
    public void setDescripcionMarca(String descripcionMarca) {
	this.descripcionMarca = descripcionMarca;
    }
}
