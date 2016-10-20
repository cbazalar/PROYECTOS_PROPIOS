/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author cbazalar
 *
 */
public class ProcesoOCRActualizarUnidadesMaximas extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 7245529832253112179L;
	String usuario;
	String[] listaId  ;
	String[] listaCodigoPais;
	String[] listaCodigoPeriodo;
	String[] listaCodigoCliente ;
	String[] listaNumLote ;
	String[] listaCodigoVta;
    String[] listaUnidadDemanda ;
    String[] listaUnidadDemandaIni;
    String[] listaIndicadorPROL;
    
    
	/**
	 * @return the listaId
	 */
	public String[] getListaId() {
		return listaId;
	}

	/**
	 * @param listaId the listaId to set
	 */
	public void setListaId(String[] listaId) {
		this.listaId = listaId;
	}

	/**
	 * @return the listaUnidadDemanda
	 */
	public String[] getListaUnidadDemanda() {
		return listaUnidadDemanda;
	}

	/**
	 * @param listaUnidadDemanda the listaUnidadDemanda to set
	 */
	public void setListaUnidadDemanda(String[] listaUnidadDemanda) {
		this.listaUnidadDemanda = listaUnidadDemanda;
	}

	/**
	 * @return the listaUnidadDemandaIni
	 */
	public String[] getListaUnidadDemandaIni() {
		return listaUnidadDemandaIni;
	}

	/**
	 * @param listaUnidadDemandaIni the listaUnidadDemandaIni to set
	 */
	public void setListaUnidadDemandaIni(String[] listaUnidadDemandaIni) {
		this.listaUnidadDemandaIni = listaUnidadDemandaIni;
	}
	
	

	/**
	 * @return the listaCodigoPais
	 */
	public String[] getListaCodigoPais() {
		return listaCodigoPais;
	}

	/**
	 * @param listaCodigoPais the listaCodigoPais to set
	 */
	public void setListaCodigoPais(String[] listaCodigoPais) {
		this.listaCodigoPais = listaCodigoPais;
	}

	/**
	 * @return the listaCodigoCliente
	 */
	public String[] getListaCodigoCliente() {
		return listaCodigoCliente;
	}

	/**
	 * @param listaCodigoCliente the listaCodigoCliente to set
	 */
	public void setListaCodigoCliente(String[] listaCodigoCliente) {
		this.listaCodigoCliente = listaCodigoCliente;
	}

	/**
	 * @return the listaNumLote
	 */
	public String[] getListaNumLote() {
		return listaNumLote;
	}

	/**
	 * @param listaNumLote the listaNumLote to set
	 */
	public void setListaNumLote(String[] listaNumLote) {
		this.listaNumLote = listaNumLote;
	}

	/**
	 * @return the listaCodigoVta
	 */
	public String[] getListaCodigoVta() {
		return listaCodigoVta;
	}

	/**
	 * @param listaCodigoVta the listaCodigoVta to set
	 */
	public void setListaCodigoVta(String[] listaCodigoVta) {
		this.listaCodigoVta = listaCodigoVta;
	}
		
	/**
	 * @return the listaCodigoPeriodo
	 */
	public String[] getListaCodigoPeriodo() {
		return listaCodigoPeriodo;
	}

	/**
	 * @param listaCodigoPeriodo the listaCodigoPeriodo to set
	 */
	public void setListaCodigoPeriodo(String[] listaCodigoPeriodo) {
		this.listaCodigoPeriodo = listaCodigoPeriodo;
	}
	
	/**
	 * @return the listaIndicadorPROL
	 */
	public String[] getListaIndicadorPROL() {
		return listaIndicadorPROL;
	}
	
	/**
	 * @param listaIndicadorPROL the listaIndicadorPROL to set
	 */
	public void setListaIndicadorPROL(String[] listaIndicadorPROL) {
		this.listaIndicadorPROL = listaIndicadorPROL;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
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

}
