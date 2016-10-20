package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/** 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 */
public class MantenimientoFLXConsultoraForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCliente;
	private String codigoCampanyaFacturacion;
	private String nombreConsultora;
	private String lineaCredito;
	private String pedidoBase;
	private String campanyaComunicacion;
	private String calificacionExperiencia;
	private String calificacionComportamiento;
	private String flagHabilitado;
	private String flagActivo;
	private String flagComunicado;
	private String flagEnviado;
	private String flagCancelado;
	
	private String ultimaModificacionActivar;
	private String ultimaModificacionRechazar;
	
	private String flagEstatus;
	
	private String codigoMotivo;
	
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
	 * @return the codigoCampanyaFacturacion
	 */
	public String getCodigoCampanyaFacturacion() {
		return codigoCampanyaFacturacion;
	}
	/**
	 * @param codigoCampanyaFacturacion the codigoCampanyaFacturacion to set
	 */
	public void setCodigoCampanyaFacturacion(String codigoCampanyaFacturacion) {
		this.codigoCampanyaFacturacion = codigoCampanyaFacturacion;
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
	 * @return the lineaCredito
	 */
	public String getLineaCredito() {
		return lineaCredito;
	}
	/**
	 * @param lineaCredito the lineaCredito to set
	 */
	public void setLineaCredito(String lineaCredito) {
		this.lineaCredito = lineaCredito;
	}
	/**
	 * @return the pedidoBase
	 */
	public String getPedidoBase() {
		return pedidoBase;
	}
	/**
	 * @param pedidoBase the pedidoBase to set
	 */
	public void setPedidoBase(String pedidoBase) {
		this.pedidoBase = pedidoBase;
	}
	/**
	 * @return the campanyaComunicacion
	 */
	public String getCampanyaComunicacion() {
		return campanyaComunicacion;
	}
	/**
	 * @param campanyaComunicacion the campanyaComunicacion to set
	 */
	public void setCampanyaComunicacion(String campanyaComunicacion) {
		this.campanyaComunicacion = campanyaComunicacion;
	}
	/**
	 * @return the calificacionExperiencia
	 */
	public String getCalificacionExperiencia() {
		return calificacionExperiencia;
	}
	/**
	 * @param calificacionExperiencia the calificacionExperiencia to set
	 */
	public void setCalificacionExperiencia(String calificacionExperiencia) {
		this.calificacionExperiencia = calificacionExperiencia;
	}
	/**
	 * @return the calificacionComportamiento
	 */
	public String getCalificacionComportamiento() {
		return calificacionComportamiento;
	}
	/**
	 * @param calificacionComportamiento the calificacionComportamiento to set
	 */
	public void setCalificacionComportamiento(String calificacionComportamiento) {
		this.calificacionComportamiento = calificacionComportamiento;
	}
	/**
	 * @return the flagHabilitado
	 */
	public String getFlagHabilitado() {
		return flagHabilitado;
	}
	/**
	 * @param flagHabilitado the flagHabilitado to set
	 */
	public void setFlagHabilitado(String flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}
	/**
	 * @return the flagActivo
	 */
	public String getFlagActivo() {
		return flagActivo;
	}
	/**
	 * @param flagActivo the flagActivo to set
	 */
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}
	/**
	 * @return the flagComunicado
	 */
	public String getFlagComunicado() {
		return flagComunicado;
	}
	/**
	 * @param flagComunicado the flagComunicado to set
	 */
	public void setFlagComunicado(String flagComunicado) {
		this.flagComunicado = flagComunicado;
	}
	/**
	 * @return the flagEnviado
	 */
	public String getFlagEnviado() {
		return flagEnviado;
	}
	/**
	 * @return the flagCancelado
	 */
	public String getFlagCancelado() {
		return flagCancelado;
	}
	/**
	 * @param flagCancelado the flagCancelado to set
	 */
	public void setFlagCancelado(String flagCancelado) {
		this.flagCancelado = flagCancelado;
	}
	/**
	 * @param flagEnviado the flagEnviado to set
	 */
	public void setFlagEnviado(String flagEnviado) {
		this.flagEnviado = flagEnviado;
	}
	/**
	 * @return the ultimaModificacionActivar
	 */
	public String getUltimaModificacionActivar() {
		return ultimaModificacionActivar;
	}
	/**
	 * @param ultimaModificacionActivar the ultimaModificacionActivar to set
	 */
	public void setUltimaModificacionActivar(String ultimaModificacionActivar) {
		this.ultimaModificacionActivar = ultimaModificacionActivar;
	}
	/**
	 * @return the ultimaModificacionRechazar
	 */
	public String getUltimaModificacionRechazar() {
		return ultimaModificacionRechazar;
	}
	/**
	 * @param ultimaModificacionRechazar the ultimaModificacionRechazar to set
	 */
	public void setUltimaModificacionRechazar(String ultimaModificacionRechazar) {
		this.ultimaModificacionRechazar = ultimaModificacionRechazar;
	}
	/**
	 * @return the flagEstatus
	 */
	public String getFlagEstatus() {
		return flagEstatus;
	}
	/**
	 * @param flagEstatus the flagEstatus to set
	 */
	public void setFlagEstatus(String flagEstatus) {
		this.flagEstatus = flagEstatus;
	}
	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}
	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}	
}
