package biz.belcorp.ssicc.web.spusicc.inc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;




/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoINCPremiosElectivosSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *                      
 */
public class MantenimientoINCPremiosElectivosSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;	

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
	
	private String codigoCliente;
	private String[] unidadesItems = {};
	private String oidPais;
	private String longitudCodigoCliente;
	
	private String numeroPremio;

	private boolean indicadorBusqueda;
	private String numeroPeriodos;
	private String puntajeNuevo;
	
	private int ultimo;
	
	private String indicadorNoValidaPuntaje;
	
	private String msgPremioNiveles;
	private String msgPremioUnicaNiveles;
	private String msgNoExistePuntajeDisponible;

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
	 * @return the puntajeNuevo
	 */
	public String getPuntajeNuevo() {
		return puntajeNuevo;
	}

	/**
	 * @param puntajeNuevo the puntajeNuevo to set
	 */
	public void setPuntajeNuevo(String puntajeNuevo) {
		this.puntajeNuevo = puntajeNuevo;
	}

	/**
	 * @return the ultimo
	 */
	public int getUltimo() {
		return ultimo;
	}

	/**
	 * @param ultimo the ultimo to set
	 */
	public void setUltimo(int ultimo) {
		this.ultimo = ultimo;
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
	 * @return the msgPremioNiveles
	 */
	public String getMsgPremioNiveles() {
		return msgPremioNiveles;
	}

	/**
	 * @param msgPremioNiveles the msgPremioNiveles to set
	 */
	public void setMsgPremioNiveles(String msgPremioNiveles) {
		this.msgPremioNiveles = msgPremioNiveles;
	}

	/**
	 * @return the msgPremioUnicaNiveles
	 */
	public String getMsgPremioUnicaNiveles() {
		return msgPremioUnicaNiveles;
	}

	/**
	 * @param msgPremioUnicaNiveles the msgPremioUnicaNiveles to set
	 */
	public void setMsgPremioUnicaNiveles(String msgPremioUnicaNiveles) {
		this.msgPremioUnicaNiveles = msgPremioUnicaNiveles;
	}

	/**
	 * @return the msgNoExistePuntajeDisponible
	 */
	public String getMsgNoExistePuntajeDisponible() {
		return msgNoExistePuntajeDisponible;
	}

	/**
	 * @param msgNoExistePuntajeDisponible the msgNoExistePuntajeDisponible to set
	 */
	public void setMsgNoExistePuntajeDisponible(String msgNoExistePuntajeDisponible) {
		this.msgNoExistePuntajeDisponible = msgNoExistePuntajeDisponible;
	}
	
  
	

}
