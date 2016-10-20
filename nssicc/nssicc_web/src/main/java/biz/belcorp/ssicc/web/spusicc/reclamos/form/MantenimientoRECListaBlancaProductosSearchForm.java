package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECListaBlancaProductosSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoOperacion[];
	private String codigoTipoOperacion[];
	private String codigoVenta;
	private String codigoCliente;
	private String codigoMotivoReal;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	
	private String lineaDefecto;
	private String lineaMaxima;
	
	private String regionList[];
	private String zonaList[];
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoOperacion
	 */
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoOperacion(String[] codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	/**
	 * @return the codigoTipoOperacion
	 */
	public String[] getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}
	/**
	 * @param codigoTipoOperacion the codigoTipoOperacion to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoOperacion(String[] codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}
	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the codigoMotivoReal
	 */
	public String getCodigoMotivoReal() {
		return codigoMotivoReal;
	}
	/**
	 * @param codigoMotivoReal the codigoMotivoReal to set
	 */
	public void setCodigoMotivoReal(String codigoMotivoReal) {
		this.codigoMotivoReal = codigoMotivoReal;
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
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}
	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}
	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}
	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}
	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}
	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
}