package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;



public class ValidacionVencimientoCronograma implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoZona;
	private String fechaInicial;
	private String fechaFinal;
	private String codigoActividad;
	private String codigoActividadAuxiliar;
	private String codigoPeriodo;
	
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getCodigoActividad() {
		return codigoActividad;
	}
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}
	public String getCodigoActividadAuxiliar() {
		return codigoActividadAuxiliar;
	}
	public void setCodigoActividadAuxiliar(String codigoActividadAuxiliar) {
		this.codigoActividadAuxiliar = codigoActividadAuxiliar;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
   

}
