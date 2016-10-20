/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 *  
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ProcesoBatchHisto extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414104057481540737L;

	private String codigoPais;
	
	private String codigoSistema;
	
	private String codigoInterfaz;

	private Sistema sistema;

	private ProcesoBatch procesoBatch;

	private Timestamp fechaInicioProceso;

	private Timestamp fechaFinProceso;
	
	private String numeroLote;
	
	private String log;

	private String usuarioProceso;
	
    private String codigoEstadoProceso;
	
	private String descEstadoProceso;
	
	private String status;
	
    private String descripcionEtapaProceso;
	
	private String descripcionRecomendacion;

	private String periodo;
	
	private Timestamp fechaProceso;
	
	private Long idProcesoBatch;
	
	private String valorAdicional1;
	
	private String valorAdicional2;
	
	private String valorAdicional3;
	
	public ProcesoBatchHisto() {
		codigoPais = "";
		codigoSistema = "";
		numeroLote = "";
		usuarioProceso = "";
		codigoEstadoProceso = "";
		descEstadoProceso = "";
		log = "";
		status = "";
		descripcionEtapaProceso = Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT;
		descripcionRecomendacion = "";
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

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * @return Returns the log.
	 */
	public String getLog() {
		return log;
	}


	/**
	 * @param log The log to set.
	 */
	public void setLog(String log) {
		this.log = log;
	}


	/**
	 * @return Returns the procesoBatch.
	 */
	public ProcesoBatch getProcesoBatch() {
		return procesoBatch;
	}


	/**
	 * @param procesoBatch The procesoBatch to set.
	 */
	public void setProcesoBatch(ProcesoBatch procesoBatch) {
		this.procesoBatch = procesoBatch;
	}


	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}


	/**
	 * @param codigoSistema The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}


	/**
	 * @return Returns the codigoEstadoProceso.
	 */
	public String getCodigoEstadoProceso() {
		return codigoEstadoProceso;
	}


	/**
	 * @param codigoEstadoProceso The codigoEstadoProceso to set.
	 */
	public void setCodigoEstadoProceso(String codigoEstadoProceso) {
		this.codigoEstadoProceso = codigoEstadoProceso;
	}


	/**
	 * @return Returns the descEstadoProceso.
	 */
	public String getDescEstadoProceso() {
		return descEstadoProceso;
	}


	/**
	 * @param descEstadoProceso The descEstadoProceso to set.
	 */
	public void setDescEstadoProceso(String descEstadoProceso) {
		this.descEstadoProceso = descEstadoProceso;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}


	/**
	 * @return the descripcionEtapaProceso
	 */
	public String getDescripcionEtapaProceso() {
		return descripcionEtapaProceso;
	}


	/**
	 * @param descripcionEtapaProceso the descripcionEtapaProceso to set
	 */
	public void setDescripcionEtapaProceso(String descripcionEtapaProceso) {
		this.descripcionEtapaProceso = descripcionEtapaProceso;
	}


	/**
	 * @return the descripcionRecomendacion
	 */
	public String getDescripcionRecomendacion() {
		return descripcionRecomendacion;
	}


	/**
	 * @param descripcionRecomendacion the descripcionRecomendacion to set
	 */
	public void setDescripcionRecomendacion(String descripcionRecomendacion) {
		this.descripcionRecomendacion = descripcionRecomendacion;
	}


	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}


	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	/**
	 * @return the fechaProceso
	 */
	public Timestamp getFechaProceso() {
		return fechaProceso;
	}


	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(Timestamp fechaProceso) {
		this.fechaProceso = fechaProceso;
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
	 * @return the valorAdicional1
	 */
	public String getValorAdicional1() {
		return valorAdicional1;
	}


	/**
	 * @param valorAdicional1 the valorAdicional1 to set
	 */
	public void setValorAdicional1(String valorAdicional1) {
		this.valorAdicional1 = valorAdicional1;
	}


	/**
	 * @return the valorAdicional2
	 */
	public String getValorAdicional2() {
		return valorAdicional2;
	}


	/**
	 * @param valorAdicional2 the valorAdicional2 to set
	 */
	public void setValorAdicional2(String valorAdicional2) {
		this.valorAdicional2 = valorAdicional2;
	}


	/**
	 * @return the valorAdicional3
	 */
	public String getValorAdicional3() {
		return valorAdicional3;
	}


	/**
	 * @param valorAdicional3 the valorAdicional3 to set
	 */
	public void setValorAdicional3(String valorAdicional3) {
		this.valorAdicional3 = valorAdicional3;
	}



}
