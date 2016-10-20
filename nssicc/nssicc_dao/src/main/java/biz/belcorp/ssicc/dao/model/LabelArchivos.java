/**
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="LabelArchivos.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */

public class LabelArchivos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreArchivo;
	private String pesoArchivo;
	private String lineasArchivo;
	private String indicadorEjecucion;
	private long numeroRegistro;
	private String observacion;
	
	private boolean error = false; //NSSiCC
	private boolean esArchivo = true; //NSSiCC
	
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the pesoArchivo
	 */
	public String getPesoArchivo() {
		return pesoArchivo;
	}

	/**
	 * @param pesoArchivo the pesoArchivo to set
	 */
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}

	/**
	 * @return the lineasArchivo
	 */
	public String getLineasArchivo() {
		return lineasArchivo;
	}

	/**
	 * @param lineasArchivo the lineasArchivo to set
	 */
	public void setLineasArchivo(String lineasArchivo) {
		this.lineasArchivo = lineasArchivo;
	}

	/**
	 * @return the indicadorEjecucion
	 */
	public String getIndicadorEjecucion() {
		return indicadorEjecucion;
	}

	/**
	 * @param indicadorEjecucion the indicadorEjecucion to set
	 */
	public void setIndicadorEjecucion(String indicadorEjecucion) {
		this.indicadorEjecucion = indicadorEjecucion;
	}

	public String toString() {
		return new ToStringBuilder(this).append("nombreArchivo", this.nombreArchivo).append(
				"pesoArchivo", this.pesoArchivo).append("lineasArchivo", this.lineasArchivo).toString();
	}

	/**
	 * @return the numerRegistro
	 */
	public long getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * @param numerRegistro the numerRegistro to set
	 */
	public void setNumerRegistro(long numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	

	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(long numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	
	
	/* INI NSSICC */
	
	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return the esArchivo
	 */
	public boolean isEsArchivo() {
		return esArchivo;
	}

	/**
	 * @param esArchivo the esArchivo to set
	 */
	public void setEsArchivo(boolean esArchivo) {
		this.esArchivo = esArchivo;
	}
	
	
	/* FIN NSSICC */
	
	
	
	
}
