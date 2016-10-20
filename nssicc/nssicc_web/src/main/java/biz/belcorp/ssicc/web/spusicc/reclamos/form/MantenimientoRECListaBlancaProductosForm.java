package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import org.primefaces.model.UploadedFile;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRECListaBlancaProductosForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidListaBlanca;
	private String codigoPais;
	private String codigoOperacion[];
	private String codigoTipoOperacion[];
	private String codigoVenta;
	private String codigoCliente;
	private String codigoPeriodoInicio;
	private String codigoMotivoReal;
	private String codigoPeriodoFin;
	private UploadedFile codigoVentaFile;
	private UploadedFile codigoClienteFile;
	
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
	 * @return the codigoPeriodoInicio
	 * @struts.validator type = "required" 
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 * 
	 * @struts.validator type = "required"
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
	 * @return the codigoVentaFile
	 */
	public UploadedFile getCodigoVentaFile() {
		return codigoVentaFile;
	}
	/**
	 * @param codigoVentaFile the codigoVentaFile to set
	 */
	public void setCodigoVentaFile(UploadedFile codigoVentaFile) {
		this.codigoVentaFile = codigoVentaFile;
	}
	/**
	 * @return the codigoClienteFile
	 */
	public UploadedFile getCodigoClienteFile() {
		return codigoClienteFile;
	}
	/**
	 * @param codigoClienteFile the codigoClienteFile to set
	 */
	public void setCodigoClienteFile(UploadedFile codigoClienteFile) {
		this.codigoClienteFile = codigoClienteFile;
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
	
	/**
	 * @return the codigoMotivoReal
	 */
	public String getCodigoMotivoReal() {
		return codigoMotivoReal;
	}
	/**
	 * @param codigoMotivoReal the codigoMotivoReal to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoMotivoReal(String codigoMotivoReal) {
		this.codigoMotivoReal = codigoMotivoReal;
	}
	/**
	 * @return the oidListaBlanca
	 */
	public String getOidListaBlanca() {
		return oidListaBlanca;
	}
	/**
	 * @param oidListaBlanca the oidListaBlanca to set
	 */
	public void setOidListaBlanca(String oidListaBlanca) {
		this.oidListaBlanca = oidListaBlanca;
	}
}