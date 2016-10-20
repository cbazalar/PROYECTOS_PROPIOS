package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;


/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class OperacionCodigoVenta implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String numeroLinea;
    private String operacion;
    private String cuvCambia; 
    private String cantidadCambia;
    private String motivoDevolucion;
    private String cuvDeseado;
    private String cantidadDeseada;
    private String gestion;
    private String rechazado;
    private String enviado;
    private String eliminado;
    
    
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getNumeroLinea() {
		return numeroLinea;
	}
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getCuvCambia() {
		return cuvCambia;
	}
	public void setCuvCambia(String cuvCambia) {
		this.cuvCambia = cuvCambia;
	}
	public String getCantidadCambia() {
		return cantidadCambia;
	}
	public void setCantidadCambia(String cantidadCambia) {
		this.cantidadCambia = cantidadCambia;
	}
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}
	public String getCuvDeseado() {
		return cuvDeseado;
	}
	public void setCuvDeseado(String cuvDeseado) {
		this.cuvDeseado = cuvDeseado;
	}
	public String getCantidadDeseada() {
		return cantidadDeseada;
	}
	public void setCantidadDeseada(String cantidadDeseada) {
		this.cantidadDeseada = cantidadDeseada;
	}
	public String getGestion() {
		return gestion;
	}
	public void setGestion(String gestion) {
		this.gestion = gestion;
	}
	public String getRechazado() {
		return rechazado;
	}
	public void setRechazado(String rechazado) {
		this.rechazado = rechazado;
	}
	public String getEnviado() {
		return enviado;
	}
	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
}
