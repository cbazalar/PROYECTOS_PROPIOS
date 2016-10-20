package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class FtpCobrador extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String servidorFTP;
	private String puertoFTP;
	private String usuarioFTP;
	private String claveFTP;
	private String directorioFTP;				
    
	public FtpCobrador() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the servidorFTP
	 */
	public String getServidorFTP() {
		return servidorFTP;
	}

	/**
	 * @param servidorFTP the servidorFTP to set
	 */
	public void setServidorFTP(String servidorFTP) {
		this.servidorFTP = servidorFTP;
	}

	/**
	 * @return the puertoFTP
	 */
	public String getPuertoFTP() {
		return puertoFTP;
	}

	/**
	 * @param puertoFTP the puertoFTP to set
	 */
	public void setPuertoFTP(String puertoFTP) {
		this.puertoFTP = puertoFTP;
	}

	/**
	 * @return the usuarioFTP
	 */
	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	/**
	 * @param usuarioFTP the usuarioFTP to set
	 */
	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}

	/**
	 * @return the claveFTP
	 */
	public String getClaveFTP() {
		return claveFTP;
	}

	/**
	 * @param claveFTP the claveFTP to set
	 */
	public void setClaveFTP(String claveFTP) {
		this.claveFTP = claveFTP;
	}

	/**
	 * @return the directorioFTP
	 */
	public String getDirectorioFTP() {
		return directorioFTP;
	}

	/**
	 * @param directorioFTP the directorioFTP to set
	 */
	public void setDirectorioFTP(String directorioFTP) {
		this.directorioFTP = directorioFTP;
	}		
	
	
}
