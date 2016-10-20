package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;


/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class CDRsAnteriores implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroReclamo;
    private String estado;
    private String fechaIngreso;
    private String descripcion; 
    private String unidadesReclamadas;
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public String getUnidadesReclamadas() {
		return unidadesReclamadas;
	}
	public void setUnidadesReclamadas(String unidadesReclamadas) {
		this.unidadesReclamadas = unidadesReclamadas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
