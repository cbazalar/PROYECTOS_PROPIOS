package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCPremiosElectivosForm extends BaseSearchForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3899385353729842965L;
	private String codigoPais;
	private String oidConcurso;
	private String codigoClienteBuscar;
	private String nombreConsultora;
	private String meta;
	private String puntajeObtenido;
	private String puntajeCanjeado;
	private String puntajeSaldo;
	private String puntajeComprometido;
	private String puntajeDisponible;
	
	private String[] unidadesItems = {};
	private String oidPais;
	private String longitudCodigoCliente;
	
	private String numeroPremio;

	private String codigoCliente;
	private boolean indicadorBusqueda;
	
	private String numeroPeriodos;

	private String indicadorNoValidaPuntaje;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String indicadorConsultoras;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the meta
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * @return the puntajeObtenido
	 */
	public String getPuntajeObtenido() {
		return puntajeObtenido;
	}

	/**
	 * @param puntajeObtenido the puntajeObtenido to set
	 */
	public void setPuntajeObtenido(String puntajeObtenido) {
		this.puntajeObtenido = puntajeObtenido;
	}

	/**
	 * @return the puntajeCanjeado
	 */
	public String getPuntajeCanjeado() {
		return puntajeCanjeado;
	}

	/**
	 * @param puntajeCanjeado the puntajeCanjeado to set
	 */
	public void setPuntajeCanjeado(String puntajeCanjeado) {
		this.puntajeCanjeado = puntajeCanjeado;
	}

	/**
	 * @return the puntajeSaldo
	 */
	public String getPuntajeSaldo() {
		return puntajeSaldo;
	}

	/**
	 * @param puntajeSaldo the puntajeSaldo to set
	 */
	public void setPuntajeSaldo(String puntajeSaldo) {
		this.puntajeSaldo = puntajeSaldo;
	}

	/**
	 * @return the puntajeComprometido
	 */
	public String getPuntajeComprometido() {
		return puntajeComprometido;
	}

	/**
	 * @param puntajeComprometido the puntajeComprometido to set
	 */
	public void setPuntajeComprometido(String puntajeComprometido) {
		this.puntajeComprometido = puntajeComprometido;
	}

	/**
	 * @return the puntajeDisponible
	 */
	public String getPuntajeDisponible() {
		return puntajeDisponible;
	}

	/**
	 * @param puntajeDisponible the puntajeDisponible to set
	 */
	public void setPuntajeDisponible(String puntajeDisponible) {
		this.puntajeDisponible = puntajeDisponible;
	}

	/**
	 * @return the unidadesItems
	 */
	public String[] getUnidadesItems() {
		return unidadesItems;
	}

	/**
	 * @param unidadesItems the unidadesItems to set
	 */
	public void setUnidadesItems(String[] unidadesItems) {
		this.unidadesItems = unidadesItems;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	/**
	 * @return the numeroPremio
	 */
	public String getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the indicadorBusqueda
	 */
	public boolean isIndicadorBusqueda() {
		return indicadorBusqueda;
	}

	/**
	 * @param indicadorBusqueda the indicadorBusqueda to set
	 */
	public void setIndicadorBusqueda(boolean indicadorBusqueda) {
		this.indicadorBusqueda = indicadorBusqueda;
	}

	/**
	 * @return the numeroPeriodos
	 */
	public String getNumeroPeriodos() {
		return numeroPeriodos;
	}

	/**
	 * @param numeroPeriodos the numeroPeriodos to set
	 */
	public void setNumeroPeriodos(String numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}

	/**
	 * @return the indicadorNoValidaPuntaje
	 */
	public String getIndicadorNoValidaPuntaje() {
		return indicadorNoValidaPuntaje;
	}

	/**
	 * @param indicadorNoValidaPuntaje the indicadorNoValidaPuntaje to set
	 */
	public void setIndicadorNoValidaPuntaje(String indicadorNoValidaPuntaje) {
		this.indicadorNoValidaPuntaje = indicadorNoValidaPuntaje;
	}

	/**
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the indicadorConsultoras
	 */
	public String getIndicadorConsultoras() {
		return indicadorConsultoras;
	}

	/**
	 * @param indicadorConsultoras the indicadorConsultoras to set
	 */
	public void setIndicadorConsultoras(String indicadorConsultoras) {
		this.indicadorConsultoras = indicadorConsultoras;
	}
	
	

}
