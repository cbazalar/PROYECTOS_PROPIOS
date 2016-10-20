package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jose Cairampoma
 * Clase utilizada para el proceso de Recepcion de Emails de las Consultoras que
 * son enviadas por Privilege
 *
 */
public class EmailCliente extends AuditableBaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -466242385683401385L;
	public String codigoConsultora;
    public String emailConsultora;
    
	
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
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}


	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}


	/**
	 * @return the emailConsultora
	 */
	public String getEmailConsultora() {
		return emailConsultora;
	}


	/**
	 * @param emailConsultora the emailConsultora to set
	 */
	public void setEmailConsultora(String emailConsultora) {
		this.emailConsultora = emailConsultora;
	}

}
