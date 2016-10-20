package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class LlamadaCobranza extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoAccion;
	private String accionCobranza;
	private String fecha;
	private String hora;
	private String observacion;
	private String fechaPago;
	private String importePago;
	private String cobrador;
	
	public LlamadaCobranza() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
    
	public String getAccionCobranza() {
		return accionCobranza;
	}

	public void setAccionCobranza(String accionCobranza) {
		this.accionCobranza = accionCobranza;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public String getImportePago() {
		return importePago;
	}

	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}

	public String getCobrador() {
		return cobrador;
	}

	public void setCobrador(String cobrador) {
		this.cobrador = cobrador;
	}
	

		
}
