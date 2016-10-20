package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

public class MantenimientoSTOCartaInvitacionFlexipagoForm extends BaseMantenimientoSTOGestionForm implements Serializable{
	
	private static final long serialVersionUID = 1050351986922714136L;
	
	private String codigoPais;
	private String oidPais;
	private String estadoProceso;
	private String indMotivoRechazo;
	private String numeroPreImpreso;
	private String codigoConsultora;
	private String codigoRegion;
	private String codigoZona;
	private String tipoDocumentoIden;
	private String numDocumentoIden;
	private String indFirmaCliente;
	private String indFirmaReprLegal;
	private String salirPantalla = "N";
	private String numLote;
	private String numSecuencia;
	private String codCampanaInicio;
	private String longitudTipoDocumento;
	private boolean permitirCompletarCerosIdentidad;
	private String descripcionConsultora;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @return the indMotivoRechazo
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	/**
	 * @return the numeroPreImpreso
	 */
	public String getNumeroPreImpreso() {
		return numeroPreImpreso;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @return the tipoDocumentoIden
	 */
	public String getTipoDocumentoIden() {
		return tipoDocumentoIden;
	}
	/**
	 * @return the numDocumentoIden
	 */
	public String getNumDocumentoIden() {
		return numDocumentoIden;
	}
	/**
	 * @return the indFirmaCliente
	 */
	public String getIndFirmaCliente() {
		return indFirmaCliente;
	}
	/**
	 * @return the indFirmaReprLegal
	 */
	public String getIndFirmaReprLegal() {
		return indFirmaReprLegal;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @param indMotivoRechazo the indMotivoRechazo to set
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
	}
	/**
	 * @param numeroPreImpreso the numeroPreImpreso to set
	 */
	public void setNumeroPreImpreso(String numeroPreImpreso) {
		this.numeroPreImpreso = numeroPreImpreso;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @param tipoDocumentoIden the tipoDocumentoIden to set
	 */
	public void setTipoDocumentoIden(String tipoDocumentoIden) {
		this.tipoDocumentoIden = tipoDocumentoIden;
	}
	/**
	 * @param numDocumentoIden the numDocumentoIden to set
	 */
	public void setNumDocumentoIden(String numDocumentoIden) {
		this.numDocumentoIden = numDocumentoIden;
	}
	/**
	 * @param indFirmaCliente the indFirmaCliente to set
	 */
	public void setIndFirmaCliente(String indFirmaCliente) {
		this.indFirmaCliente = indFirmaCliente;
	}
	/**
	 * @param indFirmaReprLegal the indFirmaReprLegal to set
	 */
	public void setIndFirmaReprLegal(String indFirmaReprLegal) {
		this.indFirmaReprLegal = indFirmaReprLegal;
	}
	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}
	/**
	 * @param salirPantalla the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}
	/**
	 * @return the numLote
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote the numLote to set
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return the codCampanaInicio
	 */
	public String getCodCampanaInicio() {
		return codCampanaInicio;
	}
	/**
	 * @param codCampanaInicio the codCampanaInicio to set
	 */
	public void setCodCampanaInicio(String codCampanaInicio) {
		this.codCampanaInicio = codCampanaInicio;
	}
	/**
	 * @return the longitudTipoDocumento
	 */
	public String getLongitudTipoDocumento() {
		return longitudTipoDocumento;
	}
	/**
	 * @param longitudTipoDocumento the longitudTipoDocumento to set
	 */
	public void setLongitudTipoDocumento(String longitudTipoDocumento) {
		this.longitudTipoDocumento = longitudTipoDocumento;
	}
	/**
	 * @return the permitirCompletarCerosIdentidad
	 */
	public boolean isPermitirCompletarCerosIdentidad() {
		return permitirCompletarCerosIdentidad;
	}
	/**
	 * @param permitirCompletarCerosIdentidad the permitirCompletarCerosIdentidad to set
	 */
	public void setPermitirCompletarCerosIdentidad(
			boolean permitirCompletarCerosIdentidad) {
		this.permitirCompletarCerosIdentidad = permitirCompletarCerosIdentidad;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}
	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
}
