package biz.belcorp.ssicc.web.spusicc.ruv.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRUVRegistroVentasSearchForm extends BaseSearchForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	
	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private String codigoClienteDesde;     
	private String codigoClienteHasta;  
	private String oidImpuestos;  
	
	private String oidCanal;
	private String oidAcceso;
	
    private String oidSubacceso;
    private String oidTipoDocLegal;
    private String serieDocuLegal;
    private String numeroDocLegalDesde;
    private String numeroDocLegalHasta;
    private String exportar;
	private String oid;
	
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
	 * @return the fechaEmisionDesde
	 */
	public Date getFechaEmisionDesde() {
		return fechaEmisionDesde;
	}
	/**
	 * @param fechaEmisionDesde the fechaEmisionDesde to set
	 */
	public void setFechaEmisionDesde(Date fechaEmisionDesde) {
		this.fechaEmisionDesde = fechaEmisionDesde;
	}
	/**
	 * @return the fechaEmisionHasta
	 */
	public Date getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}
	/**
	 * @param fechaEmisionHasta the fechaEmisionHasta to set
	 */
	public void setFechaEmisionHasta(Date fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}
	/**
	 * @return the codigoClienteDesde
	 */
	public String getCodigoClienteDesde() {
		return codigoClienteDesde;
	}
	/**
	 * @param codigoClienteDesde the codigoClienteDesde to set
	 */
	public void setCodigoClienteDesde(String codigoClienteDesde) {
		this.codigoClienteDesde = codigoClienteDesde;
	}
	/**
	 * @return the codigoClienteHasta
	 */
	public String getCodigoClienteHasta() {
		return codigoClienteHasta;
	}
	/**
	 * @param codigoClienteHasta the codigoClienteHasta to set
	 */
	public void setCodigoClienteHasta(String codigoClienteHasta) {
		this.codigoClienteHasta = codigoClienteHasta;
	}
	/**
	 * @return the oidImpuestos
	 */
	public String getOidImpuestos() {
		return oidImpuestos;
	}
	/**
	 * @param oidImpuestos the oidImpuestos to set
	 */
	public void setOidImpuestos(String oidImpuestos) {
		this.oidImpuestos = oidImpuestos;
	}
	/**
	 * @return the oidTipoDocLegal
	 */
	public String getOidTipoDocLegal() {
		return oidTipoDocLegal;
	}
	/**
	 * @param oidTipoDocLegal the oidTipoDocLegal to set
	 */
	public void setOidTipoDocLegal(String oidTipoDocLegal) {
		this.oidTipoDocLegal = oidTipoDocLegal;
	}
	/**
	 * @return the serieDocuLegal
	 */
	public String getSerieDocuLegal() {
		return serieDocuLegal;
	}
	/**
	 * @param serieDocuLegal the serieDocuLegal to set
	 */
	public void setSerieDocuLegal(String serieDocuLegal) {
		this.serieDocuLegal = serieDocuLegal;
	}
	/**
	 * @return the numeroDocLegalDesde
	 */
	public String getNumeroDocLegalDesde() {
		return numeroDocLegalDesde;
	}
	/**
	 * @param numeroDocLegalDesde the numeroDocLegalDesde to set
	 */
	public void setNumeroDocLegalDesde(String numeroDocLegalDesde) {
		this.numeroDocLegalDesde = numeroDocLegalDesde;
	}
	/**
	 * @return the numeroDocLegalHasta
	 */
	public String getNumeroDocLegalHasta() {
		return numeroDocLegalHasta;
	}
	/**
	 * @param numeroDocLegalHasta the numeroDocLegalHasta to set
	 */
	public void setNumeroDocLegalHasta(String numeroDocLegalHasta) {
		this.numeroDocLegalHasta = numeroDocLegalHasta;
	}
	/**
	 * @return the exportar
	 */
	public String getExportar() {
		return exportar;
	}
	/**
	 * @param exportar the exportar to set
	 */
	public void setExportar(String exportar) {
		this.exportar = exportar;
	}
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the oidCanal
	 */
	public String getOidCanal() {
		return oidCanal;
	}
	/**
	 * @param oidCanal the oidCanal to set
	 */
	public void setOidCanal(String oidCanal) {
		this.oidCanal = oidCanal;
	}
	/**
	 * @return the oidAcceso
	 */
	public String getOidAcceso() {
		return oidAcceso;
	}
	/**
	 * @param oidAcceso the oidAcceso to set
	 */
	public void setOidAcceso(String oidAcceso) {
		this.oidAcceso = oidAcceso;
	}
	/**
	 * @return the oidSubacceso
	 */
	public String getOidSubacceso() {
		return oidSubacceso;
	}
	/**
	 * @param oidSubacceso the oidSubacceso to set
	 */
	public void setOidSubacceso(String oidSubacceso) {
		this.oidSubacceso = oidSubacceso;
	}

}

