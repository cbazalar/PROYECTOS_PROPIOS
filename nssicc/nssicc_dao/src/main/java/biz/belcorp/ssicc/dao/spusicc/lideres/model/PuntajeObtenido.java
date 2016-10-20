package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class PuntajeObtenido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoCliente;
	private String codigoPeriodo;      		
	private String nombreConcurso;     		
	private String valPuntajeAcumulado;    
	private String valPuntajeDisponible;   
	private String valPuntajeCanjeado;
	
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getNombreConcurso() {
		return nombreConcurso;
	}
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}
	public String getValPuntajeAcumulado() {
		return valPuntajeAcumulado;
	}
	public void setValPuntajeAcumulado(String valPuntajeAcumulado) {
		this.valPuntajeAcumulado = valPuntajeAcumulado;
	}
	public String getValPuntajeDisponible() {
		return valPuntajeDisponible;
	}
	public void setValPuntajeDisponible(String valPuntajeDisponible) {
		this.valPuntajeDisponible = valPuntajeDisponible;
	}
	public String getValPuntajeCanjeado() {
		return valPuntajeCanjeado;
	}
	public void setValPuntajeCanjeado(String valPuntajeCanjeado) {
		this.valPuntajeCanjeado = valPuntajeCanjeado;
	}   
	
}
