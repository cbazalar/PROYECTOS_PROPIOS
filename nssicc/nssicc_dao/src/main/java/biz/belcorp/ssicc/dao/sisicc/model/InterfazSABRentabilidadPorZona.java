/*
 * Created on 14-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSABRentabilidadPorZona.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazSABRentabilidadPorZona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3648267232100196759L;
	private String codigoPais;
	private String codigoSociedad;
	private String codigoEjercicio;
	private String codigoPeriodo;
	private String codigoZona;
	private String descripcionZona;
	private String tipoMoneda;
	private String centroCosto;
	private String cuentaContable;
	private String codigoMoneda;
	private Double valorIncobrabilidad;
		
	/**
	 * @return Returns the centroCosto.
	 */
	public String getCentroCosto() {
		return centroCosto;
	}
	/**
	 * @param centroCosto The centroCosto to set.
	 */
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	/**
	 * @return Returns the codigoEjercicio.
	 */
	public String getCodigoEjercicio() {
		return codigoEjercicio;
	}
	/**
	 * @param codigoEjercicio The codigoEjercicio to set.
	 */
	public void setCodigoEjercicio(String codigoEjercicio) {
		this.codigoEjercicio = codigoEjercicio;
	}
	/**
	 * @return Returns the codigoMoneda.
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	/**
	 * @param codigoMoneda The codigoMoneda to set.
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the cuentaContable.
	 */
	public String getCuentaContable() {
		return cuentaContable;
	}
	/**
	 * @param cuentaContable The cuentaContable to set.
	 */
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return Returns the tipoMoneda.
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	/**
	 * @param tipoMoneda The tipoMoneda to set.
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	/**
	 * @return Returns the valorIncobrabilidad.
	 */
	public Double getValorIncobrabilidad() {
		return valorIncobrabilidad;
	}
	/**
	 * @param valorIncobrabilidad The valorIncobrabilidad to set.
	 */
	public void setValorIncobrabilidad(Double valorIncobrabilidad) {
		this.valorIncobrabilidad = valorIncobrabilidad;
	}
}
