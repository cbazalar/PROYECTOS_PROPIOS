package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Jose Luis Rodriguez
 */

public class MaterialesVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123389083771187794L;
	private String codigoPais;
	private String oidSAP;
	private String codigoSAP;
	private String descripcionSAP;
	private String descripcionCortaSAP;
	private String codigoTipoDispensacion;
	private String unidadesEmpaque;
	private String codigoTipoAnaquel;
	private String pickSpeed;
	private String largo;
	private String alto;
	private String ancho;
	private String codigoUMPeso;
	private String codigoUMDimension;
	private String numHoraInventario;
	private String codigoCajaMaestra;
	private String unidadCajaMaestra;
	private String porcentajeAdicionalHolgura;
	private String packSize;
	private String screenQTY;
	private String lane60;
	private String lane95;
	private String numAsignacionMaximaChanel;
	private String unidadCajaGrande;
	private String unidadCajaPequena;
	
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
	 * @return the oidSAP
	 */
	public String getOidSAP() {
		return oidSAP;
	}

	/**
	 * @param oidSAP the oidSAP to set
	 */
	public void setOidSAP(String oidSAP) {
		this.oidSAP = oidSAP;
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
	 * @return the descripcionSAP
	 */
	public String getDescripcionSAP() {
		return descripcionSAP;
	}

	/**
	 * @param descripcionSAP the descripcionSAP to set
	 */
	public void setDescripcionSAP(String descripcionSAP) {
		this.descripcionSAP = descripcionSAP;
	}

	/**
	 * @return the descripcionCortaSAP
	 */
	public String getDescripcionCortaSAP() {
		return descripcionCortaSAP;
	}

	/**
	 * @param descripcionCortaSAP the descripcionCortaSAP to set
	 */
	public void setDescripcionCortaSAP(String descripcionCortaSAP) {
		this.descripcionCortaSAP = descripcionCortaSAP;
	}

	/**
	 * @return the codigoTipoDispensacion
	 */
	public String getCodigoTipoDispensacion() {
		return codigoTipoDispensacion;
	}

	/**
	 * @param codigoTipoDispensacion the codigoTipoDispensacion to set
	 */
	public void setCodigoTipoDispensacion(String codigoTipoDispensacion) {
		this.codigoTipoDispensacion = codigoTipoDispensacion;
	}

	/**
	 * @return the unidadesEmpaque
	 */
	public String getUnidadesEmpaque() {
		return unidadesEmpaque;
	}

	/**
	 * @param unidadesEmpaque the unidadesEmpaque to set
	 */
	public void setUnidadesEmpaque(String unidadesEmpaque) {
		this.unidadesEmpaque = unidadesEmpaque;
	}

	/**
	 * @return the codigoTipoAnaquel
	 */
	public String getCodigoTipoAnaquel() {
		return codigoTipoAnaquel;
	}

	/**
	 * @param codigoTipoAnaquel the codigoTipoAnaquel to set
	 */
	public void setCodigoTipoAnaquel(String codigoTipoAnaquel) {
		this.codigoTipoAnaquel = codigoTipoAnaquel;
	}

	/**
	 * @return the pickSpeed
	 */
	public String getPickSpeed() {
		return pickSpeed;
	}

	/**
	 * @param pickSpeed the pickSpeed to set
	 */
	public void setPickSpeed(String pickSpeed) {
		this.pickSpeed = pickSpeed;
	}

	/**
	 * @return the largo
	 */
	public String getLargo() {
		return largo;
	}

	/**
	 * @param largo the largo to set
	 */
	public void setLargo(String largo) {
		this.largo = largo;
	}

	/**
	 * @return the alto
	 */
	public String getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(String alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public String getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the codigoUMPeso
	 */
	public String getCodigoUMPeso() {
		return codigoUMPeso;
	}

	/**
	 * @param codigoUMPeso the codigoUMPeso to set
	 */
	public void setCodigoUMPeso(String codigoUMPeso) {
		this.codigoUMPeso = codigoUMPeso;
	}

	/**
	 * @return the codigoUMDimension
	 */
	public String getCodigoUMDimension() {
		return codigoUMDimension;
	}

	/**
	 * @param codigoUMDimension the codigoUMDimension to set
	 */
	public void setCodigoUMDimension(String codigoUMDimension) {
		this.codigoUMDimension = codigoUMDimension;
	}

	/**
	 * @return the numHoraInventario
	 */
	public String getNumHoraInventario() {
		return numHoraInventario;
	}

	/**
	 * @param numHoraInventario the numHoraInventario to set
	 */
	public void setNumHoraInventario(String numHoraInventario) {
		this.numHoraInventario = numHoraInventario;
	}

	/**
	 * @return the codigoCajaMaestra
	 */
	public String getCodigoCajaMaestra() {
		return codigoCajaMaestra;
	}

	/**
	 * @param codigoCajaMaestra the codigoCajaMaestra to set
	 */
	public void setCodigoCajaMaestra(String codigoCajaMaestra) {
		this.codigoCajaMaestra = codigoCajaMaestra;
	}

	/**
	 * @return the unidadCajaMaestra
	 */
	public String getUnidadCajaMaestra() {
		return unidadCajaMaestra;
	}

	/**
	 * @param unidadCajaMaestra the unidadCajaMaestra to set
	 */
	public void setUnidadCajaMaestra(String unidadCajaMaestra) {
		this.unidadCajaMaestra = unidadCajaMaestra;
	}

	/**
	 * @return the porcentajeAdicionalHolgura
	 */
	public String getPorcentajeAdicionalHolgura() {
		return porcentajeAdicionalHolgura;
	}

	/**
	 * @param porcentajeAdicionalHolgura the porcentajeAdicionalHolgura to set
	 */
	public void setPorcentajeAdicionalHolgura(String porcentajeAdicionalHolgura) {
		this.porcentajeAdicionalHolgura = porcentajeAdicionalHolgura;
	}

	/**
	 * @return the packSize
	 */
	public String getPackSize() {
		return packSize;
	}

	/**
	 * @param packSize the packSize to set
	 */
	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	/**
	 * @return the screenQTY
	 */
	public String getScreenQTY() {
		return screenQTY;
	}

	/**
	 * @param screenQTY the screenQTY to set
	 */
	public void setScreenQTY(String screenQTY) {
		this.screenQTY = screenQTY;
	}

	/**
	 * @return the lane60
	 */
	public String getLane60() {
		return lane60;
	}

	/**
	 * @param lane60 the lane60 to set
	 */
	public void setLane60(String lane60) {
		this.lane60 = lane60;
	}

	/**
	 * @return the lane95
	 */
	public String getLane95() {
		return lane95;
	}

	/**
	 * @param lane95 the lane95 to set
	 */
	public void setLane95(String lane95) {
		this.lane95 = lane95;
	}

	/**
	 * @return the numAsignacionMaximaChanel
	 */
	public String getNumAsignacionMaximaChanel() {
		return numAsignacionMaximaChanel;
	}

	/**
	 * @param numAsignacionMaximaChanel the numAsignacionMaximaChanel to set
	 */
	public void setNumAsignacionMaximaChanel(String numAsignacionMaximaChanel) {
		this.numAsignacionMaximaChanel = numAsignacionMaximaChanel;
	}

	/**
	 * @return the unidadCajaGrande
	 */
	public String getUnidadCajaGrande() {
		return unidadCajaGrande;
	}

	/**
	 * @param unidadCajaGrande the unidadCajaGrande to set
	 */
	public void setUnidadCajaGrande(String unidadCajaGrande) {
		this.unidadCajaGrande = unidadCajaGrande;
	}

	/**
	 * @return the unidadCajaPequena
	 */
	public String getUnidadCajaPequena() {
		return unidadCajaPequena;
	}

	/**
	 * @param unidadCajaPequena the unidadCajaPequena to set
	 */
	public void setUnidadCajaPequena(String unidadCajaPequena) {
		this.unidadCajaPequena = unidadCajaPequena;
	}

}