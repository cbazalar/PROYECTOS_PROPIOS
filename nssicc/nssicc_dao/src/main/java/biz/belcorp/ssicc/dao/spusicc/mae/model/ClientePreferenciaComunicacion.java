/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClientePreferenciaComunicacion.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ClientePreferenciaComunicacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	private String codigoTipoPrefe;
	private String desTipoPreferen;
	private String ordenTipoPref;
	
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
	 * @return the codigoTipoPrefe
	 */
	public String getCodigoTipoPrefe() {
		return codigoTipoPrefe;
	}
	/**
	 * @param codigoTipoPrefe the codigoTipoPrefe to set
	 */
	public void setCodigoTipoPrefe(String codigoTipoPrefe) {
		this.codigoTipoPrefe = codigoTipoPrefe;
	}
	/**
	 * @return the desTipoPreferen
	 */
	public String getDesTipoPreferen() {
		return desTipoPreferen;
	}
	/**
	 * @param desTipoPreferen the desTipoPreferen to set
	 */
	public void setDesTipoPreferen(String desTipoPreferen) {
		this.desTipoPreferen = desTipoPreferen;
	}
	/**
	 * @return the ordenTipoPref
	 */
	public String getOrdenTipoPref() {
		return ordenTipoPref;
	}
	/**
	 * @param ordenTipoPref the ordenTipoPref to set
	 */
	public void setOrdenTipoPref(String ordenTipoPref) {
		this.ordenTipoPref = ordenTipoPref;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		return null;
	}
	
	
}
