package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author peextrvela
 *
 */
public class ParametroCursoCapacitacion extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4121365385255385131L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoPrograma;
	private String nombrePrograma;
	private String valorInicial;
	private String valorIncremento;
	private String estadoRegistro;
	private String codigoServicioInicial;
	private String codigoServicioFinal;
	private String numeroLoteDiario;
	private String numeroLoteRegion;
	private String nivelUnidadAdm;
	private String nivelGenerarPlanilla;	
	private String planillaActual;
	private String codigoPaisDatamart;	
	private String indicadorNombreCompleto;
	private String indicadorPlanilla;
	private String indicadorConexionExterna;
	//indicador recod,bloqueo
	private String indicadorRecodificacion;
	private String indicadorBloqueo;
	private String indicadorEquiMensaje;	
	private String indicadorStatusConsultora;
	private String indicadorEnvioCronograma;
	private String valorDelimitadorNombreCompleto;
	private String indicadorPosibleEgreso;
	private String indicadorClasificacionEquivalencia;
	//
	private String indicadorEnvioProgramadas; 
	private String indicadorEnvioResumen;
	
	private String indicadorRegistroInscripciones;
	private String indicadorDespachoClasificacion;
	private String indicadorRegistroPlanillasNoProcesadas;
	private String numeroPedidosRegPlanillasNoProcesadas;
	//
	private String indicadorConsultoraRezagada;
	private String indicadorDesbloqueo;
	/**
	 * @return the indicadorDesbloqueo
	 */
	public String getIndicadorDesbloqueo() {
		return indicadorDesbloqueo;
	}
	/**
	 * @param indicadorDesbloqueo the indicadorDesbloqueo to set
	 */
	public void setIndicadorDesbloqueo(String indicadorDesbloqueo) {
		this.indicadorDesbloqueo = indicadorDesbloqueo;
	}
	/**
	 * @return Returns the indicadorConsultoraRezagada.
	 */
	public String getIndicadorConsultoraRezagada() {
		return indicadorConsultoraRezagada;
	}
	/**
	 * @param indicadorConsultoraRezagada The indicadorConsultoraRezagada to set.
	 */
	public void setIndicadorConsultoraRezagada(String indicadorConsultoraRezagada) {
		this.indicadorConsultoraRezagada = indicadorConsultoraRezagada;
	}
	/**
	 * @return Returns the indicadorBloqueo.
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}
	/**
	 * @param indicadorBloqueo The indicadorBloqueo to set.
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}
	/**
	 * @return Returns the indicadorRecodificacion.
	 */
	public String getIndicadorRecodificacion() {
		return indicadorRecodificacion;
	}
	/**
	 * @param indicadorRecodificacion The indicadorRecodificacion to set.
	 */
	public void setIndicadorRecodificacion(String indicadorRecodificacion) {
		this.indicadorRecodificacion = indicadorRecodificacion;
	}
	public String getIndicadorConexionExterna() {
		return indicadorConexionExterna;
	}
	public void setIndicadorConexionExterna(String indicadorConexionExterna) {
		this.indicadorConexionExterna = indicadorConexionExterna;
	}
	public String getIndicadorNombreCompleto() {
		return indicadorNombreCompleto;
	}
	public void setIndicadorNombreCompleto(String indicadorNombreCompleto) {
		this.indicadorNombreCompleto = indicadorNombreCompleto;
	}
	/**
	 * @return Returns the numeroLoteDiario.
	 */
	public String getNumeroLoteDiario() {
		return numeroLoteDiario;
	}
	/**
	 * @param numeroLoteDiario The numeroLoteDiario to set.
	 */
	public void setNumeroLoteDiario(String numeroLoteDiario) {
		this.numeroLoteDiario = numeroLoteDiario;
	}
	/**
	 * @return Returns the numeroLoteRegion.
	 */
	public String getNumeroLoteRegion() {
		return numeroLoteRegion;
	}
	/**
	 * @param numeroLoteRegion The numeroLoteRegion to set.
	 */
	public void setNumeroLoteRegion(String numeroLoteRegion) {
		this.numeroLoteRegion = numeroLoteRegion;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the nombrePrograma.
	 */
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	/**
	 * @param nombrePrograma The nombrePrograma to set.
	 */
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	/**
	 * @return Returns the valorIncremento.
	 */
	public String getValorIncremento() {
		return valorIncremento;
	}
	/**
	 * @param valorIncremento The valorIncremento to set.
	 */
	public void setValorIncremento(String valorIncremento) {
		this.valorIncremento = valorIncremento;
	}
	/**
	 * @return Returns the valorInicial.
	 */
	public String getValorInicial() {
		return valorInicial;
	}
	/**
	 * @param valorInicial The valorInicial to set.
	 */
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the codigoServicioFinal.
	 */
	public String getCodigoServicioFinal() {
		return codigoServicioFinal;
	}
	/**
	 * @param codigoServicioFinal The codigoServicioFinal to set.
	 */
	public void setCodigoServicioFinal(String codigoServicioFinal) {
		this.codigoServicioFinal = codigoServicioFinal;
	}
	/**
	 * @return Returns the codigoServicioInicial.
	 */
	public String getCodigoServicioInicial() {
		return codigoServicioInicial;
	}
	/**
	 * @param codigoServicioInicial The codigoServicioInicial to set.
	 */
	public void setCodigoServicioInicial(String codigoServicioInicial) {
		this.codigoServicioInicial = codigoServicioInicial;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return Returns the nivelUnidadAdm.
	 */
	public String getNivelUnidadAdm() {
		return nivelUnidadAdm;
	}
	/**
	 * @param nivelUnidadAdm The nivelUnidadAdm to set.
	 */
	public void setNivelUnidadAdm(String nivelUnidadAdm) {
		this.nivelUnidadAdm = nivelUnidadAdm;
	}
	/**
	 * @return Returns the planillaActual.
	 */
	public String getPlanillaActual() {
		return planillaActual;
	}
	/**
	 * @param planillaActual The planillaActual to set.
	 */
	public void setPlanillaActual(String planillaActual) {
		this.planillaActual = planillaActual;
	}
	/**
	 * @return Returns the codigoPaisDatamart.
	 */
	public String getCodigoPaisDatamart() {
		return codigoPaisDatamart;
	}
	/**
	 * @param codigoPaisDatamart The codigoPaisDatamart to set.
	 */
	public void setCodigoPaisDatamart(String codigoPaisDatamart) {
		this.codigoPaisDatamart = codigoPaisDatamart;
	}
	public String getIndicadorPlanilla() {
		return indicadorPlanilla;
	}
	public void setIndicadorPlanilla(String indicadorPlanilla) {
		this.indicadorPlanilla = indicadorPlanilla;
	}
	public String getNivelGenerarPlanilla() {
		return nivelGenerarPlanilla;
	}
	public void setNivelGenerarPlanilla(String nivelGenerarPlanilla) {
		this.nivelGenerarPlanilla = nivelGenerarPlanilla;
	}
	/**
	 * @return Returns the indicadorEquiMensaje.
	 */
	public String getIndicadorEquiMensaje() {
		return indicadorEquiMensaje;
	}
	/**
	 * @param indicadorEquiMensaje The indicadorEquiMensaje to set.
	 */
	public void setIndicadorEquiMensaje(String indicadorEquiMensaje) {
		this.indicadorEquiMensaje = indicadorEquiMensaje;
	}
	
	/**
	 * @return Returns the indicadorStatusConsultora.
	 */
	public String getIndicadorStatusConsultora() {
		return indicadorStatusConsultora;
	}
	/**
	 * @param indicadorStatusConsultora The indicadorStatusConsultora to set.
	 */
	public void setIndicadorStatusConsultora(String indicadorStatusConsultora) {
		this.indicadorStatusConsultora = indicadorStatusConsultora;
	}
	/**
	 * @return Returns the valorDelimitadorNombreCompleto.
	 */
	public String getValorDelimitadorNombreCompleto() {
		return valorDelimitadorNombreCompleto;
	}
	/**
	 * @param valorDelimitadorNombreCompleto The valorDelimitadorNombreCompleto to set.
	 */
	public void setValorDelimitadorNombreCompleto(
			String valorDelimitadorNombreCompleto) {
		this.valorDelimitadorNombreCompleto = valorDelimitadorNombreCompleto;
	}
	/**
	 * @return Returns the indicadorEnvioCronograma.
	 */
	public String getIndicadorEnvioCronograma() {
		return indicadorEnvioCronograma;
	}
	/**
	 * @param indicadorEnvioCronograma The indicadorEnvioCronograma to set.
	 */
	public void setIndicadorEnvioCronograma(String indicadorEnvioCronograma) {
		this.indicadorEnvioCronograma = indicadorEnvioCronograma;
	}
	/**
	 * @return Returns the indicadorPosibleEgreso.
	 */
	public String getIndicadorPosibleEgreso() {
		return indicadorPosibleEgreso;
	}
	/**
	 * @param indicadorPosibleEgreso The indicadorPosibleEgreso to set.
	 */
	public void setIndicadorPosibleEgreso(String indicadorPosibleEgreso) {
		this.indicadorPosibleEgreso = indicadorPosibleEgreso;
	}
	/**
	 * @return Returns the indicadorClasificacionEquivalencia.
	 */
	public String getIndicadorClasificacionEquivalencia() {
		return indicadorClasificacionEquivalencia;
	}
	/**
	 * @param indicadorClasificacionEquivalencia The indicadorClasificacionEquivalencia to set.
	 */
	public void setIndicadorClasificacionEquivalencia(
			String indicadorClasificacionEquivalencia) {
		this.indicadorClasificacionEquivalencia = indicadorClasificacionEquivalencia;
	}
	/**
	 * @return Returns the indicadorEnvioProgramadas.
	 */
	public String getIndicadorEnvioProgramadas() {
		return indicadorEnvioProgramadas;
	}
	/**
	 * @param indicadorEnvioProgramadas The indicadorEnvioProgramadas to set.
	 */
	public void setIndicadorEnvioProgramadas(String indicadorEnvioProgramadas) {
		this.indicadorEnvioProgramadas = indicadorEnvioProgramadas;
	}
	/**
	 * @return Returns the indicadorEnvioResumen.
	 */
	public String getIndicadorEnvioResumen() {
		return indicadorEnvioResumen;
	}
	/**
	 * @param indicadorEnvioResumen The indicadorEnvioResumen to set.
	 */
	public void setIndicadorEnvioResumen(String indicadorEnvioResumen) {
		this.indicadorEnvioResumen = indicadorEnvioResumen;
	}
	/**
	 * @return Returns the indicadorRegistroInscripciones.
	 */
	public String getIndicadorRegistroInscripciones() {
		return indicadorRegistroInscripciones;
	}
	/**
	 * @param indicadorRegistroInscripciones The indicadorRegistroInscripciones to set.
	 */
	public void setIndicadorRegistroInscripciones(
			String indicadorRegistroInscripciones) {
		this.indicadorRegistroInscripciones = indicadorRegistroInscripciones;
	}
	/**
	 * @return Returns the indicadorDespachoClasificacion.
	 */
	public String getIndicadorDespachoClasificacion() {
		return indicadorDespachoClasificacion;
	}
	/**
	 * @param indicadorDespachoClasificacion The indicadorDespachoClasificacion to set.
	 */
	public void setIndicadorDespachoClasificacion(
			String indicadorDespachoClasificacion) {
		this.indicadorDespachoClasificacion = indicadorDespachoClasificacion;
	}
	public String getIndicadorRegistroPlanillasNoProcesadas() {
		return indicadorRegistroPlanillasNoProcesadas;
	}
	public void setIndicadorRegistroPlanillasNoProcesadas(
			String indicadorRegistroPlanillasNoProcesadas) {
		this.indicadorRegistroPlanillasNoProcesadas = indicadorRegistroPlanillasNoProcesadas;
	}
	public String getNumeroPedidosRegPlanillasNoProcesadas() {
		return numeroPedidosRegPlanillasNoProcesadas;
	}
	public void setNumeroPedidosRegPlanillasNoProcesadas(
			String numeroPedidosRegPlanillasNoProcesadas) {
		this.numeroPedidosRegPlanillasNoProcesadas = numeroPedidosRegPlanillasNoProcesadas;
	}

}
