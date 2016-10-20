package biz.belcorp.ssicc.web.spusicc.mav.form;
import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


public class MantenimientoMAVConfiguracionCondicionEnvioForm extends BaseEditForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String correlativoMAV;
	private String codigoConsRest;
	private String correlativoConsideracion;
	private String numeroUnidades;
	private String valCondi1;
	private String valCondi2;
	private String valCondi3;
	private String codigoCliente;
	private String indicadorEnvio;
	private String oidEnvio;
	
	private String descripcionRegion;
	private String descripcionZona;
	private String descripcionCapacitadora;
	
	private String indicadorActivo;
	
	private boolean indicadorEnvioAux;
	
	/**
	 * @return the correlativoMAV
	 */
	public String getCorrelativoMAV() {
		return correlativoMAV;
	}
	/**
	 * @param correlativoMAV the correlativoMAV to set
	 */
	public void setCorrelativoMAV(String correlativoMAV) {
		this.correlativoMAV = correlativoMAV;
	}
	/**
	 * @return the codigoConsRest
	 */
	public String getCodigoConsRest() {
		return codigoConsRest;
	}
	/**
	 * @param codigoConsRest the codigoConsRest to set
	 */
	public void setCodigoConsRest(String codigoConsRest) {
		this.codigoConsRest = codigoConsRest;
	}
	/**
	 * @return the correlativoConsideracion
	 */
	public String getCorrelativoConsideracion() {
		return correlativoConsideracion;
	}
	/**
	 * @param correlativoConsideracion the correlativoConsideracion to set
	 */
	public void setCorrelativoConsideracion(String correlativoConsideracion) {
		this.correlativoConsideracion = correlativoConsideracion;
	}
	/**
	 * @return the numeroUnidades
	 */
	public String getNumeroUnidades() {
		return numeroUnidades;
	}
	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}
	/**
	 * @return the valCondi1
	 */
	public String getValCondi1() {
		return valCondi1;
	}
	/**
	 * @param valCondi1 the valCondi1 to set
	 */
	public void setValCondi1(String valCondi1) {
		this.valCondi1 = valCondi1;
	}
	/**
	 * @return the valCondi2
	 */
	public String getValCondi2() {
		return valCondi2;
	}
	/**
	 * @param valCondi2 the valCondi2 to set
	 */
	public void setValCondi2(String valCondi2) {
		this.valCondi2 = valCondi2;
	}
	/**
	 * @return the valCondi3
	 */
	public String getValCondi3() {
		return valCondi3;
	}
	/**
	 * @param valCondi3 the valCondi3 to set
	 */
	public void setValCondi3(String valCondi3) {
		this.valCondi3 = valCondi3;
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
	 * @return the indicadorEnvio
	 */
	public String getIndicadorEnvio() {
		return indicadorEnvio;
	}
	/**
	 * @param indicadorEnvio the indicadorEnvio to set
	 */
	public void setIndicadorEnvio(String indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}
	/**
	 * @return the oidEnvio
	 */
	public String getOidEnvio() {
		return oidEnvio;
	}
	/**
	 * @param oidEnvio the oidEnvio to set
	 */
	public void setOidEnvio(String oidEnvio) {
		this.oidEnvio = oidEnvio;
	}
	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return the descripcionCapacitadora
	 */
	public String getDescripcionCapacitadora() {
		return descripcionCapacitadora;
	}
	/**
	 * @param descripcionCapacitadora the descripcionCapacitadora to set
	 */
	public void setDescripcionCapacitadora(String descripcionCapacitadora) {
		this.descripcionCapacitadora = descripcionCapacitadora;
	}	
	
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the indicadorEnvioAux
	 */
	public boolean isIndicadorEnvioAux() {
		return indicadorEnvioAux;
	}
	/**
	 * @param indicadorEnvioAux the indicadorEnvioAux to set
	 */
	public void setIndicadorEnvioAux(boolean indicadorEnvioAux) {
		this.indicadorEnvioAux = indicadorEnvioAux;
	}
}
