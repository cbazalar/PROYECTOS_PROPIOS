/*
 * Created on 29/12/2015 10:04:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaRMSWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaFacturaRMSWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 * 
 * 
 */
public class ConsultaCatalogosRMSWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoCatalogo;
	private String descripcionCatalogo;
	
	
	/**
	 * @return the codigoCatalogo
	 */
	public Integer getCodigoCatalogo() {
		return codigoCatalogo;
	}
	/**
	 * @param codigoCatalogo the codigoCatalogo to set
	 */
	public void setCodigoCatalogo(Integer codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}	
	
	/**
	 * @return the descripcionCatalogo
	 */
	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}
	/**
	 * @param descripcionCatalogo the descripcionCatalogo to set
	 */
	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}
	
	
}