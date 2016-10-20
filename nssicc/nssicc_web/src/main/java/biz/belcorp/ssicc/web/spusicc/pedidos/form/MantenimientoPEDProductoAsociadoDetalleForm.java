/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoPEDProductoAsociadoDetalleForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8417179381463178001L;
	
	private String codigoPais;
	private String codigoSap;
	private String textoBreve;
	private String oidCatalogo;
	private String factorRepeticion;
	private String flagDigitable;
	private String flagImprimible;
	private String precioCatalogo;
	private String precioPosicionamiento;
	private String costoEstandar;
	private String unidadesEstimadas;
	private String ventaNetaEstimada;
	private String numeroPaginaCatalogo;
	private String oidTipoOferta;
	private String oidEstrategia;
	
	private String mensajeProductoActual;
	private boolean flagDigitableSoloLectura;
	private boolean flagImprimibleSoloLectura;
	private boolean flagCerrarVentana;
	
	private String descripcion;
	
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
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}
	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	/**
	 * @return the textoBreve
	 */
	public String getTextoBreve() {
		return textoBreve;
	}
	/**
	 * @param textoBreve the textoBreve to set
	 */
	public void setTextoBreve(String textoBreve) {
		this.textoBreve = textoBreve;
	}
	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}
	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
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
	 * @return the flagDigitable
	 */
	public String getFlagDigitable() {
		return flagDigitable;
	}
	/**
	 * @param flagDigitable the flagDigitable to set
	 */
	public void setFlagDigitable(String flagDigitable) {
		this.flagDigitable = flagDigitable;
	}
	/**
	 * @return the flagImprimible
	 */
	public String getFlagImprimible() {
		return flagImprimible;
	}
	/**
	 * @param flagImprimible the flagImprimible to set
	 */
	public void setFlagImprimible(String flagImprimible) {
		this.flagImprimible = flagImprimible;
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
	 * @return the costoEstandar
	 */
	public String getCostoEstandar() {
		return costoEstandar;
	}
	/**
	 * @param costoEstandar the costoEstandar to set
	 */
	public void setCostoEstandar(String costoEstandar) {
		this.costoEstandar = costoEstandar;
	}
	/**
	 * @return the unidadesEstimadas
	 */
	public String getUnidadesEstimadas() {
		return unidadesEstimadas;
	}
	/**
	 * @param unidadesEstimadas the unidadesEstimadas to set
	 */
	public void setUnidadesEstimadas(String unidadesEstimadas) {
		this.unidadesEstimadas = unidadesEstimadas;
	}
	/**
	 * @return the ventaNetaEstimada
	 */
	public String getVentaNetaEstimada() {
		return ventaNetaEstimada;
	}
	/**
	 * @param ventaNetaEstimada the ventaNetaEstimada to set
	 */
	public void setVentaNetaEstimada(String ventaNetaEstimada) {
		this.ventaNetaEstimada = ventaNetaEstimada;
	}
	/**
	 * @return the numeroPaginaCatalogo
	 */
	public String getNumeroPaginaCatalogo() {
		return numeroPaginaCatalogo;
	}
	/**
	 * @param numeroPaginaCatalogo the numeroPaginaCatalogo to set
	 */
	public void setNumeroPaginaCatalogo(String numeroPaginaCatalogo) {
		this.numeroPaginaCatalogo = numeroPaginaCatalogo;
	}
	/**
	 * @return the oidTipoOferta
	 */
	public String getOidTipoOferta() {
		return oidTipoOferta;
	}
	/**
	 * @param oidTipoOferta the oidTipoOferta to set
	 */
	public void setOidTipoOferta(String oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
	}
	/**
	 * @return the oidEstrategia
	 */
	public String getOidEstrategia() {
		return oidEstrategia;
	}
	/**
	 * @param oidEstrategia the oidEstrategia to set
	 */
	public void setOidEstrategia(String oidEstrategia) {
		this.oidEstrategia = oidEstrategia;
	}
	/**
	 * @return the mensajeProductoActual
	 */
	public String getMensajeProductoActual() {
		return mensajeProductoActual;
	}
	/**
	 * @param mensajeProductoActual the mensajeProductoActual to set
	 */
	public void setMensajeProductoActual(String mensajeProductoActual) {
		this.mensajeProductoActual = mensajeProductoActual;
	}
	/**
	 * @return the flagDigitableSoloLectura
	 */
	public boolean isFlagDigitableSoloLectura() {
		return flagDigitableSoloLectura;
	}
	/**
	 * @param flagDigitableSoloLectura the flagDigitableSoloLectura to set
	 */
	public void setFlagDigitableSoloLectura(boolean flagDigitableSoloLectura) {
		this.flagDigitableSoloLectura = flagDigitableSoloLectura;
	}
	/**
	 * @return the flagCerrarVentana
	 */
	public boolean isFlagCerrarVentana() {
		return flagCerrarVentana;
	}
	/**
	 * @param flagCerrarVentana the flagCerrarVentana to set
	 */
	public void setFlagCerrarVentana(boolean flagCerrarVentana) {
		this.flagCerrarVentana = flagCerrarVentana;
	}
	/**
	 * @return the flagImprimibleSoloLectura
	 */
	public boolean isFlagImprimibleSoloLectura() {
		return flagImprimibleSoloLectura;
	}
	/**
	 * @param flagImprimibleSoloLectura the flagImprimibleSoloLectura to set
	 */
	public void setFlagImprimibleSoloLectura(boolean flagImprimibleSoloLectura) {
		this.flagImprimibleSoloLectura = flagImprimibleSoloLectura;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
