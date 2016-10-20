/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="EncuestaConsLoveMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class EncuestaConsLoveMAEWebService implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String codigoCliente;     
    private String infMarca;
    private String fechaNaci;
    private String emailDuplaPot;
    private String detLogro;
    private String necLogro;
    private String valLogro;
    private String cmpIniLogro;
    private String cmpFinLogro;
    private String fechaActV3;
    private String fechaActV6;
    private String fechaActEst;
    private String porAsigLogro;
    private String usuario;
    
    public EncuestaConsLoveMAEWebService(){
    	codigoCliente=infMarca=fechaNaci=
        emailDuplaPot=detLogro=necLogro=valLogro=
        cmpIniLogro=cmpFinLogro=fechaActV3=fechaActV6=
        fechaActEst=porAsigLogro=usuario="";
    }
    
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
	 * @return the infMarca
	 */
	public String getInfMarca() {
		return infMarca;
	}
	/**
	 * @param infMarca the infMarca to set
	 */
	public void setInfMarca(String infMarca) {
		this.infMarca = infMarca;
	}
	/**
	 * @return the fechaNaci
	 */
	public String getFechaNaci() {
		return fechaNaci;
	}
	/**
	 * @param fechaNaci the fechaNaci to set
	 */
	public void setFechaNaci(String fechaNaci) {
		this.fechaNaci = fechaNaci;
	}
	/**
	 * @return the emailDuplaPot
	 */
	public String getEmailDuplaPot() {
		return emailDuplaPot;
	}
	/**
	 * @param emailDuplaPot the emailDuplaPot to set
	 */
	public void setEmailDuplaPot(String emailDuplaPot) {
		this.emailDuplaPot = emailDuplaPot;
	}
	/**
	 * @return the detLogro
	 */
	public String getDetLogro() {
		return detLogro;
	}
	/**
	 * @param detLogro the detLogro to set
	 */
	public void setDetLogro(String detLogro) {
		this.detLogro = detLogro;
	}
	/**
	 * @return the necLogro
	 */
	public String getNecLogro() {
		return necLogro;
	}
	/**
	 * @param necLogro the necLogro to set
	 */
	public void setNecLogro(String necLogro) {
		this.necLogro = necLogro;
	}
	/**
	 * @return the valLogro
	 */
	public String getValLogro() {
		return valLogro;
	}
	/**
	 * @param valLogro the valLogro to set
	 */
	public void setValLogro(String valLogro) {
		this.valLogro = valLogro;
	}
	/**
	 * @return the cmpIniLogro
	 */
	public String getCmpIniLogro() {
		return cmpIniLogro;
	}
	/**
	 * @param cmpIniLogro the cmpIniLogro to set
	 */
	public void setCmpIniLogro(String cmpIniLogro) {
		this.cmpIniLogro = cmpIniLogro;
	}
	/**
	 * @return the cmpFinLogro
	 */
	public String getCmpFinLogro() {
		return cmpFinLogro;
	}
	/**
	 * @param cmpFinLogro the cmpFinLogro to set
	 */
	public void setCmpFinLogro(String cmpFinLogro) {
		this.cmpFinLogro = cmpFinLogro;
	}
	/**
	 * @return the fechaActV3
	 */
	public String getFechaActV3() {
		return fechaActV3;
	}
	/**
	 * @param fechaActV3 the fechaActV3 to set
	 */
	public void setFechaActV3(String fechaActV3) {
		this.fechaActV3 = fechaActV3;
	}
	/**
	 * @return the fechaActV6
	 */
	public String getFechaActV6() {
		return fechaActV6;
	}
	/**
	 * @param fechaActV6 the fechaActV6 to set
	 */
	public void setFechaActV6(String fechaActV6) {
		this.fechaActV6 = fechaActV6;
	}
	/**
	 * @return the fechaActEst
	 */
	public String getFechaActEst() {
		return fechaActEst;
	}
	/**
	 * @param fechaActEst the fechaActEst to set
	 */
	public void setFechaActEst(String fechaActEst) {
		this.fechaActEst = fechaActEst;
	}
	/**
	 * @return the porAsigLogro
	 */
	public String getPorAsigLogro() {
		return porAsigLogro;
	}
	/**
	 * @param porAsigLogro the porAsigLogro to set
	 */
	public void setPorAsigLogro(String porAsigLogro) {
		this.porAsigLogro = porAsigLogro;
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
    
  
}
