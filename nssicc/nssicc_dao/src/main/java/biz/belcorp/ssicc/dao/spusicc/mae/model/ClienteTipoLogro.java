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
 * <a href="ClienteTipoLogro.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ClienteTipoLogro extends AuditableBaseObject implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codTipoLogro;    
    private String desTipoLogro;
	/**
	 * @return the codTipoLogro
	 */
	public String getCodTipoLogro() {
		return codTipoLogro;
	}
	/**
	 * @param codTipoLogro the codTipoLogro to set
	 */
	public void setCodTipoLogro(String codTipoLogro) {
		this.codTipoLogro = codTipoLogro;
	}
	/**
	 * @return the desTipoLogro
	 */
	public String getDesTipoLogro() {
		return desTipoLogro;
	}
	/**
	 * @param desTipoLogro the desTipoLogro to set
	 */
	public void setDesTipoLogro(String desTipoLogro) {
		this.desTipoLogro = desTipoLogro;
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
