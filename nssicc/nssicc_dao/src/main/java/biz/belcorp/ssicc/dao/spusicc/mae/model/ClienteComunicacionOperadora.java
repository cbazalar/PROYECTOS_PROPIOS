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
 * <a href="ClienteComunicacionOperadora.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ClienteComunicacionOperadora extends AuditableBaseObject implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codigoTipoComun;    
    private String valTextComun;
    private String codTipoOperador;
    private String desTipoOperador;
    private String oidConsultora;
    private Long oidTipoComunicacion;
    
    
	/**
	 * @return the oidTipoComunicacion
	 */
	public Long getOidTipoComunicacion() {
		return oidTipoComunicacion;
	}
	/**
	 * @param oidTipoComunicacion the oidTipoComunicacion to set
	 */
	public void setOidTipoComunicacion(Long oidTipoComunicacion) {
		this.oidTipoComunicacion = oidTipoComunicacion;
	}
	/**
	 * @return the oidConsultora
	 */
	public String getOidConsultora() {
		return oidConsultora;
	}
	/**
	 * @param oidConsultora the oidConsultora to set
	 */
	public void setOidConsultora(String oidConsultora) {
		this.oidConsultora = oidConsultora;
	}
	/**
	 * @return the codigoTipoComun
	 */
	public String getCodigoTipoComun() {
		return codigoTipoComun;
	}
	/**
	 * @param codigoTipoComun the codigoTipoComun to set
	 */
	public void setCodigoTipoComun(String codigoTipoComun) {
		this.codigoTipoComun = codigoTipoComun;
	}
	/**
	 * @return the valTextComun
	 */
	public String getValTextComun() {
		return valTextComun;
	}
	/**
	 * @param valTextComun the valTextComun to set
	 */
	public void setValTextComun(String valTextComun) {
		this.valTextComun = valTextComun;
	}
	/**
	 * @return the codTipoOperador
	 */
	public String getCodTipoOperador() {
		return codTipoOperador;
	}
	/**
	 * @param codTipoOperador the codTipoOperador to set
	 */
	public void setCodTipoOperador(String codTipoOperador) {
		this.codTipoOperador = codTipoOperador;
	}
	/**
	 * @return the desTipoOperador
	 */
	public String getDesTipoOperador() {
		return desTipoOperador;
	}
	/**
	 * @param desTipoOperador the desTipoOperador to set
	 */
	public void setDesTipoOperador(String desTipoOperador) {
		this.desTipoOperador = desTipoOperador;
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
