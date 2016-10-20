package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form name = "consultaINTEstadoInterfazForm"
 */
public class ConsultaINTEstadoInterfazForm extends BaseSearchForm implements
		Serializable {
	private String codigoPais;

	private String codigoSistema;

	private String descripcionError;

	private String codigoInterfaz;
	
	private String codigoInterfazTexto;

	private Date fechaInicioProcesoDate;
	
	private String fechaInicioProceso;

	private String estado;

	private String numeroLote;
	
	private String recomendacion;
		

	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz
	 *            The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the descripcionError.
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError
	 *            The descripcionError to set.
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * @return Returns the numeroLote.
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            The numeroLote to set.
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return Returns the codigoInterfazTexto.
	 */
	public String getCodigoInterfazTexto() {
		return codigoInterfazTexto;
	}

	/**
	 * @param codigoInterfazTexto The codigoInterfazTexto to set.
	 */
	public void setCodigoInterfazTexto(String codigoInterfazTexto) {
		this.codigoInterfazTexto = codigoInterfazTexto;
	}

	/**
	 * @return the recomendacion
	 */
	public String getRecomendacion() {
		return recomendacion;
	}

	/**
	 * @param recomendacion the recomendacion to set
	 */
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	public Date getFechaInicioProcesoDate() {
		return fechaInicioProcesoDate;
	}

	public void setFechaInicioProcesoDate(Date fechaInicioProcesoDate) {
		this.fechaInicioProcesoDate = fechaInicioProcesoDate;
	}

	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	
	

}
