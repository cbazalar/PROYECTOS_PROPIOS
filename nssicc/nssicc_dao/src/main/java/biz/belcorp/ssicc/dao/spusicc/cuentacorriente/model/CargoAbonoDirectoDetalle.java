/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class CargoAbonoDirectoDetalle extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String codigoConsultora;	
	private String nombreConsultora;
	private String observaciones;
	private String importeCAD;	
	private int numeroLinea;

	
	
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
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

	/**
	 * @return the importeCAD
	 */
	public String getImporteCAD() {
		return importeCAD;
	}

	/**
	 * @param importeCAD the importeCAD to set
	 */
	public void setImporteCAD(String importeCAD) {
		this.importeCAD = importeCAD;
	}

	/**
	 * @return the numeroLinea
	 */
	public int getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @param numeroLinea the numeroLinea to set
	 */
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
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

}
