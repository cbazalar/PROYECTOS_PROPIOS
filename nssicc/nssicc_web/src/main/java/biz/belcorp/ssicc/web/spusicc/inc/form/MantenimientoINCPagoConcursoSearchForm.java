package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCPagoConcursoSearchForm extends BaseSearchForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	
	private String codigoPago;
	private String numeroConcurso;
	private String nombrePago;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;

	private String codigoClasificacion;
	private String estadoPago;


	public void reset() {
		this.codigoPago=this.numeroConcurso=null;
		this.nombrePago = this.codigoPeriodoInicio = null;
		this.codigoPeriodoFin = this.estadoPago = null;
		this.codigoClasificacion=null;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPago
	 */
	public String getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @param codigoPago the codigoPago to set
	 */
	public void setCodigoPago(String codigoPago) {
		this.codigoPago = codigoPago;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	/**
	 * @return the nombrePago
	 */
	public String getNombrePago() {
		return nombrePago;
	}

	/**
	 * @param nombrePago the nombrePago to set
	 */
	public void setNombrePago(String nombrePago) {
		this.nombrePago = nombrePago;
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
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}

	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

}
