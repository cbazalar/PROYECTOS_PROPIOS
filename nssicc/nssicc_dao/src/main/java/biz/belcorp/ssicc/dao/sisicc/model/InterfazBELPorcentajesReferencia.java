/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazBELPorcentajesReferencia.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazBELPorcentajesReferencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -684342541658218099L;
	private String  periodo;
	private String  codigoCliente;
    private String  estadoFacturacion;
    private String  codigoGrupo;
    private Double  porcentajeComision;
 
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getCodigoGrupo() {
        return codigoGrupo;
    }
    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }
    public String getEstadoFacturacion() {
        return estadoFacturacion;
    }
    public void setEstadoFacturacion(String estadoFacturacion) {
        this.estadoFacturacion = estadoFacturacion;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public Double getPorcentajeComision() {
        return porcentajeComision;
    }
    public void setPorcentajeComision(Double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }
    
}
