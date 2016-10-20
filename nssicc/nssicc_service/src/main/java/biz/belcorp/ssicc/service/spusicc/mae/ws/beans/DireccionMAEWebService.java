package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="DireccionMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * 
 */
public class DireccionMAEWebService implements Serializable {

 	private static final long serialVersionUID = 1L;

 	private String tipoDir;
 	private String nivel1;
 	private String nivel2;
 	private String nivel3;
 	private String tipoVia;
 	private String direccion;
 	private String numPrinc;
 	private String observaciones;
 	
	/**
	 * @return the tipoDir
	 */
	public String getTipoDir() {
		return tipoDir;
	}
	/**
	 * @param tipoDir the tipoDir to set
	 */
	public void setTipoDir(String tipoDir) {
		this.tipoDir = tipoDir;
	}
	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}
	/**
	 * @param nivel1 the nivel1 to set
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}
	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}
	/**
	 * @param nivel2 the nivel2 to set
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}
	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}
	/**
	 * @param nivel3 the nivel3 to set
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}
	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}
	/**
	 * @param tipoVia the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the numPrinc
	 */
	public String getNumPrinc() {
		return numPrinc;
	}
	/**
	 * @param numPrinc the numPrinc to set
	 */
	public void setNumPrinc(String numPrinc) {
		this.numPrinc = numPrinc;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
