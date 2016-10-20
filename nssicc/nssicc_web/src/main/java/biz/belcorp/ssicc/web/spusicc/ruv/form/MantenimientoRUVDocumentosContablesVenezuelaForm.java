package biz.belcorp.ssicc.web.spusicc.ruv.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRUVDocumentosContablesVenezuelaForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoTipoDocumentoContable;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoSubacceso;
	private String serie;
	private String fechaDesde;
	private String fechaHasta;
	private Date fechaDesdeDate;
	private Date fechaHastaDate;
	
	private String minimo;
	private String maximo;
	private String diferenciaMaxMin;
	private String totalRegistros;	
	private String accion;
	
	private String indicadorPorDias;
	private String indicadorPorRango;
	private String documentoLegalInicial;
	private String documentoLegalFinal;
	
	private String consultaAsignarResult;
	
	private String numeroDocumentoLegalInicial;
	private String numeroDocumentoLegalFinal;
	private String numeroFilasLegal;

	private String numeroDocumentoInternoInicial;
	private String numeroDocumentoInternoFinal;
	private String numeroFilasInterno;
	
	private String indicadorActDocCon;
	private String minimoControl;
	private String maximoControl;
	private String diferenciaMaxMinControl;
	private String totalRegistrosControl;	
	private String numeroControlInicial;
	private String numeroControlFinal;
	private String numeroFilasControl;
	private String descripcionTipoDocumento;
	private String descripcionCanal;
	private String descripcionAcceso;
	private String descripcionSubacceso;
	private String[] numeroControl;
	
	/**
	 * @return numeroDocumentoInternoInicial
	 */
	public String getNumeroDocumentoInternoInicial() {
		return numeroDocumentoInternoInicial;
	}

	/**
	 * @param numeroDocumentoInternoInicial
	 */
	public void setNumeroDocumentoInternoInicial(String numeroDocumentoInternoInicial) {
		this.numeroDocumentoInternoInicial = numeroDocumentoInternoInicial;
	}

	/**
	 * @return numeroDocumentoInternoFinal
	 */
	public String getNumeroDocumentoInternoFinal() {
		return numeroDocumentoInternoFinal;
	}

	/**
	 * @param numeroDocumentoInternoFinal
	 */
	public void setNumeroDocumentoInternoFinal(String numeroDocumentoInternoFinal) {
		this.numeroDocumentoInternoFinal = numeroDocumentoInternoFinal;
	}

	/**
	 * @return numeroFilasInterno
	 */
	public String getNumeroFilasInterno() {
		return numeroFilasInterno;
	}

	/**
	 * @param numeroFilasInterno
	 */
	public void setNumeroFilasInterno(String numeroFilasInterno) {
		this.numeroFilasInterno = numeroFilasInterno;
	}

	/**
	 * @return numeroDocumentoLegalInicial
	 */
	public String getNumeroDocumentoLegalInicial() {
		return numeroDocumentoLegalInicial;
	}
	
	/**
	 * @param numeroDocumentoLegalInicial
	 */
	public void setNumeroDocumentoLegalInicial(String numeroDocumentoLegalInicial) {
		this.numeroDocumentoLegalInicial = numeroDocumentoLegalInicial;
	}
	
	/**
	 * @return numeroDocumentoLegalFinal
	 */
	public String getNumeroDocumentoLegalFinal() {
		return numeroDocumentoLegalFinal;
	}
	
	/**
	 * @param numeroDocumentoLegalFinal
	 */
	public void setNumeroDocumentoLegalFinal(String numeroDocumentoLegalFinal) {
		this.numeroDocumentoLegalFinal = numeroDocumentoLegalFinal;
	}
	
	/**
	 * @return numeroFilasLegal
	 */
	public String getNumeroFilasLegal() {
		return numeroFilasLegal;
	}
	
	/**
	 * @param numeroFilasLegal
	 */
	public void setNumeroFilasLegal(String numeroFilasLegal) {
		this.numeroFilasLegal = numeroFilasLegal;
	}
	
	/**
	 * @return codigoPais
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
	 * @return the codigoTipoDocumentoContable
	 */
	public String getCodigoTipoDocumentoContable() {
		return codigoTipoDocumentoContable;
	}
	/**
	 * @param codigoTipoDocumentoContable the codigoTipoDocumentoContable to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoDocumentoContable(String codigoTipoDocumentoContable) {
		this.codigoTipoDocumentoContable = codigoTipoDocumentoContable;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso the codigoAcceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	/**
	 * @return the codigoSubacceso
	 */
	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}
	/**
	 * @param codigoSubacceso the codigoSubacceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}
	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie the serie to set
	 * @struts.validator type = "required"
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 * @struts.validator type = "required"
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 * @struts.validator type = "required"
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * @return the minimo
	 */
	public String getMinimo() {
		return minimo;
	}
	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}
	/**
	 * @return the maximo
	 */
	public String getMaximo() {
		return maximo;
	}
	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}
	/**
	 * @return the diferenciaMaxMin
	 */
	public String getDiferenciaMaxMin() {
		return diferenciaMaxMin;
	}
	/**
	 * @param diferenciaMaxMin the diferenciaMaxMin to set
	 */
	public void setDiferenciaMaxMin(String diferenciaMaxMin) {
		this.diferenciaMaxMin = diferenciaMaxMin;
	}
	/**
	 * @return the totalRegistros
	 */
	public String getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * @return the indicadorPorDias
	 */
	public String getIndicadorPorDias() {
		return indicadorPorDias;
	}
	/**
	 * @param indicadorPorDias the indicadorPorDias to set
	 */
	public void setIndicadorPorDias(String indicadorPorDias) {
		this.indicadorPorDias = indicadorPorDias;
	}
	/**
	 * @return the indicadorPorRango
	 */
	public String getIndicadorPorRango() {
		return indicadorPorRango;
	}
	/**
	 * @param indicadorPorRango the indicadorPorRango to set
	 */
	public void setIndicadorPorRango(String indicadorPorRango) {
		this.indicadorPorRango = indicadorPorRango;
	}
	/**
	 * @return the documentoLegalInicial
	 */
	public String getDocumentoLegalInicial() {
		return documentoLegalInicial;
	}
	/**
	 * @param documentoLegalInicial the documentoLegalInicial to set
	 */
	public void setDocumentoLegalInicial(String documentoLegalInicial) {
		this.documentoLegalInicial = documentoLegalInicial;
	}
	/**
	 * @return the documentoLegalFinal
	 */
	public String getDocumentoLegalFinal() {
		return documentoLegalFinal;
	}
	/**
	 * @param documentoLegalFinal the documentoLegalFinal to set
	 */
	public void setDocumentoLegalFinal(String documentoLegalFinal) {
		this.documentoLegalFinal = documentoLegalFinal;
	}
	/**
	 * @return the consultaAsignarResult
	 */
	public String getConsultaAsignarResult() {
		return consultaAsignarResult;
	}
	/**
	 * @param consultaAsignarResult the consultaAsignarResult to set
	 */
	public void setConsultaAsignarResult(String consultaAsignarResult) {
		this.consultaAsignarResult = consultaAsignarResult;
	}
	
	/**
	 * @return the indicadorActDocCon
	 */
	public String getIndicadorActDocCon() {
		return indicadorActDocCon;
	}

	/**
	 * @param indicadorActDocCon the indicadorActDocCon to set
	 */
	public void setIndicadorActDocCon(String indicadorActDocCon) {
		this.indicadorActDocCon = indicadorActDocCon;
	}

	/**
	 * @return the minimoControl
	 */
	public String getMinimoControl() {
		return minimoControl;
	}

	/**
	 * @param minimoControl the minimoControl to set
	 */
	public void setMinimoControl(String minimoControl) {
		this.minimoControl = minimoControl;
	}

	/**
	 * @return the maximoControl
	 */
	public String getMaximoControl() {
		return maximoControl;
	}

	/**
	 * @param maximoControl the maximoControl to set
	 */
	public void setMaximoControl(String maximoControl) {
		this.maximoControl = maximoControl;
	}

	/**
	 * @return the diferenciaMaxMinControl
	 */
	public String getDiferenciaMaxMinControl() {
		return diferenciaMaxMinControl;
	}

	/**
	 * @param diferenciaMaxMinControl the diferenciaMaxMinControl to set
	 */
	public void setDiferenciaMaxMinControl(String diferenciaMaxMinControl) {
		this.diferenciaMaxMinControl = diferenciaMaxMinControl;
	}

	/**
	 * @return the totalRegistrosControl
	 */
	public String getTotalRegistrosControl() {
		return totalRegistrosControl;
	}

	/**
	 * @param totalRegistrosControl the totalRegistrosControl to set
	 */
	public void setTotalRegistrosControl(String totalRegistrosControl) {
		this.totalRegistrosControl = totalRegistrosControl;
	}

	/**
	 * @return the numeroControlInicial
	 */
	public String getNumeroControlInicial() {
		return numeroControlInicial;
	}

	/**
	 * @param numeroControlInicial the numeroControlInicial to set
	 */
	public void setNumeroControlInicial(String numeroControlInicial) {
		this.numeroControlInicial = numeroControlInicial;
	}

	/**
	 * @return the numeroControlFinal
	 */
	public String getNumeroControlFinal() {
		return numeroControlFinal;
	}
	
	/**
	 * @return the numeroFilasControl
	 */
	public String getNumeroFilasControl() {
		return numeroFilasControl;
	}

	/**
	 * @param numeroFilasControl the numeroFilasControl to set
	 */
	public void setNumeroFilasControl(String numeroFilasControl) {
		this.numeroFilasControl = numeroFilasControl;
	}

	/**
	 * @param numeroControlFinal the numeroControlFinal to set
	 */
	public void setNumeroControlFinal(String numeroControlFinal) {
		this.numeroControlFinal = numeroControlFinal;
	}
	
	/**
	 * @return the descripcionTipoDocumento
	 */
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}

	/**
	 * @param descripcionTipoDocumento the descripcionTipoDocumento to set
	 */
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}

	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return the descripcionAcceso
	 */
	public String getDescripcionAcceso() {
		return descripcionAcceso;
	}

	/**
	 * @param descripcionAcceso the descripcionAcceso to set
	 */
	public void setDescripcionAcceso(String descripcionAcceso) {
		this.descripcionAcceso = descripcionAcceso;
	}

	/**
	 * @return the descripcionSubacceso
	 */
	public String getDescripcionSubacceso() {
		return descripcionSubacceso;
	}

	/**
	 * @param descripcionSubacceso the descripcionSubacceso to set
	 */
	public void setDescripcionSubacceso(String descripcionSubacceso) {
		this.descripcionSubacceso = descripcionSubacceso;
	}

	/**
	 * @return the numeroControl
	 */
	public String[] getNumeroControl() {
		return numeroControl;
	}

	/**
	 * @param numeroControl the numeroControl to set
	 */
	public void setNumeroControl(String[] numeroControl) {
		this.numeroControl = numeroControl;
	}

	public Date getFechaDesdeDate() {
		return fechaDesdeDate;
	}

	public void setFechaDesdeDate(Date fechaDesdeDate) {
		this.fechaDesdeDate = fechaDesdeDate;
	}

	public Date getFechaHastaDate() {
		return fechaHastaDate;
	}

	public void setFechaHastaDate(Date fechaHastaDate) {
		this.fechaHastaDate = fechaHastaDate;
	}
}