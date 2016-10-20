/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * Representa a la entidad STO_HISTO_PROCE de la base de datos.
 * 
 * <p>
 * <a href="HistoricoTipoDocumento.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cariampoma</a>
 * 
 */
public class HistoricoTipoDocumento extends AuditableBaseObject implements Serializable,Cloneable{

	private String codigoPais;
	
	private String codTipoDocu;

	private String numeroProceso;

	private String fechaInicioProceso;

	private String fechaFinProceso;

	private String flagError;

	private String descripcionError;

	private String usuarioProceso;

	private String estadoProceso;	

	private Long registrosProcesados;

	private String codigoAccionEjecutada;
	
	private Long minutosEjecucion;
	
	private String numeroProcesoPadre;
	
	private String indicadorMasivo;

	
	public HistoricoTipoDocumento(String codigoPais, String codTipoDocu,String codigoAccionEjecutada,String usuarioProceso, String numeroProceso,List stoList) {
		
		
		super();
		this.codigoPais = codigoPais;
		this.codTipoDocu = codTipoDocu;
		this.flagError = Constants.NO;
		this.usuarioProceso = usuarioProceso;
		this.estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_OK;
		this.registrosProcesados = new Long(0);
		this.codigoAccionEjecutada = codigoAccionEjecutada;
		this.numeroProceso = numeroProceso;
		this.indicadorMasivo ="S";		
    	if (stoList!=null) indicadorMasivo = "N";
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public HistoricoTipoDocumento() {
		this.codigoPais = "";
		this.codTipoDocu = "";
		this.numeroProceso = "";
		this.fechaInicioProceso = "";
		this.fechaFinProceso = "";
		this.flagError = "";
		this.descripcionError = "";
		this.usuarioProceso = "";
		this.estadoProceso = "";
		this.registrosProcesados = new Long(0);
		this.codigoAccionEjecutada = "";
	}
	
	public Object clone() throws CloneNotSupportedException{
		HistoricoTipoDocumento obj = null;
		 
		obj = (HistoricoTipoDocumento)super.clone();
		obj.numeroProceso = new String(obj.numeroProceso);
		return obj;
	}

	/**
	 * @param codigoPais
	 * @param codTipoDocu
	 * @param numeroProceso
	 * @param fechaInicioProceso
	 * @param fechaFinProceso
	 * @param flagError
	 * @param descripcionError
	 * @param usuarioProceso
	 * @param estadoProceso
	 * @param registrosProcesados
	 * @param codigoAccionEjecutada
	 */
	public HistoricoTipoDocumento(String codigoPais, String codTipoDocu,
			String numeroProceso, String fechaInicioProceso,
			String fechaFinProceso, String flagError, String descripcionError,
			String usuarioProceso, String estadoProceso,
			Long registrosProcesados, String codigoAccionEjecutada) {
		super();
		this.codigoPais = codigoPais;
		this.codTipoDocu = codTipoDocu;
		this.numeroProceso = numeroProceso;
		this.fechaInicioProceso = fechaInicioProceso;
		this.fechaFinProceso = fechaFinProceso;
		this.flagError = flagError;
		this.descripcionError = descripcionError;
		this.usuarioProceso = usuarioProceso;
		this.estadoProceso = estadoProceso;
		this.registrosProcesados = registrosProcesados;
		this.codigoAccionEjecutada = codigoAccionEjecutada;
	}	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = prime 
				+ ((codTipoDocu == null) ? 0 : codTipoDocu.hashCode());
		result = prime
				* result
				+ ((codigoAccionEjecutada == null) ? 0 : codigoAccionEjecutada
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((descripcionError == null) ? 0 : descripcionError.hashCode());
		result = prime * result
				+ ((estadoProceso == null) ? 0 : estadoProceso.hashCode());
		result = prime * result
				+ ((fechaFinProceso == null) ? 0 : fechaFinProceso.hashCode());
		result = prime
				* result
				+ ((fechaInicioProceso == null) ? 0 : fechaInicioProceso
						.hashCode());
		result = prime * result
				+ ((flagError == null) ? 0 : flagError.hashCode());
		result = prime * result
				+ ((numeroProceso == null) ? 0 : numeroProceso.hashCode());
		result = prime
				* result
				+ ((registrosProcesados == null) ? 0 : registrosProcesados
						.hashCode());
		result = prime * result
				+ ((usuarioProceso == null) ? 0 : usuarioProceso.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (getClass() != obj.getClass())
			return false;
		HistoricoTipoDocumento other = (HistoricoTipoDocumento) obj;
		if (codTipoDocu == null) {
			if (other.codTipoDocu != null)
				return false;
		} else if (!codTipoDocu.equals(other.codTipoDocu))
			return false;
		if (codigoAccionEjecutada == null) {
			if (other.codigoAccionEjecutada != null)
				return false;
		} else if (!codigoAccionEjecutada.equals(other.codigoAccionEjecutada))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (descripcionError == null) {
			if (other.descripcionError != null)
				return false;
		} else if (!descripcionError.equals(other.descripcionError))
			return false;
		if (estadoProceso == null) {
			if (other.estadoProceso != null)
				return false;
		} else if (!estadoProceso.equals(other.estadoProceso))
			return false;
		if (fechaFinProceso == null) {
			if (other.fechaFinProceso != null)
				return false;
		} else if (!fechaFinProceso.equals(other.fechaFinProceso))
			return false;
		if (fechaInicioProceso == null) {
			if (other.fechaInicioProceso != null)
				return false;
		} else if (!fechaInicioProceso.equals(other.fechaInicioProceso))
			return false;
		if (flagError == null) {
			if (other.flagError != null)
				return false;
		} else if (!flagError.equals(other.flagError))
			return false;
		if (numeroProceso == null) {
			if (other.numeroProceso != null)
				return false;
		} else if (!numeroProceso.equals(other.numeroProceso))
			return false;
		if (registrosProcesados == null) {
			if (other.registrosProcesados != null)
				return false;
		} else if (!registrosProcesados.equals(other.registrosProcesados))
			return false;
		if (usuarioProceso == null) {
			if (other.usuarioProceso != null)
				return false;
		} else if (!usuarioProceso.equals(other.usuarioProceso))
			return false;
		return true;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codTipoDocu
	 */
	public String getCodTipoDocu() {
		return codTipoDocu;
	}

	/**
	 * @param codTipoDocu the codTipoDocu to set
	 */
	public void setCodTipoDocu(String codTipoDocu) {
		this.codTipoDocu = codTipoDocu;
	}

	/**
	 * @return the numeroProceso
	 */
	public String getNumeroProceso() {
		return numeroProceso;
	}

	/**
	 * @param numeroProceso the numeroProceso to set
	 */
	public void setNumeroProceso(String numeroProceso) {
		this.numeroProceso = numeroProceso;
	}

	/**
	 * @return the fechaInicioProceso
	 */
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	/**
	 * @param fechaInicioProceso the fechaInicioProceso to set
	 */
	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	/**
	 * @return the fechaFinProceso
	 */
	public String getFechaFinProceso() {
		return fechaFinProceso;
	}

	/**
	 * @param fechaFinProceso the fechaFinProceso to set
	 */
	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	/**
	 * @return the flagError
	 */
	public String getFlagError() {
		return flagError;
	}

	/**
	 * @param flagError the flagError to set
	 */
	public void setFlagError(String flagError) {
		this.flagError = flagError;
	}

	/**
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError the descripcionError to set
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * @return the usuarioProceso
	 */
	public String getUsuarioProceso() {
		return usuarioProceso;
	}

	/**
	 * @param usuarioProceso the usuarioProceso to set
	 */
	public void setUsuarioProceso(String usuarioProceso) {
		this.usuarioProceso = usuarioProceso;
	}

	/**
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}

	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	/**
	 * @return the registrosProcesados
	 */
	public Long getRegistrosProcesados() {
		return registrosProcesados;
	}

	/**
	 * @param registrosProcesados the registrosProcesados to set
	 */
	public void setRegistrosProcesados(Long registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	/**
	 * @return the codigoAccionEjecutada
	 */
	public String getCodigoAccionEjecutada() {
		return codigoAccionEjecutada;
	}

	/**
	 * @param codigoAccionEjecutada the codigoAccionEjecutada to set
	 */
	public void setCodigoAccionEjecutada(String codigoAccionEjecutada) {
		this.codigoAccionEjecutada = codigoAccionEjecutada;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the minutosEjecucion
	 */
	public Long getMinutosEjecucion() {
		return minutosEjecucion;
	}

	/**
	 * @param minutosEjecucion the minutosEjecucion to set
	 */
	public void setMinutosEjecucion(Long minutosEjecucion) {
		this.minutosEjecucion = minutosEjecucion;
	}

	/**
	 * @return the numeroProcesoPadre
	 */
	public String getNumeroProcesoPadre() {
		return numeroProcesoPadre;
	}

	/**
	 * @param numeroProcesoPadre the numeroProcesoPadre to set
	 */
	public void setNumeroProcesoPadre(String numeroProcesoPadre) {
		this.numeroProcesoPadre = numeroProcesoPadre;
	}
	
	
	/**
	 * 
	 * @param registrosProcesados
	 */
	public void updateSTOHistoricoOnSuccess(int registrosProcesados) {
		this.flagError = Constants.NO;
		this.registrosProcesados = new Long(registrosProcesados);		
		this.estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_OK;
	}

	
	/**
	 * 
	 * @param e
	 */
	public void updateSTOHistoricoOnException(Exception e) {
		this.flagError = Constants.SI;
		this.estadoProceso = Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO;
		this.descripcionError = e.toString();
	}

	/**
	 * @return the indicadorMasivo
	 */
	public String getIndicadorMasivo() {
		return indicadorMasivo;
	}

	/**
	 * @param indicadorMasivo the indicadorMasivo to set
	 */
	public void setIndicadorMasivo(String indicadorMasivo) {
		this.indicadorMasivo = indicadorMasivo;
	}


}
