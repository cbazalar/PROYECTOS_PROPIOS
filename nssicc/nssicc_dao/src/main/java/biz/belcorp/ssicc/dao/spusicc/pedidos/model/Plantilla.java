/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author peextrramirez
 *
 */
public class Plantilla implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4796960497733396025L;

	private String codigoPlantilla;
	
	private String descripcionPlantilla;
	
	private String codigoZona;
	
	private String numeroSecuencia;
	
	private String grupoProceso;

	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	public String getDescripcionPlantilla() {
		return descripcionPlantilla;
	}

	public void setDescripcionPlantilla(String descripcionPlantilla) {
		this.descripcionPlantilla = descripcionPlantilla;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getGrupoProceso() {
		return grupoProceso;
	}

	public void setGrupoProceso(String grupoProceso) {
		this.grupoProceso = grupoProceso;
	}

}
