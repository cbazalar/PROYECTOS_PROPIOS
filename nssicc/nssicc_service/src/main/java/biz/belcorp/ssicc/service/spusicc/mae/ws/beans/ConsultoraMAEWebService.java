/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultoraMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ConsultoraMAEWebService implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codigoConsultora;     
    private String email;
    private String telefonoFijo;
    private String telefonoCelular;
    
    
    public ConsultoraMAEWebService(){
    	codigoConsultora=email=telefonoFijo=
        telefonoCelular="";
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}


	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}


	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}


	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
    
    
	  
}
