package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ConsultaPasesSsiccForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 	
 * @author Jose Luis Rodriguez
 */

public class ConsultaPasesSsiccForm extends BaseSearchForm {
	
	private static final long serialVersionUID = -8979931440349308076L;
	
	private String codigoPais;
	private String codigoPase;
	private String paisMarca;
	private String anho;
	private String mes;
	private String descErrorGenerado;
	private String descErrorSentencia;
	private String numeroErrores;
	private String tipoSeleccion;
	
	
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
	 * @return the codigoPase
	 */
	@Size(max=15)
	public String getCodigoPase() {
		return codigoPase;
	}

	/**
	 * @param codigoPase the codigoPase to set
	 */
	public void setCodigoPase(String codigoPase) {
		this.codigoPase = codigoPase;
	}

	/**
	 * @return the paisMarca
	 */
	public String getPaisMarca() {
		return paisMarca;
	}

	/**
	 * @param paisMarca the paisMarca to set
	 */
	public void setPaisMarca(String paisMarca) {
		this.paisMarca = paisMarca;
	}
	
	/**
	 * @return the anho
	 */
	@Size(max=4)
	public String getAnho() {
		return anho;
	}

	/**
	 * @param anho the anho to set
	 */
	public void setAnho(String anho) {
		this.anho = anho;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the descErrorGenerado
	 */
	public String getDescErrorGenerado() {
		return descErrorGenerado;
	}

	/**
	 * @param descErrorGenerado the descErrorGenerado to set
	 */
	public void setDescErrorGenerado(String descErrorGenerado) {
		this.descErrorGenerado = descErrorGenerado;
	}

	/**
	 * @return the descErrorSentencia
	 */
	public String getDescErrorSentencia() {
		return descErrorSentencia;
	}

	/**
	 * @param descErrorSentencia the descErrorSentencia to set
	 */
	public void setDescErrorSentencia(String descErrorSentencia) {
		this.descErrorSentencia = descErrorSentencia;
	}

	/**
	 * @return the numeroErrores
	 */
	public String getNumeroErrores() {
		return numeroErrores;
	}

	/**
	 * @param numeroErrores the numeroErrores to set
	 */
	public void setNumeroErrores(String numeroErrores) {
		this.numeroErrores = numeroErrores;
	}

	/**
	 * @return the tipoSeleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * @param tipoSeleccion the tipoSeleccion to set
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	

}
