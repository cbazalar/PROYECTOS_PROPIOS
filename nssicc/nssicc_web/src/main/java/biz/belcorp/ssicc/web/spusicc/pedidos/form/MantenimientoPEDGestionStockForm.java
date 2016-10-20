package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDGestionStockForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoVenta;
	private String codigoCampanha;
	private String TipoIngreso;
	private String descripcionCuv;
	private String valor;
	private String oidTipoCliente[];
	private String oidSubTipoCliente[];
	private String oidTipoClasificacion[];
	private String oidClasificacion[];
	private String regionList[];
	private String zonaList[];
	private String fechaActivacion;
	private UploadedFile codigoVentaFile;
	private UploadedFile codigoSapFile;
	private String codigoSapFileLabel;
	private String codigoVentaFileLabel;
	private String codigosErradosFile;
	private String codigoVentaPrincipal;
	private String indicadorActivo;
	
	private String indCierreDiario;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return the tipoIngreso
	 */
	public String getTipoIngreso() {
		return TipoIngreso;
	}
	/**
	 * @param tipoIngreso the tipoIngreso to set
	 * @struts.validator type="required"
	 */
	public void setTipoIngreso(String tipoIngreso) {
		TipoIngreso = tipoIngreso;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 * @struts.validator type="required"
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @return the oidTipoCliente
	 */
	public String[] getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String[] oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the oidSubTipoCliente
	 */
	public String[] getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}
	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String[] oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}
	/**
	 * @return the oidTipoClasificacion
	 */
	public String[] getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String[] oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	/**
	 * @return the oidClasificacion
	 */
	public String[] getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String[] oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
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
	 * @return the fechaActivacion
	 */
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	/**
	 * @param fechaActivacion the fechaActivacion to set
	 */
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
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
	 * @return the codigoCampanha
	 */
	public String getCodigoCampanha() {
		return codigoCampanha;
	}
	/**
	 * @param codigoCampanha the codigoCampanha to set
	 * @struts.validator type="required"
	 */
	public void setCodigoCampanha(String codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
	/**
	 * @return the descripcionCuv
	 */
	public String getDescripcionCuv() {
		return descripcionCuv;
	}
	/**
	 * @param descripcionCuv the descripcionCuv to set
	 */
	public void setDescripcionCuv(String descripcionCuv) {
		this.descripcionCuv = descripcionCuv;
	}
	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	/**
	 * @return the codigoSapFile
	 */
	public UploadedFile getCodigoSapFile() {
		return codigoSapFile;
	}
	/**
	 * @param codigoSapFile the codigoSapFile to set
	 */
	public void setCodigoSapFile(UploadedFile codigoSapFile) {
		this.codigoSapFile = codigoSapFile;
	}
	/**
	 * @return the codigoSapFileLabel
	 */
	public String getCodigoSapFileLabel() {
		return codigoSapFileLabel;
	}
	/**
	 * @param codigoSapFileLabel the codigoSapFileLabel to set
	 */
	public void setCodigoSapFileLabel(String codigoSapFileLabel) {
		this.codigoSapFileLabel = codigoSapFileLabel;
	}
	/**
	 * @return the codigoVentaFileLabel
	 */
	public String getCodigoVentaFileLabel() {
		return codigoVentaFileLabel;
	}
	/**
	 * @param codigoVentaFileLabel the codigoVentaFileLabel to set
	 */
	public void setCodigoVentaFileLabel(String codigoVentaFileLabel) {
		this.codigoVentaFileLabel = codigoVentaFileLabel;
	}
	/**
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the indCierreDiario
	 */
	public String getIndCierreDiario() {
		return indCierreDiario;
	}
	/**
	 * @param indCierreDiario the indCierreDiario to set
	 */
	public void setIndCierreDiario(String indCierreDiario) {
		this.indCierreDiario = indCierreDiario;
	}
	
	
}