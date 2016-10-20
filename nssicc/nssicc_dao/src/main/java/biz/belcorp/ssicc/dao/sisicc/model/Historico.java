/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Representa a la entidad BAS_HISTO_LOTES de la base de datos.
 * 
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class Historico extends AuditableBaseObject implements Serializable, Cloneable {

	private static final long serialVersionUID = 1960528487241945426L;

	private String codigoPais;

	private String codigoSistema;

	private String numeroLote;

	private String codigoInterfaz;

	private String codigoInterfazEmpaquetada;

	private String descripcionLote;

	private Timestamp fechaInicioProceso;

	private Timestamp fechaFinProceso;

	private String flagError;

	private Long registrosProcesados;

	private Long registrosErroneos;

	private String observaciones;

	private String usuarioProceso;

	private String estadoProceso;

	private String descripcionError;

	private Sistema sistema;

	private Interfaz interfaz;
	
	private String recomendacion;
	
	private String nombreArchivo;
	
	private String historicoFileName;
	
	private Long idProcesoBatch;
	
	private Long ordenHilo;
	
	private Long orderEjecucionHisto;
	
	private Long nivelHilo;
	
	
	/* INI FRMAEWORK NSSICC */
	private String flagErrorProcesoAnteriorInterfaz;
	
	private Timestamp fechaIniProcesoAnteriorInterfaz;

	private Timestamp fechaFinProcesoAnteriorInterfaz;
	
	private String flagErrorInterfaz;
	
	private Timestamp fechaIniInterfaz;

	private Timestamp fechaFinInterfaz;
	
	private String flagErrorProcesoPosteriorInterfaz;
	
	private Timestamp fechaIniProcesoPosteriorInterfaz;

	private Timestamp fechaFinProcesoPosteriorInterfaz;

	/* FIN FRMAEWORK NSSICC */
	
	
	public Historico() {
		codigoPais = "";
		codigoSistema = "";
		numeroLote = "";
		codigoInterfaz = "";
		codigoInterfazEmpaquetada = "";
		descripcionLote = "";
		flagError = "";
		registrosProcesados = new Long(0);
		registrosErroneos = new Long(0);
		observaciones = "";
		usuarioProceso = "";
		estadoProceso = "";
		descripcionError = "";
		recomendacion = "";
		nombreArchivo = "";
		historicoFileName = "";
		ordenHilo = new Long(0);
		orderEjecucionHisto = new Long(0);
		nivelHilo = new Long(0);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		Historico obj = null;
	    obj = (Historico)super.clone();
	    return obj;
    }
	
	/**
	 * @return the orderEjecucionHisto
	 */
	public Long getOrderEjecucionHisto() {
		return orderEjecucionHisto;
	}

	/**
	 * @param orderEjecucionHisto the orderEjecucionHisto to set
	 */
	public void setOrderEjecucionHisto(Long orderEjecucionHisto) {
		this.orderEjecucionHisto = orderEjecucionHisto;
	}

	/**
	 * @return historicoFileName
	 */
	public String getHistoricoFileName() {
		return historicoFileName;
	}

	/**
	 * @param historicoFileName
	 */
	public void setHistoricoFileName(String historicoFileName) {
		this.historicoFileName = historicoFileName;
	}

	public void registroProcesado() {
		registrosProcesados = new Long(registrosProcesados.longValue() + 1);
	}

	public void registroErroneo() {
		registrosErroneos = new Long(registrosErroneos.longValue() + 1);
	}

	/**
	 * @return Returns the interfaz.
	 */
	public Interfaz getInterfaz() {
		return interfaz;
	}

	/**
	 * @param interfaz
	 *            The interfaz to set.
	 */
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/**
	 * @return Returns the sistema.
	 */
	public Sistema getSistema() {
		return sistema;
	}

	/**
	 * @param sistema
	 *            The sistema to set.
	 */
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz
	 *            The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return Returns the descripcionLote.
	 */
	public String getDescripcionLote() {
		return descripcionLote;
	}

	/**
	 * @param descripcionLote
	 *            The descripcionLote to set.
	 */
	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	/**
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}

	/**
	 * @param estadoProceso
	 *            The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	/**
	 * @return Returns the fechaFinProceso.
	 */
	public Timestamp getFechaFinProceso() {
		return fechaFinProceso;
	}

	/**
	 * @param fechaFinProceso
	 *            The fechaFinProceso to set.
	 */
	public void setFechaFinProceso(Timestamp fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	/**
	 * @return Returns the fechaInicioProceso.
	 */
	public Timestamp getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	/**
	 * @param fechaInicioProceso
	 *            The fechaInicioProceso to set.
	 */
	public void setFechaInicioProceso(Timestamp fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	/**
	 * @return Returns the flagError.
	 */
	public String getFlagError() {
		return flagError;
	}

	/**
	 * @param flagError
	 *            The flagError to set.
	 */
	public void setFlagError(String flagError) {
		this.flagError = flagError;
	}

	/**
	 * @return Returns the numeroLote.
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            The numeroLote to set.
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return Returns the registrosErroneos.
	 */
	public Long getRegistrosErroneos() {
		return registrosErroneos;
	}

	/**
	 * @param registrosErroneos
	 *            The registrosErroneos to set.
	 */
	public void setRegistrosErroneos(Long registrosErroneos) {
		this.registrosErroneos = registrosErroneos;
	}

	/**
	 * @return Returns the registrosProcesados.
	 */
	public Long getRegistrosProcesados() {
		return registrosProcesados;
	}

	/**
	 * @param registrosProcesados
	 *            The registrosProcesados to set.
	 */
	public void setRegistrosProcesados(Long registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	/**
	 * @return Returns the usuarioProceso.
	 */
	public String getUsuarioProceso() {
		return usuarioProceso;
	}

	/**
	 * @param usuarioProceso
	 *            The usuarioProceso to set.
	 */
	public void setUsuarioProceso(String usuarioProceso) {
		this.usuarioProceso = usuarioProceso;
	}

	/**
	 * @return Returns the codigoInterfazEmpaquetada.
	 */
	public String getCodigoInterfazEmpaquetada() {
		return codigoInterfazEmpaquetada;
	}

	/**
	 * @param codigoInterfazEmpaquetada
	 *            The codigoInterfazEmpaquetada to set.
	 */
	public void setCodigoInterfazEmpaquetada(String codigoInterfazEmpaquetada) {
		this.codigoInterfazEmpaquetada = codigoInterfazEmpaquetada;
	}

	/**
	 * @return Returns the descripcionError.
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError
	 *            The descripcionError to set.
	 */
	public void setDescripcionError(String descripcionError) {
		//this.descripcionError = StringUtils.abbreviate(descripcionError, 500);
		this.descripcionError = StringUtils.abbreviate(descripcionError, 4000);
	}

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Historico)) {
            return false;
        }
        Historico rhs = (Historico) object;
        return new EqualsBuilder().append(this.fechaFinProceso,
                rhs.fechaFinProceso).append(this.descripcionLote,
                rhs.descripcionLote).append(this.codigoInterfazEmpaquetada,
                rhs.codigoInterfazEmpaquetada)
                .append(this.sistema, rhs.sistema).append(this.interfaz,
                        rhs.interfaz).append(this.auditInfo, rhs.auditInfo)
                .append(this.codigoSistema, rhs.codigoSistema).append(
                        this.flagError, rhs.flagError).append(
                        this.observaciones, rhs.observaciones).append(
                        this.codigoPais, rhs.codigoPais).append(
                        this.registrosErroneos, rhs.registrosErroneos).append(
                        this.numeroLote, rhs.numeroLote).append(
                        this.estadoProceso, rhs.estadoProceso).append(
                        this.codigoInterfaz, rhs.codigoInterfaz).append(
                        this.descripcionError, rhs.descripcionError).append(
                        this.fechaInicioProceso, rhs.fechaInicioProceso)
                .append(this.registrosProcesados, rhs.registrosProcesados)
                .append(this.usuarioProceso, rhs.usuarioProceso).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(775438759, -929459445).append(
                this.fechaFinProceso).append(this.descripcionLote).append(
                this.codigoInterfazEmpaquetada).append(this.sistema).append(
                this.interfaz).append(this.auditInfo)
                .append(this.codigoSistema).append(this.flagError).append(
                        this.observaciones).append(this.codigoPais).append(
                        this.registrosErroneos).append(this.numeroLote).append(
                        this.estadoProceso).append(this.codigoInterfaz).append(
                        this.descripcionError).append(this.fechaInicioProceso)
                .append(this.registrosProcesados).append(this.usuarioProceso)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append(
                        "registrosErroneos", this.registrosErroneos).append(
                        "flagError", this.flagError).append("descripcionError",
                        this.descripcionError)
                .append("interfaz", this.interfaz).append("observaciones",
                        this.observaciones).append("usuarioProceso",
                        this.usuarioProceso).append("registrosProcesados",
                        this.registrosProcesados).append("auditInfo",
                        this.auditInfo).append("codigoInterfaz",
                        this.codigoInterfaz).append("estadoProceso",
                        this.estadoProceso).append("fechaFinProceso",
                        this.fechaFinProceso).append("sistema", this.sistema)
                .append("numeroLote", this.numeroLote).append(
                        "fechaInicioProceso", this.fechaInicioProceso).append(
                        "codigoInterfazEmpaquetada",
                        this.codigoInterfazEmpaquetada).append("codigoSistema",
                        this.codigoSistema).append("descripcionLote",
                        this.descripcionLote).toString();
    }

	/**
	 * @return the recomendacion
	 */
	public String getRecomendacion() {
		return recomendacion;
	}

	/**
	 * @param recomendacion the recomendacion to set
	 */
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the idProcesoBatch
	 */
	public Long getIdProcesoBatch() {
		return idProcesoBatch;
	}

	/**
	 * @param idProcesoBatch the idProcesoBatch to set
	 */
	public void setIdProcesoBatch(Long idProcesoBatch) {
		this.idProcesoBatch = idProcesoBatch;
	}

	/**
	 * @return the ordenHilo
	 */
	public Long getOrdenHilo() {
		return ordenHilo;
	}

	/**
	 * @param ordenHilo the ordenHilo to set
	 */
	public void setOrdenHilo(Long ordenHilo) {
		this.ordenHilo = ordenHilo;
	}

	/**
	 * @return the nivelHilo
	 */
	public Long getNivelHilo() {
		return nivelHilo;
	}

	/**
	 * @param nivelHilo the nivelHilo to set
	 */
	public void setNivelHilo(Long nivelHilo) {
		this.nivelHilo = nivelHilo;
	}

	
	/* INI FRAMEWORK NSSICC */
	
	/**
	 * @return the flagErrorProcesoAnteriorInterfaz
	 */
	public String getFlagErrorProcesoAnteriorInterfaz() {
		return flagErrorProcesoAnteriorInterfaz;
	}

	/**
	 * @param flagErrorProcesoAnteriorInterfaz the flagErrorProcesoAnteriorInterfaz to set
	 */
	public void setFlagErrorProcesoAnteriorInterfaz(
			String flagErrorProcesoAnteriorInterfaz) {
		this.flagErrorProcesoAnteriorInterfaz = flagErrorProcesoAnteriorInterfaz;
	}

	/**
	 * @return the fechaIniProcesoAnteriorInterfaz
	 */
	public Timestamp getFechaIniProcesoAnteriorInterfaz() {
		return fechaIniProcesoAnteriorInterfaz;
	}

	/**
	 * @param fechaIniProcesoAnteriorInterfaz the fechaIniProcesoAnteriorInterfaz to set
	 */
	public void setFechaIniProcesoAnteriorInterfaz(
			Timestamp fechaIniProcesoAnteriorInterfaz) {
		this.fechaIniProcesoAnteriorInterfaz = fechaIniProcesoAnteriorInterfaz;
	}

	/**
	 * @return the fechaFinProcesoAnteriorInterfaz
	 */
	public Timestamp getFechaFinProcesoAnteriorInterfaz() {
		return fechaFinProcesoAnteriorInterfaz;
	}

	/**
	 * @param fechaFinProcesoAnteriorInterfaz the fechaFinProcesoAnteriorInterfaz to set
	 */
	public void setFechaFinProcesoAnteriorInterfaz(
			Timestamp fechaFinProcesoAnteriorInterfaz) {
		this.fechaFinProcesoAnteriorInterfaz = fechaFinProcesoAnteriorInterfaz;
	}

	/**
	 * @return the flagErrorInterfaz
	 */
	public String getFlagErrorInterfaz() {
		return flagErrorInterfaz;
	}

	/**
	 * @param flagErrorInterfaz the flagErrorInterfaz to set
	 */
	public void setFlagErrorInterfaz(String flagErrorInterfaz) {
		this.flagErrorInterfaz = flagErrorInterfaz;
	}

	/**
	 * @return the fechaIniInterfaz
	 */
	public Timestamp getFechaIniInterfaz() {
		return fechaIniInterfaz;
	}

	/**
	 * @param fechaIniInterfaz the fechaIniInterfaz to set
	 */
	public void setFechaIniInterfaz(Timestamp fechaIniInterfaz) {
		this.fechaIniInterfaz = fechaIniInterfaz;
	}

	/**
	 * @return the fechaFinInterfaz
	 */
	public Timestamp getFechaFinInterfaz() {
		return fechaFinInterfaz;
	}

	/**
	 * @param fechaFinInterfaz the fechaFinInterfaz to set
	 */
	public void setFechaFinInterfaz(Timestamp fechaFinInterfaz) {
		this.fechaFinInterfaz = fechaFinInterfaz;
	}

	/**
	 * @return the flagErrorProcesoPosteriorInterfaz
	 */
	public String getFlagErrorProcesoPosteriorInterfaz() {
		return flagErrorProcesoPosteriorInterfaz;
	}

	/**
	 * @param flagErrorProcesoPosteriorInterfaz the flagErrorProcesoPosteriorInterfaz to set
	 */
	public void setFlagErrorProcesoPosteriorInterfaz(
			String flagErrorProcesoPosteriorInterfaz) {
		this.flagErrorProcesoPosteriorInterfaz = flagErrorProcesoPosteriorInterfaz;
	}

	/**
	 * @return the fechaIniProcesoPosteriorInterfaz
	 */
	public Timestamp getFechaIniProcesoPosteriorInterfaz() {
		return fechaIniProcesoPosteriorInterfaz;
	}

	/**
	 * @param fechaIniProcesoPosteriorInterfaz the fechaIniProcesoPosteriorInterfaz to set
	 */
	public void setFechaIniProcesoPosteriorInterfaz(
			Timestamp fechaIniProcesoPosteriorInterfaz) {
		this.fechaIniProcesoPosteriorInterfaz = fechaIniProcesoPosteriorInterfaz;
	}

	/**
	 * @return the fechaFinProcesoPosteriorInterfaz
	 */
	public Timestamp getFechaFinProcesoPosteriorInterfaz() {
		return fechaFinProcesoPosteriorInterfaz;
	}

	/**
	 * @param fechaFinProcesoPosteriorInterfaz the fechaFinProcesoPosteriorInterfaz to set
	 */
	public void setFechaFinProcesoPosteriorInterfaz(
			Timestamp fechaFinProcesoPosteriorInterfaz) {
		this.fechaFinProcesoPosteriorInterfaz = fechaFinProcesoPosteriorInterfaz;
	}

    
	/* FIN FRAMEWORK NSSICC */
	
	

}
