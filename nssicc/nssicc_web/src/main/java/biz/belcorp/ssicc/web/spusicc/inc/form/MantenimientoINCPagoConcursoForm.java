package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCPagoConcursoForm extends BaseEditForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	
	private String codigoPago;
	private String numeroConcurso;
	private String nombrePago;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;

	private String codigoClasificacion;
	private String indicePeriodo;
	private String estadoPago;
	
	private boolean indActualizarBonoPremio;
	private List listBonoPremio;
	

	public void reset() {
		this.codigoPago=this.numeroConcurso=null;
		this.nombrePago = this.codigoPeriodoInicio = null;
		this.codigoPeriodoFin = null;
		this.codigoClasificacion=null;
		this.indicePeriodo=null;
		this.indActualizarBonoPremio = false;
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return the indicePeriodo
	 */
	public String getIndicePeriodo() {
		return indicePeriodo;
	}

	/**
	 * @param indicePeriodo the indicePeriodo to set
	 * @struts.validator type="required"
	 */
	public void setIndicePeriodo(String indicePeriodo) {
		this.indicePeriodo = indicePeriodo;
	}

	/**
	 * @return the indActualizarBonoPremio
	 */
	public boolean isIndActualizarBonoPremio() {
		return indActualizarBonoPremio;
	}

	/**
	 * @param indActualizarBonoPremio the indActualizarBonoPremio to set
	 */
	public void setIndActualizarBonoPremio(boolean indActualizarBonoPremio) {
		this.indActualizarBonoPremio = indActualizarBonoPremio;
	}

	/**
	 * @return the listBonoPremio
	 */
	public List getListBonoPremio() {
		return listBonoPremio;
	}

	/**
	 * @param listBonoPremio the listBonoPremio to set
	 */
	public void setListBonoPremio(List listBonoPremio) {
		this.listBonoPremio = listBonoPremio;
	}

}
