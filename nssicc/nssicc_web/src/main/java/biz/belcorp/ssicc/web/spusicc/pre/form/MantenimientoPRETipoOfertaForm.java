package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPRETipoOfertaForm extends BaseEditForm implements Serializable{

	private static final long serialVersionUID = -2727471777111780423L;
	
	private String oidTipoOferta;
	private String codigoCanal;
	private String codigoTipoOferta;
	private String descripcion;
	private String indComisionable;
	private String indOtorgaPuntaje;
	private String indEstadisticable;
	private String indAportaMontoMinimo;
	private String indAportaEscalaDesc;
	private String indGrat;
	private String codMedioVenta;
	private String codSeccionPaquete;
	
	private boolean indComisionableBol;
	private boolean indOtorgaPuntajeBol;
	private boolean indEstadisticableBol;
	private boolean indAportaMontoMinimoBol;
	private boolean indAportaEscalaDescBol;
	private boolean indGratBol;
	

	/**
	 * @return the indComisionableBol
	 */
	public boolean isIndComisionableBol() {
		return indComisionableBol;
	}
	/**
	 * @param indComisionableBol the indComisionableBol to set
	 */
	public void setIndComisionableBol(boolean indComisionableBol) {
		this.indComisionableBol = indComisionableBol;
	}
	/**
	 * @return the indOtorgaPuntajeBol
	 */
	public boolean isIndOtorgaPuntajeBol() {
		return indOtorgaPuntajeBol;
	}
	/**
	 * @param indOtorgaPuntajeBol the indOtorgaPuntajeBol to set
	 */
	public void setIndOtorgaPuntajeBol(boolean indOtorgaPuntajeBol) {
		this.indOtorgaPuntajeBol = indOtorgaPuntajeBol;
	}
	/**
	 * @return the indEstadisticableBol
	 */
	public boolean isIndEstadisticableBol() {
		return indEstadisticableBol;
	}
	/**
	 * @param indEstadisticableBol the indEstadisticableBol to set
	 */
	public void setIndEstadisticableBol(boolean indEstadisticableBol) {
		this.indEstadisticableBol = indEstadisticableBol;
	}
	/**
	 * @return the indAportaMontoMinimoBol
	 */
	public boolean isIndAportaMontoMinimoBol() {
		return indAportaMontoMinimoBol;
	}
	/**
	 * @param indAportaMontoMinimoBol the indAportaMontoMinimoBol to set
	 */
	public void setIndAportaMontoMinimoBol(boolean indAportaMontoMinimoBol) {
		this.indAportaMontoMinimoBol = indAportaMontoMinimoBol;
	}
	/**
	 * @return the indAportaEscalaDescBol
	 */
	public boolean isIndAportaEscalaDescBol() {
		return indAportaEscalaDescBol;
	}
	/**
	 * @param indAportaEscalaDescBol the indAportaEscalaDescBol to set
	 */
	public void setIndAportaEscalaDescBol(boolean indAportaEscalaDescBol) {
		this.indAportaEscalaDescBol = indAportaEscalaDescBol;
	}
	/**
	 * @return the indGratBol
	 */
	public boolean isIndGratBol() {
		return indGratBol;
	}
	/**
	 * @param indGratBol the indGratBol to set
	 */
	public void setIndGratBol(boolean indGratBol) {
		this.indGratBol = indGratBol;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the codigoTipoOferta
	 */
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}
	/**
	 * @param codigoTipoOferta the codigoTipoOferta to set
	 */
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
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
	/**
	 * @return the indComisionable
	 */
	public String getIndComisionable() {
		return indComisionable;
	}
	/**
	 * @param indComisionable the indComisionable to set
	 */
	public void setIndComisionable(String indComisionable) {
		this.indComisionable = indComisionable;
	}
	/**
	 * @return the indOtorgaPuntaje
	 */
	public String getIndOtorgaPuntaje() {
		return indOtorgaPuntaje;
	}
	/**
	 * @param indOtorgaPuntaje the indOtorgaPuntaje to set
	 */
	public void setIndOtorgaPuntaje(String indOtorgaPuntaje) {
		this.indOtorgaPuntaje = indOtorgaPuntaje;
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
	 * @return the indAportaMontoMinimo
	 */
	public String getIndAportaMontoMinimo() {
		return indAportaMontoMinimo;
	}
	/**
	 * @param indAportaMontoMinimo the indAportaMontoMinimo to set
	 */
	public void setIndAportaMontoMinimo(String indAportaMontoMinimo) {
		this.indAportaMontoMinimo = indAportaMontoMinimo;
	}
	/**
	 * @return the indAportaEscalaDesc
	 */
	public String getIndAportaEscalaDesc() {
		return indAportaEscalaDesc;
	}
	/**
	 * @param indAportaEscalaDesc the indAportaEscalaDesc to set
	 */
	public void setIndAportaEscalaDesc(String indAportaEscalaDesc) {
		this.indAportaEscalaDesc = indAportaEscalaDesc;
	}
	/**
	 * @return the indGrat
	 */
	public String getIndGrat() {
		return indGrat;
	}
	/**
	 * @param indGrat the indGrat to set
	 */
	public void setIndGrat(String indGrat) {
		this.indGrat = indGrat;
	}
	/**
	 * @return the codMedioVenta
	 */
	public String getCodMedioVenta() {
		return codMedioVenta;
	}
	/**
	 * @param codMedioVenta the codMedioVenta to set
	 */
	public void setCodMedioVenta(String codMedioVenta) {
		this.codMedioVenta = codMedioVenta;
	}
	/**
	 * @return the codSeccionPaquete
	 */
	public String getCodSeccionPaquete() {
		return codSeccionPaquete;
	}
	/**
	 * @param codSeccionPaquete the codSeccionPaquete to set
	 */
	public void setCodSeccionPaquete(String codSeccionPaquete) {
		this.codSeccionPaquete = codSeccionPaquete;
	}

	
	
}
