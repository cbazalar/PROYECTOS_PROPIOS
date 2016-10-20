package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Jose Luis Rodriguez
 */

public class CentroDistribucion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1847232101222318289L;
	private String codigoPais;
	private String oidCentroDistribucion;
	private String codigoCentroDistribucion;
	private String descripcionCentroDistribucion;
	private String codigoNivelOutsourcing;
	private String indicadorValorDefecto;
	private String codigoOrdenListaPicado;
	private String codigoOrden;
	private String textoChequeo;
	private String textoPrimerosPedidos;
	private String prefijoEtiquetado;
	private String codigoAgrupacionDefault;
	private String indicadorImpresionDatosBasicos;
	private String codigoVisualizacionChequeo;
	private String indicadorOrdenvisualizacion;
	private String codigoNivelAgrupacionOlas;
	private String numeroMinimoPedidos;
	private String numeroEtiqueta;
	
	/**
	 * @return numeroEtiqueta
	 */
	public String getNumeroEtiqueta() {
		return numeroEtiqueta;
	}

	/**
	 * @param numeroEtiqueta
	 */
	public void setNumeroEtiqueta(String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidCentroDistribucion
	 */
	public String getOidCentroDistribucion() {
		return oidCentroDistribucion;
	}

	/**
	 * @param oidCentroDistribucion the oidCentroDistribucion to set
	 */
	public void setOidCentroDistribucion(String oidCentroDistribucion) {
		this.oidCentroDistribucion = oidCentroDistribucion;
	}

	/**
	 * @return the codigoCentroDistribucion
	 */
	public String getCodigoCentroDistribucion() {
		return codigoCentroDistribucion;
	}

	/**
	 * @param codigoCentroDistribucion the codigoCentroDistribucion to set
	 */
	public void setCodigoCentroDistribucion(String codigoCentroDistribucion) {
		this.codigoCentroDistribucion = codigoCentroDistribucion;
	}

	/**
	 * @return the descripcionCentroDistribucion
	 */
	public String getDescripcionCentroDistribucion() {
		return descripcionCentroDistribucion;
	}

	/**
	 * @param descripcionCentroDistribucion the descripcionCentroDistribucion to set
	 */
	public void setDescripcionCentroDistribucion(
			String descripcionCentroDistribucion) {
		this.descripcionCentroDistribucion = descripcionCentroDistribucion;
	}

	/**
	 * @return the codigoNivelOutsourcing
	 */
	public String getCodigoNivelOutsourcing() {
		return codigoNivelOutsourcing;
	}

	/**
	 * @param codigoNivelOutsourcing the codigoNivelOutsourcing to set
	 */
	public void setCodigoNivelOutsourcing(String codigoNivelOutsourcing) {
		this.codigoNivelOutsourcing = codigoNivelOutsourcing;
	}

	/**
	 * @return the indicadorValorDefecto
	 */
	public String getIndicadorValorDefecto() {
		return indicadorValorDefecto;
	}

	/**
	 * @param indicadorValorDefecto the indicadorValorDefecto to set
	 */
	public void setIndicadorValorDefecto(String indicadorValorDefecto) {
		this.indicadorValorDefecto = indicadorValorDefecto;
	}

	/**
	 * @return the codigoOrdenListaPicado
	 */
	public String getCodigoOrdenListaPicado() {
		return codigoOrdenListaPicado;
	}

	/**
	 * @param codigoOrdenListaPicado the codigoOrdenListaPicado to set
	 */
	public void setCodigoOrdenListaPicado(String codigoOrdenListaPicado) {
		this.codigoOrdenListaPicado = codigoOrdenListaPicado;
	}

	/**
	 * @return the codigoOrden
	 */
	public String getCodigoOrden() {
		return codigoOrden;
	}

	/**
	 * @param codigoOrden the codigoOrden to set
	 */
	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

	/**
	 * @return the textoChequeo
	 */
	public String getTextoChequeo() {
		return textoChequeo;
	}

	/**
	 * @param textoChequeo the textoChequeo to set
	 */
	public void setTextoChequeo(String textoChequeo) {
		this.textoChequeo = textoChequeo;
	}

	/**
	 * @return the textoPrimerosPedidos
	 */
	public String getTextoPrimerosPedidos() {
		return textoPrimerosPedidos;
	}

	/**
	 * @param textoPrimerosPedidos the textoPrimerosPedidos to set
	 */
	public void setTextoPrimerosPedidos(String textoPrimerosPedidos) {
		this.textoPrimerosPedidos = textoPrimerosPedidos;
	}

	/**
	 * @return the prefijoEtiquetado
	 */
	public String getPrefijoEtiquetado() {
		return prefijoEtiquetado;
	}

	/**
	 * @param prefijoEtiquetado the prefijoEtiquetado to set
	 */
	public void setPrefijoEtiquetado(String prefijoEtiquetado) {
		this.prefijoEtiquetado = prefijoEtiquetado;
	}

	/**
	 * @return the codigoAgrupacionDefault
	 */
	public String getCodigoAgrupacionDefault() {
		return codigoAgrupacionDefault;
	}

	/**
	 * @param codigoAgrupacionDefault the codigoAgrupacionDefault to set
	 */
	public void setCodigoAgrupacionDefault(String codigoAgrupacionDefault) {
		this.codigoAgrupacionDefault = codigoAgrupacionDefault;
	}

	/**
	 * @return the indicadorImpresionDatosBasicos
	 */
	public String getIndicadorImpresionDatosBasicos() {
		return indicadorImpresionDatosBasicos;
	}

	/**
	 * @param indicadorImpresionDatosBasicos the indicadorImpresionDatosBasicos to set
	 */
	public void setIndicadorImpresionDatosBasicos(
			String indicadorImpresionDatosBasicos) {
		this.indicadorImpresionDatosBasicos = indicadorImpresionDatosBasicos;
	}

	/**
	 * @return the codigoVisualizacionChequeo
	 */
	public String getCodigoVisualizacionChequeo() {
		return codigoVisualizacionChequeo;
	}

	/**
	 * @param codigoVisualizacionChequeo the codigoVisualizacionChequeo to set
	 */
	public void setCodigoVisualizacionChequeo(String codigoVisualizacionChequeo) {
		this.codigoVisualizacionChequeo = codigoVisualizacionChequeo;
	}

	/**
	 * @return the indicadorOrdenvisualizacion
	 */
	public String getIndicadorOrdenvisualizacion() {
		return indicadorOrdenvisualizacion;
	}

	/**
	 * @param indicadorOrdenvisualizacion the indicadorOrdenvisualizacion to set
	 */
	public void setIndicadorOrdenvisualizacion(String indicadorOrdenvisualizacion) {
		this.indicadorOrdenvisualizacion = indicadorOrdenvisualizacion;
	}

	/**
	 * @return the codigoNivelAgrupacionOlas
	 */
	public String getCodigoNivelAgrupacionOlas() {
		return codigoNivelAgrupacionOlas;
	}

	/**
	 * @param codigoNivelAgrupacionOlas the codigoNivelAgrupacionOlas to set
	 */
	public void setCodigoNivelAgrupacionOlas(String codigoNivelAgrupacionOlas) {
		this.codigoNivelAgrupacionOlas = codigoNivelAgrupacionOlas;
	}

	/**
	 * @return the numeroMinimoPedidos
	 */
	public String getNumeroMinimoPedidos() {
		return numeroMinimoPedidos;
	}

	/**
	 * @param numeroMinimoPedidos the numeroMinimoPedidos to set
	 */
	public void setNumeroMinimoPedidos(String numeroMinimoPedidos) {
		this.numeroMinimoPedidos = numeroMinimoPedidos;
	}

}