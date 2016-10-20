/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.web.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoSABCargarFuenteVentaPrevistaForm extends BaseCargaArchivoForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318730272263572952L;
		
	private String codigoPais;		
	private String codigoPeriodoInicio;	
	private String codigoPeriodoFin;
	private boolean flagCalcular;
	private boolean flagMostrarCalculo;
	private String indPeriYaCargados;
	private boolean flagMostrarMensajeCamp;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the flagCalcular
	 */
	public boolean isFlagCalcular() {
		return flagCalcular;
	}
	/**
	 * @param flagCalcular the flagCalcular to set
	 */
	public void setFlagCalcular(boolean flagCalcular) {
		this.flagCalcular = flagCalcular;
	}
	/**
	 * @return the flagMostrarCalculo
	 */
	public boolean isFlagMostrarCalculo() {
		return flagMostrarCalculo;
	}
	/**
	 * @param flagMostrarCalculo the flagMostrarCalculo to set
	 */
	public void setFlagMostrarCalculo(boolean flagMostrarCalculo) {
		this.flagMostrarCalculo = flagMostrarCalculo;
	}
	/**
	 * @return the indPeriYaCargados
	 */
	public String getIndPeriYaCargados() {
		return indPeriYaCargados;
	}
	/**
	 * @param indPeriYaCargados the indPeriYaCargados to set
	 */
	public void setIndPeriYaCargados(String indPeriYaCargados) {
		this.indPeriYaCargados = indPeriYaCargados;
	}
	/**
	 * @return the flagMostrarMensajeCamp
	 */
	public boolean isFlagMostrarMensajeCamp() {
		return flagMostrarMensajeCamp;
	}
	/**
	 * @param flagMostrarMensajeCamp the flagMostrarMensajeCamp to set
	 */
	public void setFlagMostrarMensajeCamp(boolean flagMostrarMensajeCamp) {
		this.flagMostrarMensajeCamp = flagMostrarMensajeCamp;
	}
	
}
