package biz.belcorp.ssicc.service.spusicc.let.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="LiderLETWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * 
 */
public class LiderLETWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoLider;
	private String nombreLider;
	private String codigoSeccion;
	private String codigoZona;
	private String codigoRegion;
	private String codigoPais;
    
    public LiderLETWebService(){
    	this.codigoLider = "";
    	this.nombreLider = "";
    	this.codigoSeccion = "";
    	this.codigoZona = "";
    	this.codigoRegion = "";
    	this.codigoPais = "";
    }

	/**
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}

	/**
	 * @param codigoLider the codigoLider to set
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}

	/**
	 * @return the nombreLider
	 */
	public String getNombreLider() {
		return nombreLider;
	}

	/**
	 * @param nombreLider the nombreLider to set
	 */
	public void setNombreLider(String nombreLider) {
		this.nombreLider = nombreLider;
	}

	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
    
   
}
