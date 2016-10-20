package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



public class MantenimientoSTOBloqueoControlSearchForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5297116629790602323L;
	private String codigoPais;
	private String codigoCliente;	
	private String tipoCliente;	
	private String subTipoCliente;
	private String tipoClasificacion;	
	private String clasificacion;
	private String codigoPeriodo;
	private String codigoRegion;
	private String codigoZona;
	private String tipoBloqueo;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	private String codigoTipoDocumento;
	private String indicadorPeriodo;
	private String motivoBloqueo;
	private String codigosErradosFile;

	//PER-SiCC-2012-0819
	private String lineaDefecto;
	private String lineaMaxima;
	
	
	
	
	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}

	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}

	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return
	 */
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}

	/**
	 * @param tipoClasificacion
	 */
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	/**
	 * @return
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * @param clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the tipoBloqueo
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	/**
	 * @param tipoBloqueo the tipoBloqueo to set
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	/**
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	/**
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return the indicadorPeriodo
	 */
	public String getIndicadorPeriodo() {
		return indicadorPeriodo;
	}

	/**
	 * @param indicadorPeriodo the indicadorPeriodo to set
	 */
	public void setIndicadorPeriodo(String indicadorPeriodo) {
		this.indicadorPeriodo = indicadorPeriodo;
	}

	/**
	 * @return the motivoBloqueo
	 */
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}

	/**
	 * @param motivoBloqueo the motivoBloqueo to set
	 */
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}

	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}

	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}	
}