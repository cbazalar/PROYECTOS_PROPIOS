package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoPERMovimientosBancariosSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/02/2015
 */
public class MantenimientoPERMovimientosBancariosSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -6887059628588280298L;

	private String codigoPais;
	
	private String codigoTipoOrigenDatos;
	
	private String numeroLoteInterno;
	
	private String fechaProceso;
	
	private Date fechaProcesoD;
	
	private String codigoSociedad;
	
	private String codigoBancoSicc;

	private String statusLote;

	/**
	 * @return Returns the codigoBancoSicc.
	 */
	public String getCodigoBancoSicc() {
		return codigoBancoSicc;
	}

	/**
	 * @param codigoBancoSicc The codigoBancoSicc to set.
	 */
	public void setCodigoBancoSicc(String codigoBancoSicc) {
		this.codigoBancoSicc = codigoBancoSicc;
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
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}

	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso
	 *            The fechaProceso to set.
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	

	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}

	/**
	 * @param numeroLote The numeroLoteInterno to set.
	 */
	public void setNumeroLoteInterno(String numeroLote) {
		this.numeroLoteInterno = numeroLote;
	}

	
	/**
	 * @return Returns the statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/**
	 * @param statusLote The statusLote to set.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
	
}
