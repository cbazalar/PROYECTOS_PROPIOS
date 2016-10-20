package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaPREMatrizFacturacionForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4426910003427008980L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVentaBuscar;
	
	private String codigoVenta;
	private String codigoSAP;
	private String descripcionProducto;

	private String tipoOferta;
	private String precioCatalogo; 
	private String formaPago;
	private String precioPosicionamiento;
	private String catalogo;
	private String numeroPagina;
	private String grupo;
	private String estrategia;
	private String factorRepeticion;
	private String indAplicaComisiones;
	private String indEstadisticable;
	private String indAplicaPuntajes;
	private String productoReemplazo; 
	private String productoAlternativo;
	private String indDigitable;
	private String indPrincipal;
	
	private boolean mostrarScrollHeightPA;
	private boolean mostrarScrollHeightLV;
	private boolean mostrarScrollHeightCS;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoVentaBuscar
	 */
	public String getCodigoVentaBuscar() {
		return codigoVentaBuscar;
	}
	/**
	 * @param codigoVentaBuscar the codigoVentaBuscar to set
	 */
	public void setCodigoVentaBuscar(String codigoVentaBuscar) {
		this.codigoVentaBuscar = codigoVentaBuscar;
	}
	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}
	/**
	 * @param tipoOferta the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
	/**
	 * @return the precioCatalogo
	 */
	public String getPrecioCatalogo() {
		return precioCatalogo;
	}
	/**
	 * @param precioCatalogo the precioCatalogo to set
	 */
	public void setPrecioCatalogo(String precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}
	/**
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	/**
	 * @return the precioPosicionamiento
	 */
	public String getPrecioPosicionamiento() {
		return precioPosicionamiento;
	}
	/**
	 * @param precioPosicionamiento the precioPosicionamiento to set
	 */
	public void setPrecioPosicionamiento(String precioPosicionamiento) {
		this.precioPosicionamiento = precioPosicionamiento;
	}
	/**
	 * @return the catalogo
	 */
	public String getCatalogo() {
		return catalogo;
	}
	/**
	 * @param catalogo the catalogo to set
	 */
	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}
	/**
	 * @return the numeroPagina
	 */
	public String getNumeroPagina() {
		return numeroPagina;
	}
	/**
	 * @param numeroPagina the numeroPagina to set
	 */
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * @return the estrategia
	 */
	public String getEstrategia() {
		return estrategia;
	}
	/**
	 * @param estrategia the estrategia to set
	 */
	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}
	/**
	 * @return the factorRepeticion
	 */
	public String getFactorRepeticion() {
		return factorRepeticion;
	}
	/**
	 * @param factorRepeticion the factorRepeticion to set
	 */
	public void setFactorRepeticion(String factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}
	/**
	 * @return the indAplicaComisiones
	 */
	public String getIndAplicaComisiones() {
		return indAplicaComisiones;
	}
	/**
	 * @param indAplicaComisiones the indAplicaComisiones to set
	 */
	public void setIndAplicaComisiones(String indAplicaComisiones) {
		this.indAplicaComisiones = indAplicaComisiones;
	}
	/**
	 * @return the indEstadisticable
	 */
	public String getIndEstadisticable() {
		return indEstadisticable;
	}
	/**
	 * @param indEstadisticable the indEstadisticable to set
	 */
	public void setIndEstadisticable(String indEstadisticable) {
		this.indEstadisticable = indEstadisticable;
	}
	/**
	 * @return the indAplicaPuntajes
	 */
	public String getIndAplicaPuntajes() {
		return indAplicaPuntajes;
	}
	/**
	 * @param indAplicaPuntajes the indAplicaPuntajes to set
	 */
	public void setIndAplicaPuntajes(String indAplicaPuntajes) {
		this.indAplicaPuntajes = indAplicaPuntajes;
	}
	/**
	 * @return the productoReemplazo
	 */
	public String getProductoReemplazo() {
		return productoReemplazo;
	}
	/**
	 * @param productoReemplazo the productoReemplazo to set
	 */
	public void setProductoReemplazo(String productoReemplazo) {
		this.productoReemplazo = productoReemplazo;
	}
	/**
	 * @return the productoAlternativo
	 */
	public String getProductoAlternativo() {
		return productoAlternativo;
	}
	/**
	 * @param productoAlternativo the productoAlternativo to set
	 */
	public void setProductoAlternativo(String productoAlternativo) {
		this.productoAlternativo = productoAlternativo;
	}
	/**
	 * @return the indDigitable
	 */
	public String getIndDigitable() {
		return indDigitable;
	}
	/**
	 * @param indDigitable the indDigitable to set
	 */
	public void setIndDigitable(String indDigitable) {
		this.indDigitable = indDigitable;
	}
	/**
	 * @return the indPrincipal
	 */
	public String getIndPrincipal() {
		return indPrincipal;
	}
	/**
	 * @param indPrincipal the indPrincipal to set
	 */
	public void setIndPrincipal(String indPrincipal) {
		this.indPrincipal = indPrincipal;
	}
	/**
	 * @return the mostrarScrollHeightPA
	 */
	public boolean isMostrarScrollHeightPA() {
		return mostrarScrollHeightPA;
	}
	/**
	 * @param mostrarScrollHeightPA the mostrarScrollHeightPA to set
	 */
	public void setMostrarScrollHeightPA(boolean mostrarScrollHeightPA) {
		this.mostrarScrollHeightPA = mostrarScrollHeightPA;
	}
	/**
	 * @return the mostrarScrollHeightLV
	 */
	public boolean isMostrarScrollHeightLV() {
		return mostrarScrollHeightLV;
	}
	/**
	 * @param mostrarScrollHeightLV the mostrarScrollHeightLV to set
	 */
	public void setMostrarScrollHeightLV(boolean mostrarScrollHeightLV) {
		this.mostrarScrollHeightLV = mostrarScrollHeightLV;
	}
	/**
	 * @return the mostrarScrollHeightCS
	 */
	public boolean isMostrarScrollHeightCS() {
		return mostrarScrollHeightCS;
	}
	/**
	 * @param mostrarScrollHeightCS the mostrarScrollHeightCS to set
	 */
	public void setMostrarScrollHeightCS(boolean mostrarScrollHeightCS) {
		this.mostrarScrollHeightCS = mostrarScrollHeightCS;
	}
	
	

}
