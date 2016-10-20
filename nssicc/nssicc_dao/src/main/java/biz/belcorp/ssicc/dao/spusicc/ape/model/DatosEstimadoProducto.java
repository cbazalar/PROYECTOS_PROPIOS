package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class DatosEstimadoProducto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355222605398197756L;
	private String codigoPais;
	private String codigoCentroDistribucion;
	private String codigoLineaArmadoDefault;
	private String codigoLineaArmado;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String oidPeriodo;
	private String codigoSAP;
	private String oidProducto;
	private String descripcionSAP;
	private String flagFueraPedido;
	private String unidadEstimada;
	private String descripcionProc;
	private String flagLineaAfp;
	private boolean viewEdit;

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
	 * @return codigoCentroDistribucion
	 */
	public String getCodigoCentroDistribucion() {
		return codigoCentroDistribucion;
	}
	
	/**
	 * @param codigoCentroDistribucion
	 */
	public void setCodigoCentroDistribucion(String codigoCentroDistribucion) {
		this.codigoCentroDistribucion = codigoCentroDistribucion;
	}
	
	/**
	 * @return codigoLineaArmadoDefault
	 */
	public String getCodigoLineaArmadoDefault() {
		return codigoLineaArmadoDefault;
	}
	
	/**
	 * @param codigoLineaArmadoDefault
	 */
	public void setCodigoLineaArmadoDefault(String codigoLineaArmadoDefault) {
		this.codigoLineaArmadoDefault = codigoLineaArmadoDefault;
	}
	
	/**
	 * @return codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}
	
	/**
	 * @param codigoLineaArmado
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	
	/**
	 * @return codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	
	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	/**
	 * @return codigoPeriodo
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
	 * @return oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}
	
	/**
	 * @param oidPeriodo
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}
	
	/**
	 * @return codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	
	/**
	 * @param codigoSAP
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	
	/**
	 * @return oidProducto
	 */
	public String getOidProducto() {
		return oidProducto;
	}
	
	/**
	 * @param oidProducto
	 */
	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}
	
	/**
	 * @return descripcionSAP
	 */
	public String getDescripcionSAP() {
		return descripcionSAP;
	}
	
	/**
	 * @param descripcionSAP
	 */
	public void setDescripcionSAP(String descripcionSAP) {
		this.descripcionSAP = descripcionSAP;
	}
	
	/**
	 * @return flagFueraPedido
	 */
	public String getFlagFueraPedido() {
		return flagFueraPedido;
	}
	
	/**
	 * @param flagFueraPedido
	 */
	public void setFlagFueraPedido(String flagFueraPedido) {
		this.flagFueraPedido = flagFueraPedido;
	}
	
	/**
	 * @return unidadEstimada
	 */
	public String getUnidadEstimada() {
		return unidadEstimada;
	}
	
	/**
	 * @param unidadEstimada
	 */
	public void setUnidadEstimada(String unidadEstimada) {
		this.unidadEstimada = unidadEstimada;
	}
	
	/**
	 * @return descripcionProc
	 */
	public String getDescripcionProc() {
		return descripcionProc;
	}
	
	/**
	 * @param descripcionProc
	 */
	public void setDescripcionProc(String descripcionProc) {
		this.descripcionProc = descripcionProc;
	}
	
	/**
	 * @return flagLineaAfp
	 */
	public String getFlagLineaAfp() {
		return flagLineaAfp;
	}
	
	/**
	 * @param flagLineaAfp
	 */
	public void setFlagLineaAfp(String flagLineaAfp) {
		this.flagLineaAfp = flagLineaAfp;
	}
	
	/**
	 * @return viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}
	
	/**
	 * @param viewEdit
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}
	
}